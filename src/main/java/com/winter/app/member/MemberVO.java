package com.winter.app.member;

import com.winter.app.member.group.MemberJoinGroup;
import com.winter.app.member.group.MemberUpdateGroup;

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
	
	@NotBlank(message = "입력해주세요",groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String username;
	
	@NotBlank(groups = MemberJoinGroup.class) //비어있으면 안된다 
	@Size(min=8, max=16,groups = MemberJoinGroup.class) 
	private String password;
	private String passwordCheck;
	
	//@Pattern(regexp="") //패턴 정하기
	private String phone;
	@Email(groups = {MemberJoinGroup.class, MemberUpdateGroup.class})
	private String email;
	
	private String address;
	
	private String name;

}
