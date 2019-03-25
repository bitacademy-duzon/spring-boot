package com.example.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.mysite.dto.JSONResult;
import com.example.mysite.service.SiteService;
import com.example.mysite.vo.SiteVo;
import com.example.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping( { "/", "/main" } )
	public String index( Model model ) {
		SiteVo siteVo = siteService.getSiteInformation();
		model.addAttribute("site", siteVo);
		return "main/index";
	}
	
	@ResponseBody
	@RequestMapping( "/hello" )
	public String hello() {
		return "<h1>안녕하세요</h1>";
	}
	
	@ResponseBody
	@RequestMapping( "/hello2" )
	public JSONResult hello2() {
		return JSONResult.success(new UserVo());
	}
}