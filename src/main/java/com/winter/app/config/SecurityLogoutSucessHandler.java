package com.winter.app.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLogoutSucessHandler implements LogoutSuccessHandler{

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		//카카오 로그아웃
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		
		if(memberVO.getSocial() == null) {
			
			response.sendRedirect("/");
			return;
		}
		
		if(memberVO.getSocial().equals("KaKao")) {
			log.info("카카오 사용자가 맞다");
		}
			
		
		
		
	}
	
	

}
