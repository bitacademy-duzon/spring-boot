package com.example.mysite.exception;

public class UserDaoException extends RuntimeException {
	private static final long serialVersionUID = 1750460045975550896L;

	public UserDaoException() {
		super( "UserDao Exception Occurs" );
	}
	
	public UserDaoException( String message ) {
		super( message );
	}
}
