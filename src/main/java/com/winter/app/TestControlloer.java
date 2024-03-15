package com.winter.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winter.app.lamdba.TestInterface;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestControlloer {
	
	@GetMapping("/")
	public String test() {
		//람다는 js와 비슷하다 function (){} => ()->{}
		TestInterface ti = (int a,int b) -> a+b;		
		System.out.println(ti.t1(0, 0));
		
		return "Index";
	}
	
	@GetMapping("/expired")
	public String expried(Model model) {
		model.addAttribute("result", "로그아웃");
		model.addAttribute("path", "/");
		return "commons/result";
	}

}
