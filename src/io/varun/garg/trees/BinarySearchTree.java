package io.varun.garg.trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.varun.garg.datastructure.LinkedListQueue;
import io.varun.garg.datastructure.LinkedListStack;
import io.varun.garg.support.BinaryTreeNode;

public class BinarySearchTree {

	BinaryTreeNode root;
	BinaryTreeNode temp;
	
	public BinaryTreeNode getRoot() {
		return root;
	}
	public void setRoot(BinaryTreeNode root) {
		this.root = root;
	}
	public BinarySearchTree() {
		super();
		root = null;
		temp = null;
	}
	
	public int height(BinaryTreeNode root){	
		int leftHeight, rightHeight;
		
		if(root == null){
			return -1;
		}
		leftHeight = height(root.getLeft());
		rightHeight = height(root.getRight());
		
		if(leftHeight > rightHeight){
			return leftHeight + 1;
		}
		else{
			return rightHeight + 1;
		}				
	}
	
	public BinaryTreeNode insert(int data, BinaryTreeNode root){
		temp = new BinaryTreeNode(data);

		if(root == null){
			root = temp;
			this.root = root;
		}
		else if(data < root.getData()){
			if(root.getLeft() != null){
				insert(data, root.getLeft());
			}
			else{
				root.setLeft(temp);
			}
		}
		else{
			if(root.getRight() != null){
				insert(data, root.getRight());
			}
			else{
				root.setRight(temp);
			}
		}	
		return root;
	}
	
	public BinaryTreeNode getMaximum(BinaryTreeNode root){
		
		while(root.getRight() != null){
			root = root.getRight();
		}
		return root;		
	}
	
	public BinaryTreeNode getMinimum(BinaryTreeNode root){
		
		while(root.getLeft() != null){
			root = root.getLeft();
		}
		return root;		
	}
	
	public BinaryTreeNode search(int value, BinaryTreeNode root){
		
		if(value == root.getData()){
			return root;
		}
		else if(value < root.getData()){
			return search(value, root.getLeft());
		}
		else if(value > root.getData()){
			return search(value, root.getRight());
		}		
		return null;
	}
	
	public BinaryTreeNode delete(int value, BinaryTreeNode root){
		
		BinaryTreeNode temp;
		if(root == null){
			System.out.println("Element not there in tree");
		}
		else if(value < root.getData()){
			root.setLeft(delete(value, root.getLeft()));
		}
		else if(value > root.getData()){
			root.setRight(delete(value, root.getRight()));
		}
		else{
			//Both the nodes are non null.
			if(root.getLeft()!=null && root.getRight()!=null){
				temp = getMaximum(root.getLeft());
				root.setData(temp.getData());
				root.setLeft(delete(temp.getData(), root.getLeft()));
			}
			//Only one node is non empty or both nodes are empty.
			else{
				temp = root;
				if(root.getLeft() == null){
					root = root.getRight();
				}
				else if(root.getRight() == null){
					root = root.getLeft();
				}
				temp = null;
			}
		}		
		return root;		
	}
	
	public void preOrderTraversal(BinaryTreeNode root){
		if(root!=null){
			System.out.print(root.getData() + " ");
			preOrderTraversal(root.getLeft());
			preOrderTraversal(root.getRight());
		}	
	}	
	
	//Iterative solution of preorder traversal
	public void preOrderTraversal_iterative(BinaryTreeNode root){
		System.out.print("Iterative pre-order traversal --> ");
		List<Integer> visited = new ArrayList<>();
		LinkedListStack<BinaryTreeNode> stack = new LinkedListStack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			root = stack.pop();
			//If root node is not visited, process it and add the root element to visited list
			if(!visited.contains(root.getData())){
				if(root.getRight()!=null){
					stack.push(root.getRight());
				}
				if(root.getLeft()!=null){
					stack.push(root.getLeft());
				}
				stack.push(root);
				visited.add(root.getData());
			}
			//If root is already visited, then just print it
			else{
				System.out.print(root.getData() + " ");
			}
		}
	}	
	
	public void postOrderTraversal(BinaryTreeNode root){
		if(root!=null){
			postOrderTraversal(root.getLeft());
			postOrderTraversal(root.getRight());
			System.out.print(root.getData() + " ");
		}						
	}
	
	//Iterative solution of postorder traversal
	public void postOrderTraversal_iterative(BinaryTreeNode root){
		System.out.print("Iterative post-order traversal --> ");
		List<Integer> visited = new ArrayList<>();
		LinkedListStack<BinaryTreeNode> stack = new LinkedListStack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			root = stack.pop();
			//If root node is not visited, process it and add the root element to visited list
			if(!visited.contains(root.getData())){
				stack.push(root);
				if(root.getRight()!=null){
					stack.push(root.getRight());
				}
				if(root.getLeft()!=null){
					stack.push(root.getLeft());
				}
				visited.add(root.getData());
			}
			//If root is already visited, then just print it
			else{
				System.out.print(root.getData() + " ");
			}
		}
	}	
	
	public void inOrderTraversal(BinaryTreeNode root){
		if(root!=null){
			inOrderTraversal(root.getLeft());
			System.out.print(root.getData() + " ");
			inOrderTraversal(root.getRight());
		}						
	}
		
	public void inOrderTraversal_iterative(BinaryTreeNode root){
		System.out.print("Itereative in-order traversal --> ");
		List<Integer> visited = new ArrayList<>();
		LinkedListStack<BinaryTreeNode> stack = new LinkedListStack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			root = stack.pop();
			//If root node is not visited, process it and add the root element to visited list
			if(!visited.contains(root.getData())){
				if(root.getRight()!=null){
					stack.push(root.getRight());
				}
				stack.push(root);
				if(root.getLeft()!=null){
					stack.push(root.getLeft());
				}
				visited.add(root.getData());
			}
			//If root is already visited, then just print it
			else{
				System.out.print(root.getData() + " ");
			}
		}
	}
	
	public void levelOrderTraversal(BinaryTreeNode root){
		System.out.print("Iterative level-order traversal --> ");
		BinaryTreeNode left, right;
		LinkedListQueue<BinaryTreeNode> queue = new LinkedListQueue<BinaryTreeNode>();
		queue.enqueue(root);
		while(!queue.isEmpty()){
			root = queue.dequeue();
			left = root.getLeft();
			right = root.getRight();
			System.out.print(root.getData() + " ");
			if(left!=null){
				queue.enqueue(root.getLeft());
			}
			if(right!=null){
				queue.enqueue(root.getRight());
			}
		}
	}
	
	public static void main(String[] args) {
				
		BinarySearchTree tree = new BinarySearchTree();
		tree.insert(10, tree.getRoot());
		tree.insert(15, tree.getRoot());
		tree.insert(1, tree.getRoot());
		tree.insert(4, tree.getRoot());
		tree.insert(5, tree.getRoot());
		tree.insert(2, tree.getRoot());
		tree.insert(45, tree.getRoot());
		tree.insert(13, tree.getRoot());
		
/*		tree.inOrderTraversal(tree.getRoot());
		System.out.println("\n");
		tree.levelOrderTraversal(tree.getRoot());
		System.out.println("\n");
		tree.preOrderTraversal(tree.getRoot());
		System.out.println("\n");
	
		try{
			System.out.println(tree.search(45, tree.getRoot()).getData());
		}catch(NullPointerException ex){
			System.out.println("Node not found");
		};
		
		System.out.println("Maximum is " + tree.getMaximum(tree.getRoot()).getData());
		System.out.println("Minimum is " + tree.getMinimum(tree.getRoot()).getData());
		
		tree.delete(45, tree.getRoot());
		tree.levelOrderTraversal(tree.getRoot());
		System.out.println();*/
		tree.preOrderTraversal_iterative(tree.getRoot());
		System.out.println();
		tree.inOrderTraversal_iterative(tree.getRoot());
		System.out.println();
		tree.postOrderTraversal_iterative(tree.getRoot());
		System.out.println();
		tree.levelOrderTraversal(tree.getRoot());
		System.out.println();
		
		
	}
	
}
