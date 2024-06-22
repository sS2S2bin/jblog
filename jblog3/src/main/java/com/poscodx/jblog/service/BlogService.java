package com.poscodx.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.PostRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;


@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Transactional
	public void join(String id) {
		blogRepository.insert(id);
		categoryRepository.insert(id);
		
	}
	
	/* Blog */
	public BlogVo getBlogContent(String id) {
		return blogRepository.getBlogContent(id);
	}
	
	@Transactional
	public void update(String id, BlogVo blogVo) {
		blogRepository.update(id,blogVo);
	}
	


	/* Post */
	@Transactional
	public void setPost(PostVo postVo) {
		postRepository.setPost(postVo);
	}
	// 포스트 1개 
	public PostVo getContent(String id, Long categoryNo, Long postNo) {
		return blogRepository.getContent(id,categoryNo,postNo);
	}
	// post List
	public List<PostVo> getPostList(Long categoryNo) {
		return postRepository.getPostList(categoryNo);
	}
	
	/* Category */
	
	// category list
	public List<CategoryVo> getCategoryList(String id) {
		return categoryRepository.getCategoryList(id);
	}
	
	public List<CategoryVo> getAdminCategoryList(String id) {
		return categoryRepository.getAdminCategoryList(id);
	}
	
	// 이름과 설명이 적혀있는 카테고리 추가 
	@Transactional
	public void setCategory(CategoryVo categoryVo) {
		categoryRepository.setCategory(categoryVo);
		
	}

}
