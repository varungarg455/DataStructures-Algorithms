package io.varun.garg.interfaces;

import io.varun.garg.exceptions.StackEmptyException;
import io.varun.garg.exceptions.StackFullException;

//This is a stack interface with generic data type.
public interface StackInterface<T>{

	public int size();
	public boolean isEmpty();
	public T top() throws StackEmptyException;
	
	public void push(T element) throws StackFullException;
	public T pop() throws StackEmptyException;
	
}
