package io.varun.garg.trees;

import io.varun.garg.datastructure.LinkedListQueue;
import io.varun.garg.support.BinaryTreeNode;

public class AVLTree {

	BinaryTreeNode root;
	BinaryTreeNode temp;
	
	public BinaryTreeNode getRoot() {
		return root;
	}

	public void setRoot(BinaryTreeNode root) {
		this.root = root; 
	}

	public BinaryTreeNode insert(int value, BinaryTreeNode root){
		
		if(root == null){
			temp = new BinaryTreeNode(value);
			root = temp;
		}
		else if(value<root.getData()){
			root.setLeft(insert(value, root.getLeft()));
			if(height(root.getLeft()) - height(root.getRight()) == 2){
				if(value < root.getLeft().getData()){
					root = SingleRotationLeft(root);
				}
				else{
					root = DoubleRotationLeft(root);
				}
			}
			
		}
		else{
			root.setRight(insert(value, root.getRight()));
			if(height(root.getRight()) - height(root.getLeft()) == 2){
				if(value > root.getRight().getData()){
					root = SingleRotationRight(root);
				}
				else{
					root = DoubleRotationRight(root);
				}
			}
		}
		return root;
	}
	
	public BinaryTreeNode delete(int value, BinaryTreeNode root){
		
		if(root==null){
			System.out.println("Element not there in tree");
		}
		else if(value < root.getData()){
			root.setLeft(delete(value, root.getLeft()));
			if(height(root.getRight()) - height(root.getLeft()) == 2){
				if(root.getRight().getRight() != null){
					root = SingleRotationRight(root);
				}
				else{
					root = DoubleRotationRight(root);
				}
			}
		}
		else if(value > root.getData()){
			root.setRight(delete(value,  root.getRight()));
			if(height(root.getLeft()) - height(root.getRight()) == 2){
				if(root.getLeft().getLeft() != null){
					root = SingleRotationLeft(root);
				}
				else{
					root = DoubleRotationLeft(root);
				}
			}
		}
		else{
			if(root.getLeft() != null && root.getRight() != null){
				temp = getMaximum(root.getLeft());
				root.setData(temp.getData());
				root.setLeft(delete(temp.getData(), root.getLeft()));
			}
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
	
	public BinaryTreeNode SingleRotationLeft(BinaryTreeNode node){
		temp = node.getLeft();
		node.setLeft(temp.getRight());
		temp.setRight(root);
		return temp;
	}
	
	public BinaryTreeNode SingleRotationRight(BinaryTreeNode node){
		temp = node.getRight();
		node.setRight(temp.getLeft());
		temp.setLeft(node);
		return temp;
	}
	
	public BinaryTreeNode DoubleRotationLeft(BinaryTreeNode node){
		node.setLeft(SingleRotationRight(node.getLeft()));
		return SingleRotationLeft(node);
	}
	
	public BinaryTreeNode DoubleRotationRight(BinaryTreeNode node){
		node.setRight(SingleRotationLeft(node.getRight()));
		return SingleRotationRight(node);
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
	
	public static void main(String[] args){
		
		AVLTree tree = new AVLTree();
		tree.setRoot(tree.insert(10, tree.getRoot()));
		tree.setRoot(tree.insert(15, tree.getRoot()));
		tree.setRoot(tree.insert(1, tree.getRoot()));
		tree.setRoot(tree.insert(4, tree.getRoot()));
		tree.setRoot(tree.insert(5, tree.getRoot()));
		tree.setRoot(tree.insert(2, tree.getRoot()));
		
		tree.setRoot(tree.insert(45, tree.getRoot()));
		
		tree.setRoot(tree.insert(13, tree.getRoot()));

		tree.setRoot(tree.insert(50, tree.getRoot()));

		tree.setRoot(tree.insert(55, tree.getRoot()));
		
		tree.setRoot(tree.delete(2, tree.getRoot()));
		
		/*tree.setRoot(tree.delete(13, tree.getRoot()));
		tree.setRoot(tree.insert(14, tree.getRoot()));
		tree.setRoot(tree.delete(14, tree.getRoot()));
		//tree.setRoot(tree.delete(1, tree.getRoot()));
*/		
		System.out.println();
		tree.levelOrderTraversal(tree.getRoot());
	}
	
}
