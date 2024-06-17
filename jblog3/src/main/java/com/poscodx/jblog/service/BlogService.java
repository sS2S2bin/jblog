package com.poscodx.jblog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;


@Service
public class BlogService {
	private BlogRepository blogRepository;

	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	@Transactional
	public void join(String id) {
		blogRepository.insert(id);
		
	}

	// 포스트 1개 
	public PostVo getContent(String id, Long categoryNo, Long postNo) {
		return blogRepository.getContent(id,categoryNo,postNo);
	}

	// category list
	public List<CategoryVo> getCategoryList(String id) {
		return blogRepository.getCategoryList(id);
	}

	// post List
	public List<PostVo> getPostList(String id, Long categoryNo) {
		return blogRepository.getPostList(categoryNo);
	}

	public BlogVo getBlogContent(String id) {
		return blogRepository.getBlogContent(id);
	}

//	// min category
//	public Long getMinCategory(String id) {
//		return blogRepository.getMinCategory(id);
//	}
//
//	public Long getMinPost(Long categoryNo) {
//		return blogRepository.getMinPost(categoryNo);
//	}

}
