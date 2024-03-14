package com.winter.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginSucessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String rememberId = request.getParameter("rememberId");
		
		if(rememberId != null) {
			//꺼낸 ID를 Client에 Cookie에 저장
			Cookie cookie = new Cookie("rememberId", authentication.getName());
			cookie.setMaxAge(600);
			cookie.setPath("/");//서브도메인에서도 사용가능
			
			response.addCookie(cookie);
			
			
		}else {
			Cookie [] cookies = request.getCookies();
			
			for(Cookie c : cookies) {
				if(c.getName().equals("rememberId")) {
					c.setMaxAge(0);
					c.setValue("");
					c.setPath("/");
					response.addCookie(c);
					log.info("Cookie 삭제 하기");
					break;
				}
			}
		}
		
		
		response.sendRedirect("/");
		
	}

	
}
