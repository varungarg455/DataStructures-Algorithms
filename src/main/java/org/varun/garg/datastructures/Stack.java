/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to implement the stack.
 * The stacks support following functionalities:
 		1. Push - Used to push a new element in the stack.
 		2. Pop - Remove an element from stack and return it.
 		3. IsEmpty - Tells if the stack is empty or not.
 		4. PrintStack - Used to print the stack.
 * This is implemented as generic class so that we can put any type of
 * data/object inside this stack.
  
******************************************************************************/

package org.varun.garg.datastructures;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.varun.garg.nodes.LinkedListNode;

@Component
@Scope("prototype")
public class Stack<T> implements ApplicationContextAware{
	
	private LinkedListNode<T> top;
	private ApplicationContext context;

	public LinkedListNode<T> getTop() {
		return top;
	}
	public void setTop(LinkedListNode<T> top) {
		this.top = top;
	}	
	public ApplicationContext getContext() {
		return context;
	}
	
	public Stack() {
		super();
	}
	
	public void push(T data){
		LinkedListNode<T> temp = (LinkedListNode<T>) getContext().getBean("linkedListNode");
		temp.setData(data);
		temp.setNextNode(null);
		if(isEmpty()){
			top = temp;
		}
		else{
			temp.setNextNode(top);
			top = temp;
		}
	}
	
	public T pop(){
		if(!isEmpty()){
			LinkedListNode<T> temp=null;
			temp = top;
			top = top.getNextNode();
			return temp.getData();
		}
		else{
			return null;
		}
	}
	
	public boolean isEmpty(){
		if(top==null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void printStack(){
		if(isEmpty()){
			System.out.println("Stack is empty");
		}
		else{
			LinkedListNode<T> temp = top;
			System.out.print("(top) ");
			while(temp!=null){
				System.out.print(temp.getData() + " --> ");
				temp = temp.getNextNode();
			}
			System.out.println("NULL");
		}
	}
	
	/*
	 * This function is used as we have implemented 'ApplicationContextAware' interface.
	 * This interface helps us getting the ApplicationContext object.
	 * Using this object we can use getBean() and get the objects of LinkedListNode.
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}	

}
