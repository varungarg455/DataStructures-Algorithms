/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to implement the binary search tree(BST).
 * The BST support following functionalities:
 		1. Insert - Used to insert a new element in 
 		2. Pop - Remove an element from stack and return it.
 		3. IsEmpty - Tells if the stack is empty or not.
 * This is implemented as generic class so that we can put any type of
 * data/object inside this BST.
  
******************************************************************************/

package org.varun.garg.datastructures;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.varun.garg.nodes.BinaryTreeNode;

@Component
@Scope("prototype")
public class BinarySearchTree implements ApplicationContextAware{
	
	BinaryTreeNode<Integer> root;
	ApplicationContext context;

	public BinaryTreeNode<Integer> getRoot() {
		return root;
	}
	public void setRoot(BinaryTreeNode<Integer> root) {
		this.root = root;
	}

	public BinarySearchTree() {
		super();
	}
	
	public BinaryTreeNode<Integer> getMaximum(BinaryTreeNode<Integer> root){
		while(root.getRightNode()!=null){
			root = root.getRightNode();
		}
		return root;
	}
	
	public BinaryTreeNode<Integer> getMinimum(BinaryTreeNode<Integer> root){
		while(root.getLeftNode()!=null){
			root = root.getLeftNode();
		}
		return root;
	}	
	
	public void insert(BinaryTreeNode<Integer> root, int data){		
		BinaryTreeNode<Integer> temp = (BinaryTreeNode<Integer>) context.getBean("binaryTreeNode");
		temp.setData(data);		
		while(true){
			if(root == null){
				root = temp;
				this.root = root;
				break;
			}
			else if(data >= root.getData()){
				if(root.getRightNode() == null){
					root.setRightNode(temp);
					break;
				}
				else{
					root = root.getRightNode();
				}
			}
			else{
				if(root.getLeftNode() == null){
					root.setLeftNode(temp);
					break;
				}
				else{
					root = root.getLeftNode();
				}
			}
		}
	}
	
/*	public boolean delete(BinaryTreeNode<Integer> root, int data){
		
		while(root!=null){
			
		//	if()
			
		}
	
	}*/
	
	public boolean search(int data){
		boolean flag = false;
		BinaryTreeNode<Integer> temp = root;
		while(temp!=null){
			if(data == temp.getData()){
				flag = true;
				break;
			}
			else if(data < temp.getData()){
				temp = temp.getLeftNode();
			}
			else{
				temp = temp.getRightNode();
			}
		}
		return flag;
	}
	
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;	
	}
	
}
