package com.cg.ofda.Exceptions;

public class OrderAlreadyBookedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public OrderAlreadyBookedException(String message) {
		super(message);
		
	}

}
