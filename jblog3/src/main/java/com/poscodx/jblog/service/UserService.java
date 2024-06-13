package com.poscodx.jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.BlogRepository;
import com.poscodx.jblog.repository.CategoryRepository;
import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private CategoryRepository categoryRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void join(UserVo vo) {
		userRepository.insert(vo);
		blogRepository.insert(vo.getId());
		categoryRepository.insert(vo.getId());
	}
}
