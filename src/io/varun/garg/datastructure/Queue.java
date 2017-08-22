package io.varun.garg.datastructure;

import io.varun.garg.exceptions.QueueEmptyException;
import io.varun.garg.exceptions.QueueFullException;
import io.varun.garg.exceptions.StackEmptyException;
import io.varun.garg.exceptions.StackFullException;
import io.varun.garg.interfaces.QueueInterface;

public class Queue implements QueueInterface<Integer>{

	private int front;
	private int rear;
	private int capacity;
	private int[] s;
	
	public Queue(int capacity) {
		super();
		this.capacity = capacity;
		this.front = -1;
		this.rear = -1;
		s = new int[this.capacity];
	}

	@Override
	public int size() {
		if(front==-1 && rear==-1){
			return 0;
		}
		else if(front==rear){
			return 1;
		}
		else if(front<rear){
			return (rear-front+1);
		}
		else{
			return (capacity-(front-rear-1));
		}
	}

	@Override
	public boolean isEmpty() {
		return (front==-1 || rear==-1);
	}

	@Override
	public Integer front() throws StackEmptyException {
		if(isEmpty()){
			throw new QueueEmptyException("Queue is empty");
		}
		return s[front];
	}

	@Override
	public void enqueue(Integer element) throws StackFullException {
		if(size()==capacity){
			throw new QueueFullException("Queue is full");
		}
		else{
			if(size()==0){
				front=0;
				rear=0;
			}
			else{
				rear = (rear+1) % capacity;
			}
			s[rear]=element;
		}
	}

	@Override
	public Integer dequeue() throws StackEmptyException {
		int element;
		if(size()==0){
			throw new QueueEmptyException("Queue is empty");
		}
		else if(size()==1){
			element = s[front];
			s[front] = 0;
			front = -1;
			rear = -1;
		}		
		else{
			element = s[front];
			s[front] = 0;
			front = (front+1) % capacity;
		}
		return element;
	}
	
	public void printQueue(){
		for(int i=0; i<capacity; i++){
			System.out.print(s[i] + " ");
		}
		System.out.println("\nFront = " + front + "\tRear = " + rear);
	}
	
	public static void main(String[] args){
		
		try{
			Queue queue = new Queue(5);
			queue.enqueue(5);
			queue.printQueue();
			queue.enqueue(10);
			queue.printQueue();
			queue.enqueue(15);
			queue.printQueue();
			queue.enqueue(25);
			queue.printQueue();
			queue.enqueue(35);
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			System.out.println(queue.front());
			queue.printQueue();
			queue.enqueue(11);
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			queue.enqueue(21);
			queue.printQueue();
			queue.enqueue(31);
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
			System.out.println(queue.dequeue());
			queue.printQueue();
		}catch(Exception ex){
			System.out.println("Error captured : " + ex.getMessage());
		}
	}	
}
