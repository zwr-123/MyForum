package com.example.demo.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson2.JSONObject;
import com.example.demo.bean.GitHubUser;
import com.example.demo.bean.OAuthForGitHub;
import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	UserService us;

	public static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
	OkHttpClient client = new OkHttpClient();


	
	/**
	 * code ---> access_token  --->插入用户信息
	 * @date 2024年5月7日 上午9:42:49
	 * @param code
	 * @param ra
	 * @return
	 */
	@SuppressWarnings("finally")
	@GetMapping("/callback")
	public String CallBack(@RequestParam String code, HttpServletRequest req,HttpServletResponse rep)  {
		/**
		 * github返回code后请求access_token信息
		 */
		log.info("返回的code：{}", code);
		try {
			OAuthForGitHub ofg = new OAuthForGitHub("Ov23lieCe1UectFRY6bN", "b3354cfc757af7862de00bf211aa5b9cc74f5cc5",
					code);
			String json = JSONObject.toJSONString(ofg);
			String res = runPost("https://github.com/login/oauth/access_token", json);
			log.info("返回的json：{}", res);

			/**
			 * 获得access_token后请求用户信息
			 */
			if (res != null) {
				String access_token = res.split("&")[0].split("=")[1];
//			https://api.github.com/user
				GitHubUser gitHubUser = runGet("https://api.github.com/user", access_token);

//				如果gitHubUser为空 重定向到首页。这里最好用全局异常处理
				if(gitHubUser==null) {
					return "redirect:/";
				}
				
//				若gitHubUser不为空，则添加到Session。以便首页显示用户名
				req.getSession().setAttribute("gitHubUser", gitHubUser);
				
				//查询是否为新用户
				User resUser=us.selectByID(gitHubUser.getId());
				if(resUser==null) {
					//存入数据库
					User user = new User();
					BeanUtils.copyProperties(gitHubUser, user);
					String token=UUID.randomUUID().toString();
					user.setToken(token);
					user.setCreateTime(LocalDateTime.now());
					user.setUpdateTime(LocalDateTime.now());
					us.insert(user);
					rep.addCookie(new Cookie("token", token));
				}
				
//				若已存在用户，传递cookie给前端
				rep.addCookie(new Cookie("token", resUser.getToken()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			return "redirect:/";
		}
	}

	
	
	String runPost(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(json, JSON);
		Request request = new Request.Builder().url(url).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public GitHubUser runGet(String url, String access_token) throws IOException {
		Request request = new Request.Builder().url(url).addHeader("Authorization", "Bearer " + access_token).build();
		try (Response response = client.newCall(request).execute()) {
			String res = response.body().string();
			log.info("返回的用户信息：{}",res);
			JSONObject.parseObject(res, GitHubUser.class);
//			return response.body().string().split("\"")[3];
			return JSONObject.parseObject(res, GitHubUser.class);
		}
	}
}
