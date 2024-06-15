package com.example.demo.bean;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.service.UserService;
import com.example.demo.util.BaseContext;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
			Cookie[] cookies = request.getCookies();
			if(cookies!=null) {
				for (Cookie cookie : cookies) {
					if("token".equals(cookie.getName())) {
						User user= userService.selectByToken(cookie.getValue());
//						发布问题时，需要获取用户id，因此在这里设置
						BaseContext.setCurrentId(user.getId());
//						当前端持久化存有cookie，访问页面时，不会经过callback下的代码，因此没有用户对象存入session，因此前端页面会展示错误，
//						需要在此处存入
						request.getSession().setAttribute("gitHubUser", user);
						if(user!=null) {
							return true;
						}
						break;
					}
				}
			}
			response.sendRedirect("/?LoginMsg="+ URLEncoder.encode("请先登录！", "UTF-8"));
			return false;
	}
}
