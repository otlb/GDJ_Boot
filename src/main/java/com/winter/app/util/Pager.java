package com.winter.app.util;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pager {
	
	private Long page;// 페이지 번호
	private Long perPage ;//한 페이지당 몇개 
	
	private Long startIndex;
	
	private Long totalPage;
	
	private Long startNum;
	private Long lastNum;	
	
	private boolean start;
	private boolean last;
	
	private String search;
	private String kind;
	
	public void MakeIendex(){
		this.startIndex = (this.getPage()-1)*this.getPage();
		
	}
	
	public void makeNum(Long totalCount)throws Exception{
		//검색결과가 없을때 페이지 번호 1로하기  
		if(totalCount<1) {
			totalCount=1L;
		}
		//총페이지 
		totalPage = totalCount/this.getPerPage();
		if(totalCount%this.getPerPage() !=0) {
			totalPage++;
		}
		this.setTotalPage(totalPage);
		
		//총페이지로 총 블럭 구하기
		Long perBlock = 5L;
		Long totalBlock = totalPage/perBlock;
		if(totalPage%perBlock !=0) {
			totalBlock++;
		}
		
		//현재페이지로 현재 블럭
		Long curBlock = this.getPage()/perBlock;
		if(this.getPage()%perBlock !=0) {
			curBlock++;
		}
		
		//현재블럭번호로 시작번호 끝번호
		lastNum = curBlock*perBlock;
		startNum = lastNum-perBlock+1;
		
		this.setLastNum(lastNum);
		this.setStartNum(startNum);
		//이전유무
		if(curBlock == 1) {
			this.setStart(true);
		}
		
		if(curBlock == totalBlock) {
			this.setLast(true);
			this.setLastNum(totalPage);
		}
		
		
		
	}
	
	//getter
	// public 리턴타입 get멤버변수명(){}
	public Long getPage() {
		if(this.page ==null || this.page<1) {
			this.page = 1L;
		}				
		return this.page;
	}
	
	public Long getPerPage() {
		if(this.perPage == null || this.perPage<1) {
			this.perPage = 10L;
		}
		return this.perPage;
	}
	public String getSearch() {
		if(this.search == null ) {
			this.search = "";
		}
		return this.search;
	}

}
