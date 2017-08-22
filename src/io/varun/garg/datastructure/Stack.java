package io.varun.garg.datastructure;

import java.lang.reflect.Array;

import io.varun.garg.exceptions.StackEmptyException;
import io.varun.garg.exceptions.StackFullException;
import io.varun.garg.interfaces.StackInterface;

public class Stack<T> implements StackInterface<T> {

	private int capacity;
	private int top;
	private T[] s;
		
	public Stack(Class<T> type, int capacity) {
		super();
		this.capacity = capacity;
		this.top = -1;
		s = (T[]) Array.newInstance(type, capacity);
	}

	@Override
	public int size() {
		return top+1;
	}

	@Override
	public boolean isEmpty() {
		return (top<0);
	}

	@Override
	public T top() throws StackEmptyException{
		if(isEmpty()){
			throw new StackEmptyException("Stack is currently empty");
		}
		return s[top];
	}

	@Override
	public void push(T element) throws StackFullException{
		if(size()==this.capacity){
			throw new StackFullException("Stack is full");
		}
		s[++top] = element;		
	}

	@Override
	public T pop() throws StackEmptyException{
		if(isEmpty()){
			throw new StackEmptyException("Stack is currently empty");
		}
		return s[top--];
	}
	
	public void printStack(){
		for(int i=0; i<=top; i++) {
			System.out.print(s[i] + " ");
		}
	}
	
	public static void main(String[] args){
		Stack<Integer> arrayStack = new Stack<Integer>(Integer.class, 2);
		try{
			arrayStack.push(10);
			arrayStack.push(15);
			arrayStack.printStack();
			arrayStack.pop();
			arrayStack.printStack();
			System.out.println(arrayStack.isEmpty());
		}catch(StackEmptyException se){
			System.out.println("Exception captured : " + se.getMessage());
		}catch(StackFullException sf){
			System.out.println("Exception captured : " + sf.getMessage());
		};
	}
	
}
