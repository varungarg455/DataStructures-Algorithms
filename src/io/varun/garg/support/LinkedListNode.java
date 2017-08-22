package io.varun.garg.support;

public class LinkedListNode<T> {
	
	private T data;
	private LinkedListNode<T> nextNode;
	
	public LinkedListNode(T data) {
		super();
		this.data = data;
		this.nextNode = null;
	}
	
	public LinkedListNode(T data, LinkedListNode<T> nextNode) {
		super();
		this.data = data;
		this.nextNode = nextNode;
	}
	
	public LinkedListNode() {
		super();
		// TODO Auto-generated constructor stub
	}



	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public LinkedListNode<T> getNextNode() {
		return nextNode;
	}

	public void setNextNode(LinkedListNode<T> nextNode) {
		this.nextNode = nextNode;
	}	
	
}
