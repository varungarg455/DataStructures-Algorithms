/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to implement the queue.
 * The queue support following functionalities:
 		1. Add - Used to add a new element from the rear.
 		2. Remove - Remove an element from front and return it.
 		3. IsEmpty - Tells if the queue is empty or not.
 		4. PrintStack - Used to print the queue.
 * This is implemented as generic class so that we can put any type of
 * data/object inside this queue.
  
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
public class Queue<T> implements ApplicationContextAware{
	
	private LinkedListNode<T> front;
	private LinkedListNode<T> rear;	
	private ApplicationContext context;
	
	public LinkedListNode<T> getFront() {
		return front;
	}
	public void setFront(LinkedListNode<T> front) {
		this.front = front;
	}
	public LinkedListNode<T> getRear() {
		return rear;
	}
	public void setRear(LinkedListNode<T> rear) {
		this.rear = rear;
	}
	public ApplicationContext getContext() {
		return context;
	}
	
	public Queue() {
		super();
	}
	
	public void add(T data){
		LinkedListNode<T> temp = (LinkedListNode<T>) getContext().getBean("linkedListNode");
		temp.setData(data);
		temp.setNextNode(null);
		if(isEmpty()){
			front = temp;
			rear = temp;
		}
		else{
			rear.setNextNode(temp);
			rear = temp;
		}
	}
	
	public T remove(){
		if(!isEmpty()){
			LinkedListNode<T> temp=null;
			temp = front;
			front = front.getNextNode();
			return temp.getData();
		}
		else{
			return null;
		}
	}
		
	public boolean isEmpty(){
		if(rear==null || front==null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void printQueue(){
		if(isEmpty()){
			System.out.println("Queue is empty");
		}
		else{
			LinkedListNode<T> temp = front;
			System.out.print("(front) ");
			while(temp!=null){
				System.out.print(temp.getData() + " --> ");
				temp = temp.getNextNode();
			}
			System.out.println("NULL (rear)");
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
