package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.service.UserService;

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
						if(user!=null) {
							return true;
						}
						break;
					}
				}
			}
			request.setAttribute("LoginMsg", "请先登录！");
			request.getRequestDispatcher("/").forward(request, response);
			return false;
	}
}
