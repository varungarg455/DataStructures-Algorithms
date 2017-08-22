package io.varun.garg.exceptions;

public class StackEmptyException extends RuntimeException{

	private static final long serialVersionUID = -5148230925525799569L;

	public StackEmptyException(String error){
		super(error);
	}		
}
