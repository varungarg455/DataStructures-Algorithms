/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to create the nodes for 
 * stacks/queues/linked lists. It contains a data column and one 
 * pointers for next node.
 * This is implemented as generic class so that we can put any type of
 * data/object inside this node.
  
******************************************************************************/

package org.varun.garg.nodes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LinkedListNode<T> {

	private T data;
	private LinkedListNode<T> nextNode;
	
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
	
	public LinkedListNode() {
		super();
	}
	
	public LinkedListNode(T data, LinkedListNode<T> nextNode) {
		super();
		this.data = data;
		this.nextNode = nextNode;
	}
	
	public LinkedListNode(T data) {
		super();
		this.data = data;
		this.nextNode = null;
	}		
}
