package io.varun.garg.interfaces;

import io.varun.garg.exceptions.LinkedListException;

public interface LinkedListInterface<T> {

	public void insertAtStart(T element);
	public void insertAtEnd(T element);
	public void insertAtPos(T element, int position) throws LinkedListException;
		
	public T deleteAtStart() throws LinkedListException;
	public T deleteAtEnd() throws LinkedListException;
	public T deleteAtPos(int position) throws LinkedListException;	
	
	public int search(T element) throws LinkedListException;
	
	
	
}
