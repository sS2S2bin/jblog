package com.poscodx.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	private SqlSession sqlsession;
	
	
	public void insert(UserVo vo) {
		sqlsession.insert("user.insert",vo);	
	}

}
