/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to create the nodes for weighted graphs. 
 * It contains a data column, a weight column and one pointer for the next node
 * This is implemented as generic class so that we can put any type of
 * data/object inside this node.
  
******************************************************************************/

package org.varun.garg.nodes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WeightedLinkedListNode<T> {

	private T data;
	private int weight;
	private WeightedLinkedListNode<T> nextNode;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public WeightedLinkedListNode<T> getNextNode() {
		return nextNode;
	}
	public void setNextNode(WeightedLinkedListNode<T> nextNode) {
		this.nextNode = nextNode;
	}
	
	public WeightedLinkedListNode() {
		super();
	}
	
	public WeightedLinkedListNode(T data, int weight, WeightedLinkedListNode<T> nextNode) {
		super();
		this.data = data;
		this.weight = weight;
		this.nextNode = nextNode;
	}
	
	public WeightedLinkedListNode(T data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
		this.nextNode = null;
	}	
}
