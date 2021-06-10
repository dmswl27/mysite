package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.SiteVo;


@Repository
public class AdminRepository {
	@Autowired(required = true)
	private SqlSession sqlSession;
	
	public SiteVo findAll() {
		return sqlSession.selectOne("site.select");
		
	}

	public void update(SiteVo vo) {
		sqlSession.update("site.update",vo);
		
	}

}
