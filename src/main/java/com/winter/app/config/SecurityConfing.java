package com.winter.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfing {
	@Autowired
	private SecurityLoginSucessHandler handler;
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web
				.ignoring()  //무시해라
				.requestMatchers("/css/**") //요청한것들은 즉 작성한 경로들은 보안확인 하지말고 무시해라  
				.requestMatchers("/js/**")
				.requestMatchers("/vendor/**")
				.requestMatchers("/img/**")
				.requestMatchers("/favicon/**")
				;
 	}
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security)throws Exception {
//		security.cors()
//				.and()
//				.csrf()
//				.disable();
		security.
		//권한에 관련된 설정
		authorizeHttpRequests((authorizeHttpRequests)->
								authorizeHttpRequests
								.requestMatchers("/").permitAll() //누구나 접근가능
								.requestMatchers("/member/add").permitAll()
								.requestMatchers("/member/page", "/member/logout").authenticated()
								.requestMatchers("notice/list").authenticated()
								.requestMatchers("/notice/add", "/notice/delete").hasRole("ADMIN")// admin만 접근가능,경로도 2개 사용가능
								.requestMatchers("/notice/update").hasAnyRole("ADMIN, MABAGER")// 2개중 한개에 속하면 접근가능,또는 
								//.anyRequest().authenticated()//로그인한 사용자만 
								.anyRequest().permitAll()// 누구나 다
								//.anyRequest().denyAll()//전부 불가능 
								
							)//authorizeHttpRequests 끝 부분
		
							//시큐리티가 제공하는 로그인 페이지가 아닌 내가 만든 로그인 페이지로 이동해라 
							.formLogin(
									(login)->
										login
											.loginPage("/member/login")
											//.failureUrl("")//로그인 실패했을때
											.failureHandler(failHandler)//로그인이 실패했을때 특정행동을 하고싶을때 
											//.defaultSuccessUrl("/")//로그인이 성공하면 어디로 보낼것인가 ,로그인후 특정행동이 없으면 사용 
											.successHandler(handler) //defaultSuccessUrl이랑 같이 사용할수없음,로그인후 특정행동이 필요하면 사용 
											//.passwordParameter("pw") security에서 기본으로 파라미터 이름을 password 저장하는데 파라미터 이름을 바꾸고싶으면 사용
											//.usernameParameter("id") password와 동일 
											.permitAll()							
							
							)//formLogin 끝 부분
							.logout(
									(logout)->
										logout											
											.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) //작성한 url의 경로의 요청이 오면 로그아웃해라
											.logoutSuccessUrl("/") //로그아웃이 성공하면 작성한 경로로 가라
											.invalidateHttpSession(true) //로그아웃할때 세션을 없애라
											.permitAll()
							)
							;	
		
		
		return security.build();
		
		}
	@Bean
	PasswordEncoder passwordEncoder() {
		//패스워드 암호화 해주는 객체 
		return new BCryptPasswordEncoder();
	}

}
