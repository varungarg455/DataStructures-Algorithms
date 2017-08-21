/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to create the nodes for heaps. 
 * It contains a data column, a weight column and two pointers, 
 * one for the left node and other for the right node.
 * This is implemented as generic class so that we can put any type of
 * data/object inside this node.
  
******************************************************************************/

package org.varun.garg.nodes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class WeightedBinaryTreeNode<T> {

	private T data;
	private int weight;
	private WeightedBinaryTreeNode<T> leftNode;
	private WeightedBinaryTreeNode<T> rightNode;
	
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
	public WeightedBinaryTreeNode<T> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(WeightedBinaryTreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}
	public WeightedBinaryTreeNode<T> getRightNode() {
		return rightNode;
	}
	public void setRightNode(WeightedBinaryTreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	public WeightedBinaryTreeNode() {
		super();
	}
	
	public WeightedBinaryTreeNode(T data, int weight, WeightedBinaryTreeNode<T> leftNode,
			WeightedBinaryTreeNode<T> rightNode) {
		super();
		this.data = data;
		this.weight = weight;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	public WeightedBinaryTreeNode(T data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
		this.leftNode = null;
		this.rightNode = null;
	}
}
