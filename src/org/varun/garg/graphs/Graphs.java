package org.varun.garg.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.varun.garg.datastructure.LinkedListQueue;
import io.varun.garg.datastructure.LinkedListStack;
import io.varun.garg.support.LinkedListNode;

public class Graphs<T> {

	private int v;		//no of vertices
	private int e;		//no of edges
	private Map<T, LinkedListNode<T>> node;
	
	public int getV() {
		return v;
	}
	public void setV(int v) {
		this.v = v;
	}
	public int getE() {
		return e;
	}
	public void setE(int e) {
		this.e = e;
	}	
	
	public LinkedListNode<T> getNode(T data) {
		return node.get(data);
	}
	
	public Map<T, LinkedListNode<T>> getAllNodes() {
		return node;
	}
	public void addNode(T data) {
		node.put(data, new LinkedListNode<T>(data));
		v++;
	}
	
	public Graphs() {
		super();
		v=0;
		e=0;
		node = new HashMap<>();
	}
	
	private boolean addEdge(T source, T destination){

		LinkedListNode<T> temp, counter;
		int flag = 0;
		//If node doesn't exists then create a new node
		if(!node.containsKey(source)){
			addNode(source);
			node.get(source).setNextNode(node.get(source));
		}
		if(!node.containsKey(destination)){
			addNode(destination);
			node.get(destination).setNextNode(node.get(destination));
		}
		if(source!=destination){			//Check for the entry of the isolated nodes
			counter = node.get(source);
			while(counter.getNextNode() != node.get(source)){
				//Checking if destination node already exists
				if(counter.getNextNode().getData() == destination){
					flag = 1;
					break;
				}
				counter= counter.getNextNode();		
			}
			if(flag == 0){
				temp = new LinkedListNode<>(destination, node.get(source));
				counter.setNextNode(temp);
				return true;
			}
			else{
				System.out.println("Edge already exsts");
				return false;
			}
		}
		return false;
	}
	
	//Adding edge for undirected graph
	public void addEdgeUndirected(T source, T destination){
		//For undirected graphs, we need to add both
		boolean result;
		result = addEdge(source, destination);
		if(result){
			e++;
		}
		addEdge(destination, source);
	}
	
	//Adding edge for directed graph
	public void addEdgeDirected(T source, T destination){
		//For directed graphs, we need to add only one
		boolean result;
		result = addEdge(source, destination);
		if(result){
			e++;
		}
	}
	
	public int inDegree(T data){
		LinkedListNode<T> current;
		int degree = 0;
		for (T node : getAllNodes().keySet()) {
			if(node!=data){
				current = getNode(node);
				while(current.getNextNode().getData()!=node){
					current = current.getNextNode();
					if(current.getData() == data){
						degree++;
					}
				}
			}
		}
		return degree;
	}
	
	
	public void depthFirstTraversal(){
		System.out.print("Directed Depth first search --> ");
		List<T> zeroDegree = new ArrayList<>();
		LinkedListStack<T> stack = new LinkedListStack<>();
		LinkedListNode<T> current;
		List<T> visited = new ArrayList<>();
		
		//Adding all nodes with inDegree as 0
		for (T node : getAllNodes().keySet()) {
			if(inDegree(node) == 0){
				zeroDegree.add(node);
			}
		}			
		
		for (T name : zeroDegree) {
			stack.push(name);
			visited.add(name);
			while(!stack.isEmpty()){
				name = stack.pop();
				System.out.print(name + " ");
				current = getAllNodes().get(name).getNextNode();
				while(current.getData()!=name){
					if(visited.contains(current.getData()) == false){
						stack.push(current.getData());
						visited.add(current.getData());					
					}
					current = current.getNextNode();
				}
			}
		}
	}
	
	public void depthFirstTraversal_Undirected(){
		System.out.print("Undirected Depth first search --> ");
		LinkedListStack<T> stack = new LinkedListStack<>();
		LinkedListNode<T> current;
		List<T> visited = new ArrayList<>();
		
		T name = (T) getAllNodes().keySet().toArray()[0];		
		stack.push(name);
		visited.add(name);
		while(!stack.isEmpty()){
			name = stack.pop();
			System.out.print(name + " ");
			current = getAllNodes().get(name).getNextNode();
			while(current.getData()!=name){
				if(visited.contains(current.getData()) == false){
					stack.push(current.getData());
					visited.add(current.getData());					
				}
				current = current.getNextNode();
			}
		}		
	}
	
	public void breadthFirstTraversal(){
		System.out.print("Directed Breadth first search --> ");
		List<T> zeroDegree = new ArrayList<>();
		LinkedListQueue<T> queue = new LinkedListQueue<>();
		LinkedListNode<T> current;
		List<T> visited = new ArrayList<>();
				
		//Adding all nodes with inDegree as 0
		for (T node : getAllNodes().keySet()) {
			if(inDegree(node) == 0){
				zeroDegree.add(node);
			}
		}			
		
		for (T name : zeroDegree) {
			queue.enqueue(name);
			visited.add(name);
			while(!queue.isEmpty()){
				name = queue.dequeue();
				System.out.print(name + " ");
				current = getAllNodes().get(name).getNextNode();
				while(current.getData() != name){
					//Not adding already visited node in the queue
					if(visited.contains(current.getData()) == false){
						queue.enqueue(current.getData());
						visited.add(current.getData());
					}
					current = current.getNextNode();
				}
			}
		}
	}
	
	public void breadthFirstTraversal_Undirected(){
		System.out.print("Undirected Breadth first search --> ");
		LinkedListQueue<T> queue = new LinkedListQueue<>();
		LinkedListNode<T> current;
		List<T> visited = new ArrayList<>();
				
		T name = (T) getAllNodes().keySet().toArray()[0];
		queue.enqueue(name);
		visited.add(name);
		while(!queue.isEmpty()){
			name = queue.dequeue();
			System.out.print(name + " ");
			current = getAllNodes().get(name).getNextNode();
			while(current.getData() != name){
				//Not adding already visited node in the queue
				if(visited.contains(current.getData()) == false){
					queue.enqueue(current.getData());
					visited.add(current.getData());
				}
				current = current.getNextNode();
			}
		}
	}
	
	public void topologicalSort(){
		List<T> zeroDegree = new ArrayList<>();
		LinkedListStack<T> stack = new LinkedListStack<>();
		LinkedListStack<T> finalStack = new LinkedListStack<>();
		LinkedListNode<T> current;
		
		List<T> visited = new ArrayList<>();
		
		//Adding all nodes with inDegree as 0
		for (T node : getAllNodes().keySet()) {
			if(inDegree(node) == 0){
				zeroDegree.add(node);
			}
		}	
		
		for (T name : zeroDegree) {
			stack.push(name);
			while(!stack.isEmpty()){
				name = stack.pop();
				if(!visited.contains(name)){
					stack.push(name);
					visited.add(name);
					current = getAllNodes().get(name).getNextNode();
					while(current.getData() != name){
						if(!visited.contains(current.getData())){
							stack.push(current.getData());
						}
						current = current.getNextNode();
					}
				}
				else{
					finalStack.push(name);
				}
			}
		}	
		finalStack.printStack();
	}
	
	public void undirectedGraphs(){
		Graphs<Character> undirectedGraph = new Graphs<Character>();
		undirectedGraph.addEdgeUndirected('A', 'B');
		undirectedGraph.addEdgeUndirected('A', 'F');
		undirectedGraph.addEdgeUndirected('A', 'G');
		undirectedGraph.addEdgeUndirected('B', 'C');
		undirectedGraph.addEdgeUndirected('B', 'E');
		undirectedGraph.addEdgeUndirected('C', 'D');
		undirectedGraph.addEdgeUndirected('D', 'H');
		undirectedGraph.addEdgeUndirected('E', 'G');
		undirectedGraph.addEdgeUndirected('F', 'I');
		undirectedGraph.addEdgeUndirected('G', 'H');
		
		undirectedGraph.depthFirstTraversal_Undirected();
		System.out.println();
		undirectedGraph.breadthFirstTraversal_Undirected();
	}
	
	public void directedGraphs(){
		Graphs<Character> directedGraph = new Graphs<Character>();
		directedGraph.addEdgeDirected('A', 'B');
		directedGraph.addEdgeDirected('A', 'F');
		directedGraph.addEdgeDirected('A', 'G');
		directedGraph.addEdgeDirected('B', 'C');
		directedGraph.addEdgeDirected('B', 'E');
		directedGraph.addEdgeDirected('C', 'D');
		directedGraph.addEdgeDirected('D', 'H');
		directedGraph.addEdgeDirected('E', 'G');
		directedGraph.addEdgeDirected('F', 'I');
		directedGraph.addEdgeDirected('G', 'H');
		
		directedGraph.topologicalSort();
		
		directedGraph.depthFirstTraversal();
		System.out.println();
		directedGraph.breadthFirstTraversal();
	}

	public static void main(String[] args) {
		
		Graphs<Character> graphs = new Graphs<Character>();
		graphs.directedGraphs();
		System.out.println();
		graphs.undirectedGraphs();
		
	}
}
