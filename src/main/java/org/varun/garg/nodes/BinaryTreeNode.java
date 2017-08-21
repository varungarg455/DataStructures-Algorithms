/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to create the nodes for Binary Trees.
 * It contains a data column and two pointers, one for the left node and
 * other for the right node.
 * This is implemented as generic class so that we can put any type of
 * data/object inside this node.
  
******************************************************************************/

package org.varun.garg.nodes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BinaryTreeNode<T> {

	private T data;
	private BinaryTreeNode<T> leftNode;
	private BinaryTreeNode<T> rightNode;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public BinaryTreeNode<T> getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(BinaryTreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}
	public BinaryTreeNode<T> getRightNode() {
		return rightNode;
	}
	public void setRightNode(BinaryTreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}
	
	public BinaryTreeNode() {
		super();
	}
	
	public BinaryTreeNode(T data, BinaryTreeNode<T> leftNode, BinaryTreeNode<T> rightNode) {
		super();
		this.data = data;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}
	
	public BinaryTreeNode(T data) {
		super();
		this.data = data;
		this.leftNode = null;
		this.rightNode = null;
	}	
}
