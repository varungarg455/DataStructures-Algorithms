package io.varun.garg.support;

public class WeightedGraphNode<T> {

	private T data;
	private WeightedGraphNode<T> nextNode;
	private int weight;
		
	public WeightedGraphNode() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public WeightedGraphNode(T data, int weight) {
		super();
		this.data = data;
		this.weight = weight;
		this.nextNode = null;
	}
	
	public WeightedGraphNode(T data, int weight, WeightedGraphNode<T> nextNode) {
		super();
		this.data = data;
		this.weight = weight;
		this.nextNode = nextNode;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public WeightedGraphNode<T> getNextNode() {
		return nextNode;
	}
	public void setNextNode(WeightedGraphNode<T> nextNode) {
		this.nextNode = nextNode;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
