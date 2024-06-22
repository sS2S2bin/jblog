package com.poscodx.jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poscodx.jblog.repository.UserRepository;
import com.poscodx.jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public void join(UserVo vo) {
		userRepository.insert(vo);
		
	}

	public UserVo getUser(String id, String password) {
		return userRepository.getUser(id,password);
	}

	public boolean checkId(String id) {
		if(userRepository.checkId(id)>0) {
			return true;
		}else {
			return false;
		}
		// return userRepository.checkId(id)>0?false:true ;
		
	}


}
