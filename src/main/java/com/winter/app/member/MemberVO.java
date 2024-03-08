package com.winter.app.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MemberVO {
	
	@NotBlank(message = "입력해주세요")
	private String username;
	
	@NotBlank //비어있으면 안된다 
	@Size(min=8, max=16) 
	private String password;
	private String passwordCheck;
	//@Pattern(regexp="") //패턴 정하기
	private String phone;
	@Email
	private String email;
	
	private String address;
	
	private String name;

}
