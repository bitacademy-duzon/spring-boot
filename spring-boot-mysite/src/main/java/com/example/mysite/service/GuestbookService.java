package com.example.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mysite.repository.GuestbookDao;
import com.example.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	public List<GuestbookVo> getMessageList() {
		return guestbookDao.getList();
	}
	
	public List<GuestbookVo> getMessageList( Long startNo ) {
		return guestbookDao.getList( startNo );
	}	
	
	public boolean deleteMessage( GuestbookVo vo ){
		return 1 == guestbookDao.delete( vo );
	}
	
	public boolean writeMessage( GuestbookVo vo ) {
		int count = guestbookDao.insert(vo);
		return count == 1;
	}
}