package org.varun.garg.graphs;

import java.util.HashMap;
import java.util.Map;

import io.varun.garg.datastructure.LinkedListStack;
import io.varun.garg.support.DisjointSetNode;

public class DisjointSets<T> {

	Map<T, DisjointSetNode<T>> array = new HashMap<>();
	
	public Map<T, DisjointSetNode<T>> getArray() {
		return array;
	}

	//Create a new node in the array
	public boolean createNode(T data){
		//checking if the node already exists in the array
		if(array.containsKey(data)){
			return false;
		}
		else{
			array.put(data, new DisjointSetNode<T>(data, 1));
			return true;
		}
	}
	
	//Finiding the root of the element data
	public T root(T data){
		LinkedListStack<T> stack = new LinkedListStack<>();
		if(array.containsKey(data)){
			T root = array.get(data).getRoot();
			while(root!=data){
				stack.push(data);
				data = root;
				root = array.get(data).getRoot();
			}
			
			//Doing path compression
			while(!stack.isEmpty()){
				T temp = stack.pop();
				array.get(temp).setRoot(root);
			}
			return root;
		}
		return null;
	}
	
	//Combining two sets containing elements data1 and data2
	public void union(T data1, T data2){
		T root1 = root(data1);
		T root2 = root(data2);
		if(root1!=root2){
			int size1 = array.get(root1).getSize();
			int size2 = array.get(root2).getSize();
			if(size1 >= size2){
				array.get(root2).setRoot(root1);
				array.get(root1).setSize(size1 + size2);
			}
			else{
				array.get(root1).setRoot(root2);
				array.get(root2).setSize(size1 + size2);
			}
		}
	}
	
	//Finding if the data1 and data2 are conected, i.e. they have same root.
	public boolean find(T data1, T data2){
		if(root(data1) == root(data2)){
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		DisjointSets<Character> dsn = new DisjointSets<>();
		dsn.createNode('A');
		dsn.createNode('B');
		dsn.createNode('C');
		dsn.createNode('D');
		dsn.createNode('E');
		dsn.createNode('F');
		dsn.createNode('G');
		dsn.createNode('H');
		
		dsn.union('A', 'B');
		dsn.union('C', 'D');
		dsn.union('E', 'F');
		dsn.union('G', 'H');
		System.out.println(dsn.getArray().get('F').getRoot());
		dsn.union('D', 'F');
		dsn.union('B', 'H');
		dsn.union('A', 'C');
		System.out.println(dsn.find('D', 'H'));
		//System.out.println(dsn.root('D'));
		System.out.println(dsn.getArray().get('F').getRoot());
	}

}
