package com.example.mysite.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.mysite.service.UserService;
import com.example.mysite.vo.UserVo;
import com.example.security.Auth;
import com.example.security.AuthUser;

@Controller
@RequestMapping( "/user" )
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping( value="/join", method=RequestMethod.GET )
	public String join(/*@ModelAttribute UserVo userVo*/ Model model){
		model.addAttribute("userVo", new UserVo());
		return "user/join";
	}
	
	@RequestMapping( value="/join", method=RequestMethod.POST )
	public String join( 
		@ModelAttribute @Valid UserVo userVo,
		BindingResult result,
		Model model){
		
		if(result.hasErrors()) {
//			List<ObjectError> list = result.getAllErrors();
//			for(ObjectError error:list) {
//				System.out.println(error);
//			}
			
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		userService.join( userVo );
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/login";
	}

	@RequestMapping( "/joinsuccess" )
	public String joinsuccess(){
		return "user/joinsuccess";
	}
	
	@Auth(Auth.Role.USER)
	@RequestMapping( value="/modify", method=RequestMethod.GET )
	public String modify( @AuthUser UserVo authUser, Model model ){
		System.out.println( authUser );
		
		UserVo userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "user/modify";
	}
	
	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.POST )
	public String modify( 
		@AuthUser UserVo authUser, 
		@ModelAttribute UserVo userVo ){
		
		userVo.setNo( authUser.getNo() );
		userService.modifyUser( userVo );
		
		// session의 authUser 변경
		authUser.setName(userVo.getName());
		
		return "redirect:/user/modify?result=success";
	}
	
	@RequestMapping( value="/auth", method=RequestMethod.POST )
	public void auth() {}

	@RequestMapping( value="/logout", method=RequestMethod.GET )
	public void logout() {}
}
