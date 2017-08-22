package io.varun.garg.support;

public class DisjointSetNode<T> {

	T root;
	int size;
	public T getRoot() {
		return root;
	}
	public void setRoot(T root) {
		this.root = root;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public DisjointSetNode(T root, int size) {
		super();
		this.root = root;
		this.size = size;
	}
	public DisjointSetNode() {
		super();
		// TODO Auto-generated constructor stub
	}
}
