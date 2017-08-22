package io.varun.garg.practice;

import io.varun.garg.support.BinaryTreeNode;
import io.varun.garg.trees.BinaryHeap;
import io.varun.garg.trees.BinarySearchTree;
import io.varun.garg.trees.BinaryTree;

public class LevelOrderSpiralForm {

	public void levelOrderSpiral(BinaryTreeNode root){
		int i=0;
		
		if(root!=null){
			if(i%2==0){
				levelOrderSpiral(root);
			}
		}
	}
	
	public static void main(String[] args) {

		BinaryTree tree = new BinaryTree();
		for(int i=1; i<=7; i++){
			tree.insert(i, tree.getRoot());
		}
		tree.levelOrderTraversal(tree.getRoot());		
	}

}
