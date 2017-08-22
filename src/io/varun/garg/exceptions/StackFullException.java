package io.varun.garg.exceptions;

public class StackFullException extends RuntimeException{

	private static final long serialVersionUID = 1993707949749961206L;

	public StackFullException(String error){
		super(error);
	}		
}
