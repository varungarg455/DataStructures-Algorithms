package io.varun.garg.exceptions;

public class QueueFullException extends RuntimeException{

	private static final long serialVersionUID = -8959028022726337245L;

	public QueueFullException(String error){
		super(error);
	}		
}
