package com.example.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mysite.repository.SiteDao;
import com.example.mysite.vo.SiteVo;

@Service
public class SiteService {
	
	@Autowired
	private SiteDao siteDao;
	
	public SiteVo getSiteInformation() {
		return siteDao.get();
	}
	
	public boolean updateSiteInformation( SiteVo siteVo ) {
		return siteDao.update( siteVo ) == 1;
	}
}
