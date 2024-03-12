package com.winter.app.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String message = "로그인 실패";
		//instanceof 타입이맞냐?
		if(exception instanceof BadCredentialsException) {
			message = "비밀번호를 확인";
		}
		
		if(exception instanceof InternalAuthenticationServiceException) {
			message = "아이디가 없다";
		}
		
		if(exception instanceof AccountExpiredException) {
			message = "계정 유효기간 만료";
		}
		
		if(exception instanceof LockedException) {
			message = "계정이 잠김";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			message = "비밀번호 유효기간 만료";
		}
		
		if(exception instanceof DisabledException) {
			message = "휴먼계정입니다.";
		}
		
		//한글이 혹시라도 깨질수 있기 때문에 인코딩해서 보내기
		message = URLEncoder.encode(message,"UTF-8");
		
		//리다이렉트 방법
		response.sendRedirect("/member/login?message="+message);
		
		//포워드 방법 
//		request.setAttribute("message", message);
//		request.setAttribute("error", true);
//		request.getRequestDispatcher("/member/login").forward(request, response);
	}

}
