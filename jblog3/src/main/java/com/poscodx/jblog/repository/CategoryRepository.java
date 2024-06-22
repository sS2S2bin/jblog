package com.poscodx.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.CategoryVo;


@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;

	public void insert(String id) {
		sqlSession.insert("category.insert",id);
	}
	
	public List<CategoryVo> getCategoryList(String id) {
		return sqlSession.selectList("category.getCategoryList", id);
	}

	public List<CategoryVo> getAdminCategoryList(String id) {
		return sqlSession.selectList("category.getAdminCategoryList",id);
	}
	
	public void setCategory(CategoryVo categoryVo) {
		sqlSession.insert("category.setCategory",categoryVo);
		
	}
	
}
