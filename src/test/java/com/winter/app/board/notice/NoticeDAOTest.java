package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;

	//@Test
	void addTest()throws Exception {
		
		for(int i =0; i<120; i++) {
		
		NoticeVO noticeVO = new NoticeVO();
		
		noticeVO.setBoardUser("tester"+i);
		noticeVO.setBoardName("title"+i);
		noticeVO.setBoardContents("TestContents"+i);
		
		int result =  noticeDAO.add(noticeVO);
		assertEquals(1, result);
		}
		System.out.println("finish");
	}
	
	@Test
	void getListTest()throws Exception{
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.makeIndex();
		//totalCount =  noticeDAO.getTotalCount(pager);
		//pager.makeNum(totalCount);
		log.info("pager"+pager);
		
		List<BoardVO> ar =  noticeDAO.getList(pager);
		assertEquals(10, ar.size());
	}

}
