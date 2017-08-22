package org.varun.garg.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.varun.garg.graphs.ShortestPathAlgo.DistanceTable;

import io.varun.garg.support.LinkedListNode;
import io.varun.garg.support.WeightedGraphNode;
import io.varun.garg.trees.MinBinaryHeap;

public class MinimumSpanningTree<T> {

	private int v;		//no of vertices
	private int e;		//no of edges
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
	
	public MinimumSpanningTree() {
		super();
		v=0;
		e=0;
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
	
	//Adding edge for undirected graph
	public void addEdgeUndirected(T source, T destination, int weight){
		//For undirected graphs, we need to add both
		boolean result;
		result = addEdge(source, destination, weight);
		if(result){
			e++;
		}
		addEdge(destination, source, weight);
	}	

	public List<GraphEdge<T>> getAllEdges(){		
		List<GraphEdge<T>> edges = new ArrayList<>();
		WeightedGraphNode<T> current;
		GraphEdge<T> temp;
		Map<T, WeightedGraphNode<T>> nodes = getAllNodes();
		for (T node : nodes.keySet()) {
			current = getNode(node).getNextNode();
			while(current.getData()!=node){
				temp = new GraphEdge<T>(node, current.getData(), current.getWeight());
				edges.add(temp);
				current = current.getNextNode();
			}			
		}
		return edges;		
	}
	
	public void kruskalAlgorithm() {
		DisjointSets<T> set = new DisjointSets<>();
		List<GraphEdge> forest = new ArrayList<>();
		
		List<GraphEdge<T>> edges = getAllEdges();		
		edges.sort(new EdgeComparator());
		
		for (T node : getAllNodes().keySet()) {
			set.createNode(node);
		}
		
		for (GraphEdge<T> edge : edges) {
			if(set.root(edge.getSource()) != set.root(edge.getDestination())){
				set.union(edge.getSource(), edge.getDestination());
				forest.add(edge);
			}
		}	
		
		for (GraphEdge graphEdge : forest) {
			System.out.println(graphEdge.getSource() + " -> " + graphEdge.getDestination() + " -> " + graphEdge.getWeight());
		}
	}
	
	public void primAlgorithm(T source){
		
		Map<T, DistanceTable<T>> distanceTable	= new HashMap<>();
		MinBinaryHeap<T> heap = new MinBinaryHeap<>();
		Map<T, WeightedGraphNode<T>> nodes = getAllNodes();
		
		//Adding all the nodes to the distance table and heap
		for (T vertex : nodes.keySet()) {
			distanceTable.put(vertex, new DistanceTable<T>(99999, null));
			heap.insert(vertex, 99999);
			
			//Checking if the vertex is same as source, then mark distance as 0
			if(vertex == source){
				distanceTable.get(vertex).setDistance(0);
				heap.updateNode(vertex, 0);
			}			
		}
		
		while(!heap.isEmpty()){
			T node = heap.delete();
			WeightedGraphNode<T> destination = nodes.get(node).getNextNode();
			while(destination.getData()!=node){
				if(heap.find(destination.getData())){
					int oldDistance = distanceTable.get(destination.getData()).getDistance();
					int newDistance = destination.getWeight();
					if(newDistance < oldDistance){
						distanceTable.get(destination.getData()).setDistance(newDistance);
						distanceTable.get(destination.getData()).setPreviousVertex(node);
						heap.updateNode(destination.getData(), newDistance);
					}
				}
				destination = destination.getNextNode();
			}
		}
		printMST(source, distanceTable);
	}
	
	public void printMST(T source, Map<T, DistanceTable<T>> distanceTable){

		for (T node : distanceTable.keySet()) {
			if(node!=source){
				System.out.println(distanceTable.get(node).getPreviousVertex() + " -> " 
									+ node + " -> " + distanceTable.get(node).getDistance());
			}
		}
	}
	
	public static void main(String[] args) {
		MinimumSpanningTree<Character> mst = new MinimumSpanningTree<>();
		mst.addEdgeUndirected('A', 'B', 3);
		mst.addEdgeUndirected('A', 'D', 4);
		mst.addEdgeUndirected('B', 'D', 1);
		mst.addEdgeUndirected('B', 'F', 9);
		mst.addEdgeUndirected('B', 'C', 5);
		mst.addEdgeUndirected('C', 'G', 2);
		mst.addEdgeUndirected('D', 'F', 6);
		mst.addEdgeUndirected('D', 'E', 7);
		mst.addEdgeUndirected('E', 'H', 3);
		mst.addEdgeUndirected('F', 'H', 4);
		mst.addEdgeUndirected('F', 'G', 4);
		mst.addEdgeUndirected('G', 'H', 3);
		
		mst.kruskalAlgorithm();
		
		System.out.println("\n");
		mst.primAlgorithm('A');
	}
	

	public class GraphEdge<T> {
		
		private T source;
		private T destination;
		private int weight;
		
		public T getSource() {
			return source;
		}
		public void setSource(T source) {
			this.source = source;
		}
		public T getDestination() {
			return destination;
		}
		public void setDestination(T destination) {
			this.destination = destination;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		public GraphEdge(T source, T destination, int weight) {
			super();
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}
		public GraphEdge() {
			super();
			// TODO Auto-generated constructor stub
		}		
	}
	
	public class EdgeComparator implements Comparator<GraphEdge>{
		@Override
		public int compare(GraphEdge edge1, GraphEdge edge2) {
			if(edge1.getWeight() == edge2.getWeight()){
				return 0;
			}
			else if(edge1.getWeight() > edge2.getWeight()){
				return 1;
			}
			else{
				return -1;
			}
		}		
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

