package com.example.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mysite.repository.UserDao;
import com.example.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean existEmail( String email ) {
		UserVo userVo = userDao.get( email );
		return userVo != null;
	}
	
	public void join( UserVo userVo ) {
		//1.DB에 사용정보 저장
		userDao.insert( userVo );
		
		//2. 인증 메일 보내기
	}
	
	public UserVo getUser( String email, String password ) {
		return userDao.get( email, password );
	}
	
	public UserVo getUser( Long no ) {
		return userDao.get( no );
	}
	
	public boolean modifyUser( UserVo userVo ) {
		return userDao.update( userVo ) == 1;
	}
	
}
