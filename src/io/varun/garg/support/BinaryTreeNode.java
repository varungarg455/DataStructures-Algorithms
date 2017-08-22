package io.varun.garg.support;

public class BinaryTreeNode{
	
	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	public BinaryTreeNode(int data) {
		super();
		this.data = data;
		this.left = null;
		this.right = null;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public BinaryTreeNode getLeft() {
		return left;
	}
	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}
	public BinaryTreeNode getRight() {
		return right;
	}
	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}	
	
	public int heightLeft(BinaryTreeNode root){
		int count = 1;
		while(root.getLeft()!=null){
			root = root.getLeft();
			count++;
		}
		return count;
	}
	
}
