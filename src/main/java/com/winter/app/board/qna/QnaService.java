package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.util.FileManager;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QnaService implements BoardService {
	
	@Autowired
	private QnaDAO qnaDAO;
	@Value("${app.upload.board.qna}")
	private String uploadPath;
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List<BoardVO> getList(Pager pager) throws Exception {
		pager.makeIndex();
		pager.makeNum(qnaDAO.getTotalCount(pager));
		
		return qnaDAO.getList(pager);
	}

	
	public int setAdd(QnaVO qnaVO, MultipartFile [] attach) throws Exception {
		int result = qnaDAO.setAdd(qnaVO);
		
		for(MultipartFile multipartFile:attach) {
			if(multipartFile.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(uploadPath, multipartFile);
			FileVO fileVO = new FileVO();
			fileVO.setBoardNum(qnaVO.getBoardNum());
			fileVO.setFileName(fileName);
			fileVO.setOriName(multipartFile.getOriginalFilename());
			
			result = qnaDAO.addFile(fileVO);
		}
		
		return result;
	}
	
	@Override
	public BoardVO getDetail(BoardVO boardVO)throws Exception{
		// TODO Auto-generated method stub 
		return qnaDAO.getDetail(boardVO);
	}


	@Override
	public int add(BoardVO boardVO, MultipartFile[] attach) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}