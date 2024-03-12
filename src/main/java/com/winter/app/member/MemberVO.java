package com.winter.app.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class MemberVO implements UserDetails {
	
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
	
	private List<RoleVO> roleVOs;
	
	private boolean accountNonExpired; // 계정의 만료 여부 true 만료 안됨, false 만료됨, 로그인 안됨 
	private boolean accountNonLocked; // 계정 잠김 여부, true: 계정 잠기지 않음, false: 계정 잠김, 로그인 안됨
	private boolean credentialsNonExpired; // 비밀번호 만료 여부, true: 만료 안됨, false: 만료 됨, 로그인 안됨
	private boolean enabled; // 계정 사용 여부, true: 계정 활성화(계정 사용 가능), false: 계정 비활성화 (계정 사용 불가, 로그인 안됨)
	
	@Override //권한(Authority)을 검사할때 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		for(RoleVO roleVO:roleVOs) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleVO.getRoleName());
			authorities.add(g);
		}		
		
		return authorities;
	}
	
	

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	
	

}
