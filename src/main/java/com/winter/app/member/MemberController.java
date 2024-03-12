package com.winter.app.member;

import java.util.Enumeration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.member.group.MemberJoinGroup;
import com.winter.app.member.group.MemberUpdateGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("page")
	public void page(HttpSession session)throws Exception{
		
		  //속성명 확인
		  Enumeration<String> en = session.getAttributeNames();
		  
		  while(en.hasMoreElements()) {
			  log.info("===={}====",en.nextElement());
		  }
		  //확인한 속성명으로 오브젝트타입에 넣고 안에 어떤 데이터가 들어있는지 확인 
		  Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		  log.info("========{}=========",obj);
		  
		  //확인된 데이터에 맞는 데이터타입으로 변수에 넣고 원하는 데이터 출력 
		  SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		  String name =  contextImpl.getAuthentication().getName();
		  MemberVO memberVO = (MemberVO)contextImpl.getAuthentication().getPrincipal();
		  
		  log.info("====Name{}====",name);
		  log.info("====membervo{}====",memberVO);
		  
		  
		  //2번째 방법 
		  //SecurityContext context = SecurityContextHolder.getContext();
		  //MemberVO memberVO2 = (MemberVO)context.getAuthentication().getPrincipal();
		  
		  
	}	
	
	@GetMapping("login")
	public String login(@ModelAttribute MemberVO memberVO,HttpSession session)throws Exception{
		
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if(obj == null) {
			return "member/login";
		}
		SecurityContextImpl contextImpl = (SecurityContextImpl)obj;
		String user = contextImpl.getAuthentication().getPrincipal().toString();
		if(user.equals("Authentication")) {
			return "member/login";
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("update")
	public void update(Model model)throws Exception{
		
		
	}
	
	@PostMapping("update")
	public String update(@Validated(MemberUpdateGroup.class) MemberVO memberVO,BindingResult bindingResult)throws Exception{
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		return "redirect:../";
	}	
	
	@GetMapping("add")
	public void add(@ModelAttribute MemberVO memberVO) throws Exception{		
		//model.addAttribute("dto",memberVO) 랑 같음 		
	}
	
	@PostMapping("add")// valid 선언 후 바로 bindigResult를 써줘야함 중간에 다른 매개변수 들어가면 안됨
	public String add(@Validated(MemberJoinGroup.class) MemberVO memberVO, BindingResult bindingResult,Model model) throws Exception{
		
		boolean check = memberService.checkMember(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
		
		int result = memberService.add(memberVO);
		model.addAttribute("result", "member.add.result");
		model.addAttribute("path", "/");
		
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}
		
		//service로 보냄
		
		return "commons/result";
	}
	
}
