package com.example.demo.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.bean.LoginCheckInterceptor;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.SessionTrackingMode;

@Configuration
public class config1 implements WebMvcConfigurer{
	@Autowired
	LoginCheckInterceptor lcInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(lcInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/","/callback","/css/**","/js/**","/pic/**","/comment/**");
	}
	
	@Bean
	public ServletContextInitializer servletContextInitializer() {
	    return new ServletContextInitializer() {
	        @Override
	        public void onStartup(ServletContext servletContext) throws ServletException {
	            servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE) );
	        }
	    };
	}


	@Override
	public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**") // 对所有路径应用跨域配置
         .allowedOriginPatterns("*") // 允许任何域进行跨域访问
         .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许的请求方法
         .allowedHeaders("*") // 允许任意的请求头 
         .allowCredentials(true); // 是否允许发送凭据
	}
	
	
}
