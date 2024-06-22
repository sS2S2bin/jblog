package com.poscodx.jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.PostVo;


@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public void insert(String id) {
		sqlSession.insert("blog.insert",id);
	}

	public PostVo getContent(String id, Long categoryNo, Long postNo) {
		return sqlSession.selectOne("blog.getContent", Map.of("id",id,"categoryNo",categoryNo, "postNo",postNo));
	}

	public BlogVo getBlogContent(String id) {
		return sqlSession.selectOne("blog.getBlogContent",id);
	}
	
	public void update(String id, BlogVo blogVo) {
		sqlSession.update("blog.update",Map.of("id",id,"blogVo",blogVo));
	}


}
