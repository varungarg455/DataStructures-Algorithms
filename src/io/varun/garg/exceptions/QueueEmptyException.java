package io.varun.garg.exceptions;

public class QueueEmptyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4775777312479512221L;

	public QueueEmptyException(String error){
		super(error);
	}		
}
