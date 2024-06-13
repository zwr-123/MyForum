package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.bean.LoginCheckInterceptor;

@Configuration
public class config1 implements WebMvcConfigurer{
	@Autowired
	LoginCheckInterceptor lcInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(lcInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/","/callback","/css/**","/js/**");
	}
}
