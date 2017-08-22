package io.varun.garg.trees;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class BinaryHeap {

	int[] heap;
	int capacity;
	int no_elements;
	
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getNo_elements() {
		return no_elements;
	}
	public void setNo_elements(int no_elements) {
		this.no_elements = no_elements;
	}
	
	public BinaryHeap(int capacity) {
		super();
		this.capacity = capacity;
		this.no_elements = 0;
		heap = new int[this.capacity];
	}
	
	public void insert(int value){
		int parent, index;
		if(no_elements == capacity){
			resizeArray();
		}
		index = no_elements;
		heap[index] = value;
		no_elements += 1;
		percolateUp(index);
	}
	
	public void percolateUp(int index){
		int parent = (index - 1)/2;
		while(parent >= 0 && index>0){
			if(heap[index] > heap[parent]){
				swap(index, parent);
			}
			else{
				break;
			}
			index = parent;
			parent = (index - 1)/2;
		}
	}
	
	public int delete(){
		int index = 0;
		int temp = heap[index];
		heap[index] = heap[no_elements-1];
		no_elements--;
		percolateDown(index);
		return temp;
	}
	
	public void percolateDown(int index){
		int max;
		int leftChild = (2 *  index) + 1;
		int rightChild = (2 *  index) + 2;
		while(leftChild < no_elements && rightChild < no_elements){
			max = findMax(leftChild, rightChild);
			if(heap[index] < heap[max]){
				swap(index, max);
				index = max;
				leftChild = (2 *  index) + 1;
				rightChild = (2 *  index) + 2;
			}
			else{
				break;
			}
		}
	}
	
	public int findMax(int leftChild, int rightChild){
		if(heap[leftChild] < heap[rightChild]){
			return rightChild;
		}
		else{
			return leftChild;
		}
	}
	
	public void swap(int num1, int num2){
		int temp = heap[num1];
		heap[num1] = heap[num2];
		heap[num2] = temp;
	}
	
	public void resizeArray(){
		int capacity_new = this.capacity*2;
		int[] array_new = new int[capacity_new];
		int[] temp = heap;
		for(int i=0; i<heap.length; i++){
			array_new[i] = heap[i]; 
		}
		heap = array_new;
		temp = null;
		this.capacity = capacity_new;
	}
	
	public void printHeap(){
		for (int i=0; i<no_elements; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		BinaryHeap binaryHeap = new BinaryHeap(2);
		binaryHeap.insert(15);
		binaryHeap.insert(10);
		binaryHeap.insert(25);
		binaryHeap.insert(9);
		binaryHeap.insert(5);
		binaryHeap.insert(30);
		binaryHeap.insert(32);
		binaryHeap.insert(34);
		binaryHeap.delete();
		binaryHeap.printHeap();
	}	
}
