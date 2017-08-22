package io.varun.garg.datastructure;

import io.varun.garg.exceptions.LinkedListException;
import io.varun.garg.interfaces.LinkedListInterface;

public class LinkedList implements LinkedListInterface<Integer>{

	private Node head;
	private Node temp;
	private Node end;	
	private Node counter;
	
	public LinkedList() {
		super();
		head = null;
		end = null;
	}

	@Override
	public void insertAtStart(Integer element) {
		temp = new Node();
		temp.setData(element);
		temp.setNextNode(null);
		if(head == null){
			head = temp;
			end = temp;
		}
		else{
			temp.setNextNode(head);
			head = temp;
		}		
	}


	@Override
	public void insertAtEnd(Integer element) {
		temp = new Node();
		temp.setData(element);
		temp.setNextNode(null);
		if(head == null){
			head = temp;
			end = temp;
		}
		else{
			end.setNextNode(temp);
			end = temp;
		}		
	}


	@Override
	public void insertAtPos(Integer element, int position) throws LinkedListException {
		int i=1;
		if(position<1){
			throw new LinkedListException("Invalid position entered");						
		}
		else if(position == 1){
			temp = new Node();
			temp.setData(element);
			temp.setNextNode(head);
			head = temp;
		}
		else{
			counter = head;
			while(i<position-1){
				if(counter.getNextNode() == null){
					throw new LinkedListException("Position " + position + " doesn't exists in list");
				}
				counter = counter.getNextNode();
				i++;
			}
			temp = new Node();
			temp.setData(element);
			temp.setNextNode(counter.getNextNode());
			counter.setNextNode(temp);
		}		
	}


	@Override
	public Integer deleteAtStart() throws LinkedListException {
		temp = head;
		int value = temp.getData();
		head = head.getNextNode();
		temp = null;
		return value;
	}


	@Override
	public Integer deleteAtEnd() throws LinkedListException {
		temp = head;
		while(temp.getNextNode().getNextNode()!=null){
			temp = temp.getNextNode();
		}
		int value = temp.getData();
		temp.setNextNode(null);
		return value;
	}


	@Override
	public Integer deleteAtPos(int position) throws LinkedListException {
		int i=1;
		int value;
		if(position<1){
			throw new LinkedListException("Invalid position entered");						
		}
		else if(position == 1){
			value = head.getData();
			head = head.getNextNode();
		}
		else{
			counter = head;
			while(i<position-1){
				if(counter.getNextNode() == null){
					throw new LinkedListException("Position " + position + " doesn't exists in list");
				}
				counter = counter.getNextNode();
				i++;
			}
			value = counter.getNextNode().getData();
			counter.setNextNode(counter.getNextNode().getNextNode());
		}				
		return value;
	}

	@Override
	public int search(Integer element) throws LinkedListException {
		temp = head;
		int pos = -1;
		int i = 1;
		while(temp.getData()!=element && temp.getNextNode()!=null){
			temp = temp.getNextNode(); 
			i++;
		}
		if(temp.getData()==element){
			pos = i;
		}
		return pos;
	}
	
	public void printList(){
		temp = head;
		while(temp!=null){
			System.out.print(temp.getData() + " -> ");
			temp = temp.getNextNode();
		}
		System.out.print("NULL\n");
		
	}	
	
	public class Node{
		
		private int data;
		private Node nextNode;
		
		public Node() {
			super();
			this.data = 0;
			this.nextNode = null;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getNextNode() {
			return nextNode;
		}

		public void setNextNode(Node nextNode) {
			this.nextNode = nextNode;
		}			
	}
	
	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		list.insertAtEnd(5);
		list.insertAtStart(15);
		list.insertAtEnd(25);
		list.insertAtStart(35);
		list.printList();
		
		list.insertAtPos(13, 4);
		list.printList();
		
		/*list.deleteAtEnd();
		list.printList();
		list.deleteAtStart();
		list.printList();*/
		
		list.deleteAtPos(5);
		list.printList();
		
		System.out.println(list.search(15));
	}
	
}