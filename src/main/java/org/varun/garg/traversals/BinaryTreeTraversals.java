package org.varun.garg.traversals;

import org.springframework.stereotype.Component;
import org.varun.garg.nodes.BinaryTreeNode;

@Component
public class BinaryTreeTraversals<T> {

	public void inOrderTraversal(BinaryTreeNode<T> root){
		if(root!=null){
			inOrderTraversal(root.getLeftNode());
			System.out.print(root.getData() + " ");
			inOrderTraversal(root.getRightNode());
		}
	}
	
}
