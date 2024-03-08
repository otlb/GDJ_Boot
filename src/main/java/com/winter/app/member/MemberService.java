package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	public int add(MemberVO memberVO)throws Exception{
			
		return memberDAO.add(memberVO);
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
