package com.winter.app.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

//***-context.xml 이랑 같음
@Configuration
@Slf4j
public class FileMapping implements WebMvcConfigurer {
	
	@Value("${app.upload.url}")
	private String urlPath; // /files/**
	
	@Value("${app.upload.base}")
	private String filePath; // D://upload/
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.warn(urlPath);
		log.warn(filePath);
		

		
		registry.addResourceHandler(urlPath)
		.addResourceLocations(filePath);

		
	}
	
}
