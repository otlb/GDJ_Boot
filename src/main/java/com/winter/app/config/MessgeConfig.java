package com.winter.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration// xml설정 파일 
public class MessgeConfig implements WebMvcConfigurer {
	
	@Bean //객체생성 
	LocaleResolver localeResolver() {
		//session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		
		//cookie
		CookieLocaleResolver cResolver2 = new CookieLocaleResolver();
		cResolver2.setDefaultLocale(Locale.KOREAN);
		
		
		return cResolver2;
	}
	
	//인터셉터 생성
	@Bean
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		return changeInterceptor;
		
	}
	
//	//인터셉터 등록 
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(changeInterceptor()).ad
//	}
	
}
