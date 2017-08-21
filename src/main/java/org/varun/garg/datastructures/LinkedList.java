/******************************************************************************
						Author : Varun Garg
 * This is a class which will be used to implement the linked list.
 * The linked list support following functionalities:
 		1. Insert - Insertion at start, end and at a specific position.
 		2. Delete - Deletion at start, end, position and delete an element.
 		3. IsEmpty - Tells if the linked list is empty or not.
 * This is implemented as generic class so that we can put any type of
 * data/object inside this BST.
  
******************************************************************************/

package org.varun.garg.datastructures;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.varun.garg.nodes.LinkedListNode;

@Component
public class LinkedList<T> implements ApplicationContextAware{

	LinkedListNode<T> head;
	LinkedListNode<T> tail;
	int no_elements = 0;
	ApplicationContext context;
	
	public int getNo_elements() {
		return no_elements;
	}
	
	public LinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(LinkedListNode<T> head) {
		this.head = head;
	}

	public LinkedList() {
		super();
	}
	
	public void insertAtStart(T data){
		LinkedListNode<T> temp = (LinkedListNode<T>) context.getBean("linkedListNode");
		temp.setData(data);
		temp.setNextNode(null);		
		if(isEmpty()){
			head = temp;
			tail = temp;
		}		
		else{
			temp.setNextNode(head);
			head = temp;
		}
		no_elements++;
	}
	
	public void insertAtEnd(T data){
		LinkedListNode<T> temp = (LinkedListNode<T>) context.getBean("linkedListNode");
		temp.setData(data);
		temp.setNextNode(null);		
		if(isEmpty()){
			head = temp;
			tail = temp;
		}		
		else{
			tail.setNextNode(temp);
			tail = temp;
		}
		no_elements++;
	}
	
	public void insertAtPos(T data, int pos){
		if(isEmpty()){
			System.out.println("List is empty");
		}
		else if(pos>no_elements+1){
			System.out.println("Invalid position!!");
		}
		else if(pos == 1){
			insertAtStart(data);
		}
		else if(pos == no_elements+1){
			insertAtEnd(data);
		}
		else{
			LinkedListNode<T> temp = (LinkedListNode<T>) context.getBean("linkedListNode");
			LinkedListNode<T> counter;
			int i;
			temp.setData(data);
			temp.setNextNode(null);	
			
			counter = head;
			i = 1;
			while(i<pos-1){
				i++;
				counter = counter.getNextNode();
			}
			temp.setNextNode(counter.getNextNode());
			counter.setNextNode(temp);
			no_elements++;
		}	
	}
	
	public void deleteAtStart(){
		if(isEmpty()){
			System.out.println("List is empty");
		}
		else if(head.getNextNode() == null){
			head = null;
			tail = null;
			no_elements--;
		}
		else{
			head = head.getNextNode();
			no_elements--;			
		}
	}
	
	public void deleteAtEnd(){
		if(isEmpty()){
			System.out.println("List is empty");
		}
		//This condition is checked if there is only 1 element in the list
		else if(head.getNextNode() == null){
			head = null;
			tail = null;
			no_elements--;
		}
		else{
			LinkedListNode<T> temp = head;
			while(temp.getNextNode() != tail){
				temp = temp.getNextNode();
			}
			temp.setNextNode(null);
			tail = temp;
			no_elements--;
		}
	}
	
	public void deleteAtPos(int pos){
		if(isEmpty()){
			System.out.println("List is empty");
		}
		else if(pos>no_elements){
			System.out.println("Invalid position!!");
		}
		else if(pos == 1){
			deleteAtStart();
		}
		else if(pos == no_elements){
			deleteAtEnd();
		}
		else{
			LinkedListNode<T> temp = head;
			int i = 1;
			while(i<pos-1){
				i++;
				temp = temp.getNextNode();
			}
			temp.setNextNode(temp.getNextNode().getNextNode());	
			no_elements--;
		}
	}
	
	public boolean deleteElement(T data){
		LinkedListNode<T> temp = head;
		if(!isEmpty()){
			//This condition is checked if the element to be deleted is the first element.
			if(temp.getData() == data){
				head = head.getNextNode();
				no_elements--;
				return true;
			}
			else{
				while(temp!=null){
					if(temp.getNextNode().getData() == data){
						temp.setNextNode(temp.getNextNode().getNextNode());
						no_elements--;
						return true;
					}
				}
			}
		}
		System.out.println("List is empty or element not found in the list");
		return false;
	}
	
	public boolean search(T data){
		LinkedListNode<T> temp = head;
		while(temp!=null){
			if(temp.getData() == data){
				return true;
			}
			temp = temp.getNextNode();
		}
		return false;
	}
	
	public boolean isEmpty(){
		if(no_elements == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void printList(){
		LinkedListNode<T> counter;
		counter = head;
		while(counter!=null){
			System.out.print(counter.getData() + " -> ");
			counter = counter.getNextNode();
		}
		System.out.println("NULL");
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}	
}
