package com.poscodx.jblog.service;

import org.springframework.stereotype.Service;

import com.poscodx.jblog.repository.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	public void join(String id) {
		categoryRepository.insert(id);
	}

}
