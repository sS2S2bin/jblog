package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<PostVo> getPostList(Long categoryNo) {
		return sqlSession.selectList("blog.getPostList", categoryNo);
	}

	public Object setPost(PostVo postVo) {
		return sqlSession.insert("blog.setPost",postVo);
	}

}
