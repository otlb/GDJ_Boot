package com.winter.app.board;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Setter
@Getter
public class BoardVO {
	
	private Long boardNum;
	private String boardUser;
	private String boardName;
	private String boardContents;
	private Date boardDate;
	private Long boardHits;
	private List<FileVO> fileVOs;
}
