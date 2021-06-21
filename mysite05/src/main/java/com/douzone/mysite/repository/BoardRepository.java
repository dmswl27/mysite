package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;


@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {
		System.out.println(vo);
		int count = sqlSession.insert("board.insert", vo);
		return count ==1;
	}


	
	public BoardVo findByNo(Long no) {
		
		return sqlSession.selectOne("board.findByTitleAndContents",no);
	}

	public BoardVo update(Long no) {
		return sqlSession.selectOne("board.update",no);

	}
	public BoardVo update(BoardVo vo) {
		return sqlSession.selectOne("board.update",vo);

	}
	
	public void delete(Long no) {
		sqlSession.delete("board.delete",no);
	}

	public void updatehit(long no) {
		sqlSession.update("board.updatehit", no);
	}

	public void InsetAndComment(BoardVo vo) {
		sqlSession.update("board.InsetAndComment", vo);
		
	}

	public int getBoardCount() {
		return sqlSession.selectOne("board.getBoardCount");
		
	}

	public List<BoardVo> getPager(int startCount, int countBoard) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(startCount +" == " + countBoard);
		map.put("startCount", startCount);
		map.put("countBoard", countBoard);
		return sqlSession.selectList("getPager", map);
	}


	public BoardVo findByTitleAndContents(Long no) {
		return sqlSession.selectOne("board.findByTitleAndContents", no);
		
		
	}


	public void findAll(BoardVo vo) {
		sqlSession.selectList("board.findAll",vo);
		
	}



	public BoardVo findByRow(Long no) {
		return sqlSession.selectOne("board.findByRow",no);
	}



	public void insertAdd(BoardVo v1) {
		sqlSession.insert("board.insertAdd", v1);
		
	}


	



}
