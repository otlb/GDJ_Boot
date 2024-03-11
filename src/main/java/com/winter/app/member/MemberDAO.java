package com.winter.app.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	public MemberVO getDetail(MemberVO memberVO)throws Exception;
	
	public int add(MemberVO memberVO)throws Exception;	
	
	public int update(MemberVO memberVO)throws Exception;

}
