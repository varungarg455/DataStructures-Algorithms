package org.varun.garg.graphs;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.Current;

import io.varun.garg.datastructure.LinkedListQueue;
import io.varun.garg.datastructure.LinkedListStack;
import io.varun.garg.support.WeightedGraphNode;
import io.varun.garg.trees.MinBinaryHeap;

public class ShortestPathAlgo<T> {

	private int v;
	private int e;
	private Map<T, WeightedGraphNode<T>> node;
	
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
	
	public Map<T, WeightedGraphNode<T>> getAllNodes(){
		return node;
	}
	
	public WeightedGraphNode<T> getNode(T data){
		return node.get(data);
	}
	
	public void addNode(T data, int weight){
		node.put(data, new WeightedGraphNode<>(data, weight));
		v++;
	}
	
	public ShortestPathAlgo() {
		super();
		v = 0;
		e = 0;
		node = new HashMap<>();
	}
	
	private boolean addEdge(T source, T destination, int weight){
		WeightedGraphNode<T> counter, temp;
		if(!node.containsKey(source)){
			addNode(source, 0);
			node.get(source).setNextNode(node.get(source));
		}
		if(!node.containsKey(destination)){
			addNode(destination, 0);
			node.get(destination).setNextNode(node.get(destination));
		}
		if(source!=destination){
			counter = node.get(source);
			int flag = 0;
			while(counter.getNextNode()!=node.get(source)){
				if(counter.getNextNode().getData() == destination){
					flag = 1;
					break;
				}
				counter = counter.getNextNode();
			}
			if(flag == 0){
				temp = new WeightedGraphNode<>(destination, weight, node.get(source));
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
	
	//Adding edge for directed graph
	public void addEdgeDirected(T source, T destination, int weight){
		//For directed graphs, we need to add only one
		boolean result;
		result = addEdge(source, destination, weight);
		if(result){
			e++;
		}
	}
	
	public int inDegree(T data){
		WeightedGraphNode<T> current;
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
	
	public void breadthFirstTraversal(){
		System.out.print("Directed Breadth first search --> ");
		List<T> zeroDegree = new ArrayList<>();
		LinkedListQueue<T> queue = new LinkedListQueue<>();
		WeightedGraphNode<T> current;
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
	
	public void depthFirstTraversal(){
		System.out.print("Directed Depth first search --> ");
		List<T> zeroDegree = new ArrayList<>();
		LinkedListStack<T> stack = new LinkedListStack<>();
		WeightedGraphNode<T> current;
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
	
	/*
	 * Dijkstra algorith is used to find the shortest distance from a source to every other node.
	 * We can only get the shortest paths for the graphs which don't have negative weights in them.
	 */
	public void dijkstra(T source){
		
		Map<T, DistanceTable<T>> distanceTable	= new HashMap<>();
		List<T> visited = new ArrayList<>();
		MinBinaryHeap<T> heap = new MinBinaryHeap<>();
		Map<T, WeightedGraphNode<T>> nodes = getAllNodes();
		
		//Adding all the nodes to the distance table
		for (T vertex : getAllNodes().keySet()) {
			distanceTable.put(vertex, new DistanceTable<T>(99999, null));
			//Checking if the vertex is same as source, then mark distance as 0
			if(vertex == source){
				distanceTable.get(vertex).setDistance(0);
			}
		}
		
		WeightedGraphNode<T> destinationNode;
		heap.insert(source, 0);
		while(!heap.isEmpty()){
			T sourceName = heap.delete();
			if(!visited.contains(sourceName)){
				int sourceDistance = distanceTable.get(sourceName).getDistance();
				visited.add(sourceName);
				
				//Traversing the nodes from source one by one
				destinationNode = nodes.get(sourceName).getNextNode();
				while(destinationNode.getData()!=sourceName){					
					/*
					 * Checking this condition as once the node is added to visited list, it means it is already been processed.
					 * It should not be checked again.
					 */
					if(!visited.contains(destinationNode.getData())){	
						/*
						 * Relax is method which checks is the sum of sourceDistance + edgeWeight is less than destinationDistance.
						 * If it is true, then it set the destinationDistance as new distance and predecessor as source.
						 */
						int oldDistance = distanceTable.get(destinationNode.getData()).getDistance();
						int newDistance = relax(sourceName, destinationNode.getData(), destinationNode.getWeight(), distanceTable);
						if(newDistance < oldDistance){
							if(heap.find(destinationNode.getData())){
								heap.updateNode(destinationNode.getData(), newDistance);
							}
							else{
								heap.insert(destinationNode.getData(), newDistance);
							}
						}
					}
					destinationNode = destinationNode.getNextNode(); 
				}
			}
		}
		printShortestPaths(source, distanceTable);
	}
	
	public int relax(T sourceName, T destinationName, int edgeWeight, Map<T, DistanceTable<T>> distanceTable){
		//Data from nodes data
		int sourceDistance = distanceTable.get(sourceName).getDistance();
		int destinationdistance = distanceTable.get(destinationName).getDistance();
				
		int newDistance = sourceDistance + edgeWeight;
		if( destinationdistance > newDistance ){
			distanceTable.get(destinationName).setDistance(newDistance);
			distanceTable.get(destinationName).setPreviousVertex(sourceName);
			return newDistance;
		}
		return destinationdistance;
	}
	
	public void bellmanFord(T source){		
		Map<T, DistanceTable<T>> distanceTable	= new HashMap<>();
		MinBinaryHeap<T> heap = new MinBinaryHeap<>();
		Map<T, WeightedGraphNode<T>> nodes = getAllNodes();
		int no_vertex = getV();
		boolean negativeCycle = false;
		
		//Adding all the nodes to the distance table
		for (T vertex : getAllNodes().keySet()) {
			distanceTable.put(vertex, new DistanceTable<T>(99999, null));
			//Checking if the vertex is same as source, then mark distance as 0
			if(vertex == source){
				distanceTable.get(vertex).setDistance(0);
			}
		}
		
		//We need to run the same thing for v-1 times
		for(int i=1; i<no_vertex; i++){
			for (T sourceName : getAllNodes().keySet()) {
				WeightedGraphNode<T> destinationNode = nodes.get(sourceName).getNextNode();
				while(destinationNode.getData()!=sourceName){
					relax(sourceName, destinationNode.getData(), destinationNode.getWeight(), distanceTable);				
					destinationNode = destinationNode.getNextNode(); 
				}
			}
		}
		
		/*
		 * If even after running for n-1 times, the weights in distance table still changes,
		 * then it means it has a negative cycle. 
		 */		
		for (T vertex : getAllNodes().keySet()) {
			T sourceName = vertex;
			WeightedGraphNode<T> destinationNode = nodes.get(sourceName).getNextNode();
			while(destinationNode.getData()!=sourceName){
				int oldDistance = distanceTable.get(destinationNode.getData()).getDistance();
				int newDistance = relax(sourceName, destinationNode.getData(), destinationNode.getWeight(), distanceTable);	
				if(newDistance < oldDistance){
					negativeCycle = true;
					break;
				}
				destinationNode = destinationNode.getNextNode(); 
			}
		}		
		
		if(!negativeCycle){
			System.out.println();
			printShortestPaths(source, distanceTable);
		}
		else{
			System.out.println("Negative cycle detected");
		}
	}	
	
	public void floydWarshall(){
		WeightedGraphNode<T> counter = null;
		int[][] d = new int[getV()][getV()];
		
		Map<T, Integer> dataToNoMap = new HashMap<>();
		Map<Integer, T> noToDataMap  = new HashMap<>();
		
		int num=0;
		for (T node : getAllNodes().keySet()) {
			dataToNoMap.put(node, num);
			noToDataMap.put(num, node);	
			num++;
		}	
		
		for(int i=0; i<getV(); i++){
			for(int j=0; j<getV(); j++){
				if(i==j){
					d[i][j]=0;
				}
				else{
					d[i][j] = 99999;
				}
			}
		}

		for(int i=0; i<getV(); i++){
			T source = noToDataMap.get(i);
			counter = getAllNodes().get(source).getNextNode();
			while(counter.getData()!=source){
				int destination = dataToNoMap.get(counter.getData()); 
				d[i][destination] = counter.getWeight();
				counter = counter.getNextNode();
			}			
		}
		
		for(int k=0; k<getV(); k++){
			for(int i=0; i<getV(); i++){
				for(int j=0; j<getV(); j++){
					d[i][j] = Math.min(d[i][j], (d[i][k] + d[k][j]));
				}
			}
		}
		
		//This loop is to check is there is negative cycle in the graph
		for(int i=0; i<getV(); i++){
			if(d[i][i]<0){
				System.out.println("Graph contains a negative cycle");
				break;
			}
		}
		
		for (T node : dataToNoMap.keySet()) {
			System.out.print("\t" + node);
		}
		System.out.println();
		for(int i=0; i<getV(); i++){
			System.out.print(noToDataMap.get(i) + "\t");
			for(int j=0; j<getV(); j++){
				System.out.print(d[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	
	public void printShortestPaths(T source, Map<T, DistanceTable<T>> distanceTable){

		for (T node : distanceTable.keySet()) {
			DistanceTable<T> currentNode = distanceTable.get(node);
			String path = "";
			T previousNode = currentNode.getPreviousVertex();
			if(node != source){
				if(previousNode == source){
					path = source + " --> " + node;
				}
				else{
					path = path + node;
					while(previousNode!=source){
						path = previousNode + " --> " + path;
						previousNode = distanceTable.get(previousNode).getPreviousVertex();
					}
					path = previousNode + " --> " + path;
				}
				System.out.println("Node : " + node + "; Weight : " + currentNode.getDistance() 
									+ "; Path : " + path);
			}
		}
	}
	
	public static void main(String[] args){
		
		ShortestPathAlgo<Character> graph = new ShortestPathAlgo<>();
		graph.addEdgeDirected('A', 'B', 10);
		graph.addEdgeDirected('A', 'C', 5);
		graph.addEdgeDirected('B', 'D', 1);
		graph.addEdgeDirected('B', 'C', 2);
		graph.addEdgeDirected('C', 'B', 3);
		graph.addEdgeDirected('C', 'E', 2);
		graph.addEdgeDirected('D', 'E', 4);
		graph.addEdgeDirected('E', 'A', 7);
		graph.addEdgeDirected('E', 'D', 6);
		
		
		/*graph.breadthFirstTraversal();
		System.out.print("\n");
		graph.depthFirstTraversal();
		System.out.print("\n");		*/
		graph.dijkstra('A');
		
		System.out.print("\n");		
		graph.bellmanFord('A');
		
		
		/*ShortestPathAlgo<Character> graph = new ShortestPathAlgo<>();
		graph.addEdgeDirected('A', 'B', 3);
		graph.addEdgeDirected('A', 'C', 5);
		graph.addEdgeDirected('B', 'D', -5);
		graph.addEdgeDirected('B', 'C', 2);
		graph.addEdgeDirected('C', 'B', 3);
		graph.addEdgeDirected('C', 'E', 2);
		graph.addEdgeDirected('D', 'E', 4);
		graph.addEdgeDirected('E', 'A', 7);
		graph.addEdgeDirected('E', 'D', 6);
		//graph.bellmanFord('A');
		graph.floydWarshall();*/
	}
	
	public class DistanceTable<T>{
		int distance;
		T previousVertex;
		public int getDistance() {
			return distance;
		}
		public void setDistance(int distance) {
			this.distance = distance;
		}
		public T getPreviousVertex() {
			return previousVertex;
		}
		public void setPreviousVertex(T previousVertex) {
			this.previousVertex = previousVertex;
		}
		public DistanceTable(int distance, T previousVertex) {
			super();
			this.distance = distance;
			this.previousVertex = previousVertex;
		}						
	}
}
