package com.poscodx.jblog.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
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

	public List<CategoryVo> getCategoryList(String id) {
		return sqlSession.selectList("blog.getCategoryList", id);
	}

	public Long getMinCategory(String id) {
		return sqlSession.selectOne("blog.getMinCategory", id);
		
	}

	public Long getMinPost(Long categoryNo) {
		return sqlSession.selectOne("blog.getMinPost", categoryNo);
	}

	public List<PostVo> getPostList(Long categoryNo) {
		return sqlSession.selectList("blog.getPostList", categoryNo);
	}

	public BlogVo getBlogContent(String id) {
		return sqlSession.selectOne("blog.getBlogContent",id);
	}

}
