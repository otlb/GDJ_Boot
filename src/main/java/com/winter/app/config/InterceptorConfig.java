package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.winter.app.interceptors.LoginIntercoeptor;
import com.winter.app.interceptors.TestInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private TestInterceptor testInterceptor;
	@Autowired
	private LoginIntercoeptor loginIntercoeptor;
	@Autowired
	private LocaleChangeInterceptor changeInterceptor;
	
	
	
	
	
	
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		
//		//어떤 url이 왔을때 어떤 interceptor를 거치게 할것인가?
//		registry.addInterceptor(testInterceptor)
//				.addPathPatterns("/notice/**")
//				.excludePathPatterns("/notice/add");
//		
		
		//로그인
//		registry.addInterceptor(loginIntercoeptor)
//				.addPathPatterns("/**")
//				.excludePathPatterns("");
		
		//message 인터셉터
		registry.addInterceptor(changeInterceptor)
				.addPathPatterns("/**");
	}
	
	
	
	
	
	

}
