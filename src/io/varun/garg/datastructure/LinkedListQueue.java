package io.varun.garg.datastructure;

import io.varun.garg.exceptions.QueueEmptyException;
import io.varun.garg.exceptions.StackFullException;
import io.varun.garg.interfaces.QueueInterface;
import io.varun.garg.support.LinkedListNode;

public class LinkedListQueue<T> implements QueueInterface<T>{

	private LinkedListNode<T> front;
	private LinkedListNode<T> rear;
	
	public LinkedListQueue() {
		super();
		front = null;
		rear = null;
	}

	@Override
	public int size() {
		if(front == null){
			return 0;
		}
		else{
			int i=1;
			LinkedListNode<T> temp = front;
			while(temp!=rear){
				temp = temp.getNextNode();
				i++;
			}
			return i;
		}
	}

	@Override
	public boolean isEmpty() {
		return (front==null);
	}

	@Override
	public T front() throws QueueEmptyException {
		return front.getData();
	}

	@Override
	public void enqueue(T element) throws StackFullException {
		LinkedListNode<T> temp = new LinkedListNode<T>(element);
		if(front == null){
			front = temp;
			rear = temp;
		}
		else{
			rear.setNextNode(temp);
			rear = temp;
		}
	}

	@Override
	public T dequeue() throws QueueEmptyException {
		T value;
		if(front == null){
			throw new QueueEmptyException("Queue is empty");
		}
		else if(front == rear){
			value = front.getData();
			front = null;
			rear = null;
		}
		else{
			LinkedListNode<T> temp = front;
			front = front.getNextNode();
			value = temp.getData();
			temp = null;
		}
		return value;
	}
	
	public void printQueue(){
		if(front == null){
			System.out.println("NULL");
		}
		else{
			LinkedListNode<T> temp = front;
			while(temp!=rear){
				System.out.print(temp.getData() + " -> ");
				temp = temp.getNextNode();
			}
			System.out.print(temp.getData() + " -> NULL");
		}
	}
	
	public static void main(String[] args){
		LinkedListQueue<Integer> queue = new LinkedListQueue<Integer>();
		queue.enqueue(5);
		queue.enqueue(25);
		queue.enqueue(34);
		System.out.println("Size is : " + queue.size());
		queue.printQueue();
		queue.dequeue();
		queue.printQueue();
		
	}

}
