package com.example.mysite.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mysite.exception.UserDaoException;
import com.example.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo get( String email ) {
		return sqlSession.selectOne( "user.getByEmail", email );
	}
	
	public UserVo get( Long no ){
		return sqlSession.selectOne( "user.getByNo", no );
	}
	
	public UserVo get( String email, String password )  throws UserDaoException {
		Map<String, String> map = new HashMap<String, String>();
		map.put( "email", email );
		map.put( "password", password );
		return sqlSession.selectOne( "user.getByEmailAndPassword", map );
	}
	
	public int update( UserVo userVo ) {
		return sqlSession.update( "user.update", userVo );
	}
	
	public int insert( UserVo userVo ) {
		return sqlSession.insert( "user.insert", userVo );
	}
}
