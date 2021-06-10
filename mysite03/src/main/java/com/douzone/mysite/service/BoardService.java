package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;
	

	public BoardVo getTitleAndContents(Long no) {
		return boardRepository.findByNo(no);
		
	}

	public BoardVo modify(Long no, String title, String contents) {
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		vo.setContents(contents);
		vo.setTitle(title);
		return boardRepository.update(vo);
		
		
	}

	public int getBoardCount() {
		return boardRepository.getBoardCount();
	}

	public List<BoardVo> getPager(int startCount, int countBoard) {
		return boardRepository.getPager(startCount, countBoard);
	}

	public BoardVo view(Long no) {
		return boardRepository.findByTitleAndContents(no);
		
	}

	public void InsetAndComment(BoardVo vo) {
		boardRepository.InsetAndComment(vo);
		
	}

	public Boolean addMessage(BoardVo vo) {
		return boardRepository.insert(vo);
	}

	public void updatemessage(BoardVo vo) {
		boardRepository.update(vo);
		
	}

	public void updatehit(Long no) {
		boardRepository.updatehit(no);
		
	}

	public void delete(Long no) {
		boardRepository.delete(no);
		
	}

	public void findAll(BoardVo vo) {
		boardRepository.findAll(vo);
		
	}

	public BoardVo findByRow(Long no) {
		return boardRepository.findByRow(no);
	}

	public void insertAdd(BoardVo v1) {
		boardRepository.insertAdd(v1);
		
	}

}
