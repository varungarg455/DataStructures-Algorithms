package io.varun.garg.datastructure;

import io.varun.garg.exceptions.StackEmptyException;
import io.varun.garg.exceptions.StackFullException;
import io.varun.garg.interfaces.StackInterface;
import io.varun.garg.support.LinkedListNode;

public class LinkedListStack<T> implements StackInterface<T>{
		
	private LinkedListNode<T> top;
	
	public LinkedListStack() {
		super();
		top = null;
	}

	@Override
	public int size() {
		LinkedListNode<T> temp;
		if(top == null){
			return 0;
		}
		else{
			int i = 0;
			temp = top;
			while(temp!=null){
				temp = temp.getNextNode();
				i++;
			}
			return i;
		}
	}

	@Override
	public boolean isEmpty() {
		return (top==null);
	}

	@Override
	public T top() throws StackEmptyException {
		return top.getData();
	}

	@Override
	public void push(T element) throws StackFullException {
		LinkedListNode<T> temp = new LinkedListNode<T>(element);
		if(top == null){
			top = temp;
		}
		else{
			temp.setNextNode(top);
			top = temp;
		}
	}

	@Override
	public T pop() throws StackEmptyException {
		if(top == null){
			throw new StackEmptyException("Stack is empty");		}
		else{
			T value = top.getData();
			LinkedListNode<T> temp = top;
			top = top.getNextNode();
			temp = null;
			return value;
		}
	}
	
	public void printStack(){
		if(top == null){
			System.out.print("NULL");
		}
		else{
			LinkedListNode<T> temp = top;
			while(temp!=null){
				System.out.print(temp.getData() + " -> ");
				temp = temp.getNextNode();
			}
			System.out.println("NULL");
		}
	}

	public static void main(String[] args){
	
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
		stack.push(10);
		stack.push(25);
		stack.push(35);
		stack.printStack();
		System.out.println(stack.pop());
		stack.pop();
		stack.printStack();
		
	}
}
