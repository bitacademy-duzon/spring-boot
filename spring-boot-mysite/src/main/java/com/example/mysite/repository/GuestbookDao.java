package com.example.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.mysite.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> getList() {
		List<GuestbookVo> list = sqlSession.selectList( "guestbook.getList" );
		return list;
	}
	
	public List<GuestbookVo> getList( Long startNo ) {
		List<GuestbookVo> list = sqlSession.selectList( "guestbook.getList2", startNo );
		System.out.println( list );
		return list;
	}	
	
	public int delete( GuestbookVo vo ) {
		int count = sqlSession.delete( "guestbook.delete", vo );
		return count;
	}
	
	public int insert( GuestbookVo vo ) {
		return sqlSession.insert( "guestbook.insert", vo );
	}
}
