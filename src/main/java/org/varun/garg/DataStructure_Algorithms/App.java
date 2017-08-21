package org.varun.garg.DataStructure_Algorithms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.varun.garg.datastructures.BinarySearchTree;
import org.varun.garg.datastructures.LinkedList;
import org.varun.garg.datastructures.Queue;
import org.varun.garg.datastructures.Stack;
import org.varun.garg.nodes.BinaryTreeNode;
import org.varun.garg.traversals.BinaryTreeTraversals;

/**
 * Hello world!
 *
 */

@Configuration
@ComponentScan({"org.varun.garg"})
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        /*Stack<Integer> stack = (Stack<Integer>) context.getBean("stack");
        stack.push(10);
        stack.push(5);
        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        stack.printStack();*/
        
       /* Queue<Integer> queue = (Queue<Integer>) context.getBean("queue");
        queue.add(10);
        queue.add(25);
        queue.printQueue();
        System.out.println(queue.remove());
        queue.printQueue();*/
        
        /*BinarySearchTree bst = (BinarySearchTree) context.getBean("binarySearchTree");
        bst.insert(bst.getRoot(), 10);
        bst.insert(bst.getRoot(), 15);
        bst.insert(bst.getRoot(), 5);
        bst.insert(bst.getRoot(), 2);
        bst.insert(bst.getRoot(), 23);
        bst.insert(bst.getRoot(), 1);
        bst.insert(bst.getRoot(), 13);
        bst.insert(bst.getRoot(), 8);
                
        BinaryTreeTraversals<Integer> binaryTreeTraversals = (BinaryTreeTraversals<Integer>) context.getBean("binaryTreeTraversals");
        binaryTreeTraversals.inOrderTraversal(bst.getRoot());
        
        System.out.println(bst.search(1));*/
        
        LinkedList<Integer> list = (LinkedList<Integer>) context.getBean("linkedList");
        list.insertAtStart(5);
        list.insertAtEnd(8);
        list.insertAtPos(2, 1);
        list.insertAtPos(4, 4);
        list.insertAtPos(6, 4);
        list.insertAtEnd(14);
        list.insertAtEnd(15);
        list.insertAtEnd(16);
        
        list.deleteAtStart();
        list.deleteAtEnd();
        list.deleteAtPos(1);
        list.deleteAtPos(5);
        list.deleteAtPos(2);
        list.deleteElement(4);
        System.out.println(list.getNo_elements());
        list.printList();
    }
}
