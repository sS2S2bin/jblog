package com.poscodx.jblog.repository;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert",vo);	
	}


	public UserVo getUser(String id, String password) {
		return sqlSession.selectOne("user.getUser", Map.of("id",id,"password",password));
	}


	public Long checkId(String id) {
		return sqlSession.selectOne("user.checkId", id);
	}


}
