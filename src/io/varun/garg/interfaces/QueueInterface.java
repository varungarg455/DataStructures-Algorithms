package io.varun.garg.interfaces;

import io.varun.garg.exceptions.QueueEmptyException;
import io.varun.garg.exceptions.QueueFullException;
import io.varun.garg.exceptions.StackEmptyException;
import io.varun.garg.exceptions.StackFullException;

//This is a stack interface with generic data type.
public interface QueueInterface<T>{

	public int size();
	public boolean isEmpty();
	public T front() throws QueueEmptyException;
	
	public void enqueue(T element) throws QueueFullException;
	public T dequeue() throws QueueEmptyException;
	
}
