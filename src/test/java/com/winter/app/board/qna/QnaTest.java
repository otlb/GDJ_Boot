package com.winter.app.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.board.notice.NoticeVO;

class QnaTest {

	@Autowired
	private QnaDAO qnaDAO;

	@Test
	void addTest()throws Exception {
		
		for(Long i =0L; i<120; i++) {
		
		QnaVO qnaVO = new QnaVO();
		
		qnaVO.setBoardUser("tester"+i);
		qnaVO.setBoardName("title"+i);
		qnaVO.setBoardContents("TestContents"+i);
		qnaVO.setBoardRef((long)(i*0.15));
		
		
		
		int result =  qnaDAO.add(qnaVO);
		assertEquals(1, result);
		}
		System.out.println("finish");
	}
}
