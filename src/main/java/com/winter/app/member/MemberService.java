package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired	
	private PasswordEncoder passwordEncoder;
	
	
	
	@Override //아이디를 가지고와 db에서 조회 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		log.info("===============로그인==============");
		log.info("====={}======",username);
		try {
			memberVO = memberDAO.getDetail(memberVO);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return memberVO;
	}


	public int add(MemberVO memberVO)throws Exception{
		//비밀번호 암호화
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));	
		int result =  memberDAO.add(memberVO);
		
		//회원의 role정보 저장
		result = memberDAO.addMemberRole(memberVO);
		return result;
	}
	
	
	//add 검증 메서드
	//비번일치, 아이디 중복여부 
	public boolean checkMember(MemberVO memberVO,BindingResult bindingResult)throws Exception{
		boolean check = false;
		
		//check가 true라면 에러가 있다
		//check가 false라면 에러가 없다
		
		//annotation 검증 결과
		check = bindingResult.hasErrors();
		
		//비번 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true;
			bindingResult.rejectValue("passwordCheck","memberVO.password.equals");
		}		
		
		//id중복 
		MemberVO result = memberDAO.getDetail(memberVO);
		
		if(result != null) {
			check=true;
			bindingResult.rejectValue("username", "memberVO.username.equals");
		}
		
		return check;
		
	}
	
	
}
