package com.example.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.example.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog( GlobalExceptionHandler.class );

	@ExceptionHandler( NoHandlerFoundException.class )
	public void handleNoHandlerFoundException(
		HttpServletRequest request, 
		HttpServletResponse response,
		NoHandlerFoundException ex) throws ServletException, IOException {
		System.out.println("handleNoHandlerFoundException!!!!!!!!!");
		request.
		getRequestDispatcher("/WEB-INF/views/error/404.jsp").
		forward(request, response);
	}

	@ExceptionHandler( Exception.class )
	public void handleException( 
		HttpServletRequest request, 
		HttpServletResponse response,
		Exception e ) throws Exception {
		
		//1. 로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace( new PrintWriter( errors ) );
		LOGGER.error(errors.toString());
		
		//2. 안내페이지 가기
//		ModelAndView mav = new ModelAndView();
//		mav.addObject( "uri", request.getRequestURI() );
//		mav.addObject( "exception", errors.toString() );
//		mav.setViewName( "error/exception" );
//		return mav;
		
		String accept = request.getHeader("accept");
		if( accept.matches(".*application/json.*") ) { // JSON 응답
			response.setStatus(HttpServletResponse.SC_OK);
			
			OutputStream out = response.getOutputStream();
			out.write(new ObjectMapper().writeValueAsString(JSONResult.fail(errors.toString())).getBytes("utf-8"));
			out.flush();
			out.close();
			
		} else { // HTML 응답
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.
			getRequestDispatcher("/WEB-INF/views/error/exception.jsp").
			forward(request, response);
			
		}
		
	}
	
	
}