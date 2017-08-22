package io.varun.garg.trees;

import io.varun.garg.datastructure.LinkedListQueue;
import io.varun.garg.support.BinaryTreeNode;

public class BinaryTree {

	BinaryTreeNode root;
	BinaryTreeNode current;
	BinaryTreeNode temp;
	
	public BinaryTreeNode getRoot() {
		return root;
	}
	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	public BinaryTreeNode getCurrent() {
		return current;
	}
	public void setCurrent(BinaryTreeNode current) {
		this.current = current;
	}

	public void insert(int data){

		LinkedListQueue<BinaryTreeNode> queue = new LinkedListQueue<BinaryTreeNode>();
		
		temp = new BinaryTreeNode(data);
		
		//Checking if the tree is empty. Then create a new node and set it as root.
		if(root == null){
			root = temp;
			current = root;
		}
		else{
			//If left subchild of the current node is empty, then put new node there
			if(current.getLeft()==null){
				current.setLeft(temp);
			}
			//If right subchild of the current node is empty, then put new node there
			else if(current.getRight()==null){
				current.setRight(temp);
			}
			//If both side of current node is full, then pull a node from the queue and set it as current node.
			else{
				current = queue.dequeue();
				current.setLeft(temp);
			}
			//Put the newly added node in the queue.
			queue.enqueue(temp);
		}
	}	
	
	public void insert(int[] list){			

		LinkedListQueue<BinaryTreeNode> queue = new LinkedListQueue<BinaryTreeNode>();
		
		for(int i=0; i<list.length; i++){
			temp = new BinaryTreeNode(list[i]);
			if(root == null){
				root = temp;
				current = root;
			}
			else{
				if(current.getLeft()==null){
					current.setLeft(temp);
				}
				else if(current.getRight()==null){
					current.setRight(temp);
				}
				else{
					current = queue.dequeue();
					current.setLeft(temp);
				}
				queue.enqueue(temp);
			}
		}
	}
	
	public void insert(int data, BinaryTreeNode root){
		temp = new BinaryTreeNode(data);
		if(root == null){
			root = temp;
			this.root = root;
		}
		else{
			LinkedListQueue<BinaryTreeNode> queue = new LinkedListQueue<BinaryTreeNode>();
			while(true){
				if(root!=null){
					if(root.getLeft() == null){
						root.setLeft(temp);
						break;
					}
					else{
						queue.enqueue(root.getLeft());
					}
					if(root.getRight() == null){
						root.setRight(temp);
						break;
					}
					else{
						queue.enqueue(root.getRight());
					}
				}
				if(queue.isEmpty()){
					break;
				}
				root = queue.dequeue();
			}
		}
	}
	
	public void preOrderTraversal(BinaryTreeNode root){
		if(root!=null){
			System.out.print(root.getData() + " ");
			preOrderTraversal(root.getLeft());
			preOrderTraversal(root.getRight());
		}	
	}	
	
	public void postOrderTraversal(BinaryTreeNode root){
		if(root!=null){
			postOrderTraversal(root.getLeft());
			postOrderTraversal(root.getRight());
			System.out.print(root.getData() + " ");
		}						
	}
	
	public void inOrderTraversal(BinaryTreeNode root){
		if(root!=null){
			inOrderTraversal(root.getLeft());
			System.out.print(root.getData() + " ");
			inOrderTraversal(root.getRight());
		}						
	}
	
	public void levelOrderTraversal(BinaryTreeNode root){
		LinkedListQueue<BinaryTreeNode> queue = new LinkedListQueue<BinaryTreeNode>();
		while(true){
			if(root!=null){
				System.out.print(root.getData() + " ");
				queue.enqueue(root.getLeft());
				queue.enqueue(root.getRight());
			}
			if(queue.isEmpty()){
				break;
			}
			root = queue.dequeue();
		}
	}
	
	public static void main(String[] args) {
		
		BinaryTree binaryTree = new BinaryTree();
		int[] list = {1, 2, 3, 4, 5, 6, 7};
		binaryTree.insert(list);
		
		/*binaryTree.preOrderTraversal(binaryTree.getRoot());
		System.out.print("\n");
		
		binaryTree.postOrderTraversal(binaryTree.getRoot());
		System.out.print("\n");
		
		binaryTree.inOrderTraversal(binaryTree.getRoot());
		System.out.print("\n");
		
		binaryTree.levelOrderTraversal(binaryTree.getRoot());*/
		
		binaryTree.insert(8, binaryTree.getRoot());
		binaryTree.insert(9, binaryTree.getRoot());
	
	    
	    //binaryTree.setRoot(binaryTree.insert(1, binaryTree.getRoot()));
		binaryTree.preOrderTraversal(binaryTree.getRoot());
	}

}
