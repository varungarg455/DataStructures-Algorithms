package io.varun.garg.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinBinaryHeap<T> {
	
	class Heap<T> {
		T key;
		int value;
		public T getKey() {
			return key;
		}
		public void setKey(T key) {
			this.key = key;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public Heap() {
			
		}
		public Heap(T key, int value) {
			super();
			this.key = key;
			this.value = value;
		}			
	}
	
	//mapping variable helps in mapping the inidex of each node so that we can implement contains and update functions
	Map<T, Integer> mapping = new HashMap<>();
	List<Heap<T>> heap;
	int no_elements;
	
	public int getNo_elements() {
		return no_elements;
	}
	public void setNo_elements(int no_elements) {
		this.no_elements = no_elements;
	}
	
	public MinBinaryHeap() {
		super();
		this.no_elements = 0;
		heap = new ArrayList<>();
	}
	
	public void insert(T key, int value){
		int index;
		index = no_elements;
		heap.add(index, new Heap<T>(key, value));
		no_elements += 1;
		mapping.put(key, index);
		percolateUp(index);
	}
	
	public void percolateUp(int index){
		
		int parent = (index - 1)/2;
		while(parent >= 0 && index>0){
			int childValue = heap.get(index).getValue();
			int parentValue = heap.get(parent).getValue();
			T childName = heap.get(index).getKey();
			T parentName = heap.get(parent).getKey();
			
			if(childValue < parentValue){
				swap(index, parent);
				
				//Replacing the positions of child and parent in the map
				mapping.replace(childName, parent);
				mapping.replace(parentName, index);
			}
			else{
				break;
			}
			index = parent;
			parent = (index - 1)/2;
		}
	}
	
	public T delete(){
		int index = 0;
		if(!isEmpty()){
			Heap temp = heap.get(index);
			//Removing the element from mapping as well 
			mapping.remove(temp.getKey());
			if(no_elements == 1){
				heap.remove(index);
				no_elements--;
			}
			else{
				//Removes the last element and set the first element as the last element
				heap.set(index, heap.remove(no_elements-1));
				no_elements--;
				//Chenging the position of last element in mapping to 0 
				mapping.replace(heap.get(0).getKey(), index);
				percolateDown(index);
			}
			return (T) temp.getKey();
		}
		else{
			System.out.println("Heap is empty");
			return null;
		}
	}
	
	public boolean isEmpty(){
		if(no_elements==0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void percolateDown(int index){
		int min;
		int leftChild = (2 *  index) + 1;
		int rightChild = (2 *  index) + 2;
		while(leftChild < no_elements || rightChild < no_elements){
			min=leftChild;
			
			if(rightChild == no_elements){
				min = leftChild;
			}
			else{
				min = findMin(leftChild, rightChild);
			}
			
			int minChildValue = heap.get(min).getValue();
			int parentValue = heap.get(index).getValue();
			
			T parentName = heap.get(index).getKey();
			T minChildName = heap.get(min).getKey();
			
			if(parentValue > minChildValue){
				swap(index, min);					

				//Replacing the positions of child and parent in the map
				mapping.replace(minChildName, index);
				mapping.replace(parentName, min);
				
				index = min;
				leftChild = (2 *  index) + 1;
				rightChild = (2 *  index) + 2;
			}
			else{
				break;
			}
		}
	}
	
	public int findMin(int leftChild, int rightChild){
		if(heap.get(leftChild).getValue() > heap.get(rightChild).getValue()){
			return rightChild;
		}
		else{
			return leftChild;
		}
	}
	
	public void swap(int num1, int num2){
		Heap temp = heap.get(num1);
		heap.set(num1, heap.get(num2));
		heap.set(num2, temp);
	}
	
	//This function helps in updating the value of a node.
	public void updateNode(T key, int newValue){
		if(find(key)){
			int index = mapping.get(key);
			int oldValue = heap.get(index).getValue();
			heap.get(index).setValue(newValue);
			if(newValue>=oldValue){
				percolateDown(index);
			}
			else{
				percolateUp(index);
			}		
		}
	}
	
	public boolean find(T key){
		if(mapping.containsKey(key)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void printHeap(){
		for (int i=0; i<no_elements; i++) {
			System.out.print(heap.get(i).getKey() + "," + heap.get(i).getValue() + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		MinBinaryHeap<Character> binaryHeap = new MinBinaryHeap<>();
		binaryHeap.insert('A', 15);
		//System.out.println(binaryHeap.delete());
		binaryHeap.insert('B', 10);
		binaryHeap.insert('C', 25);
		binaryHeap.insert('D', 9);
		binaryHeap.insert('E', 5);
		binaryHeap.insert('F', 1);
		binaryHeap.insert('G', 32);
		binaryHeap.insert('H', 11);
		binaryHeap.printHeap();
		System.out.println(binaryHeap.delete());
		binaryHeap.printHeap();
		binaryHeap.insert('I', 4);
		binaryHeap.printHeap();
		binaryHeap.updateNode('H', 3);
		binaryHeap.printHeap();
		binaryHeap.updateNode('I', 40);
		binaryHeap.printHeap();
	}	
}
