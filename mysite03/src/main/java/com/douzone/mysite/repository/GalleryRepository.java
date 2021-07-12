package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void delete(Long no) {
		sqlSession.delete("gallery.delete",no);
		
	}

	public List<GalleryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
		
	}
	
	public List<GalleryVo> findAll(Long no) {
		return sqlSession.selectList("gallery.findAllByNo", no);
		
	}

	public void insert(String url) {
		sqlSession.insert("gallery.insert",url);
	}

}
