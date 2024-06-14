package com.poscodx.jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;


@Service
public class BoardService {
	private BlogRepository blogRepository;

	public BoardService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	@Transactional
	public void join(String id) {
		blogRepository.insert(id);
		
	}

}
