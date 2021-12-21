package com.cg.ofda.Exceptions;

public class OrderIdNotFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	public OrderIdNotFoundException() {

	}

	public OrderIdNotFoundException(String message) {
		super(message);
	}
}




