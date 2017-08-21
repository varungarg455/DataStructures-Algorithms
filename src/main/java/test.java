import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.varun.garg.DataStructure_Algorithms.App;
import org.varun.garg.datastructures.LinkedList;
import org.varun.garg.nodes.LinkedListNode;

public class test {
	
	public void practice(){
	
		ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
		LinkedList<Integer> list = (LinkedList<Integer>) context.getBean("linkedList");
		list.insertAtEnd(1);
		list.insertAtEnd(2);
		list.insertAtEnd(3);
		list.insertAtEnd(4);
		list.insertAtEnd(5);
		list.insertAtEnd(6);
		list.insertAtEnd(7);
		list.insertAtEnd(8);
		list.insertAtEnd(9);
		list.insertAtEnd(10);
		list.insertAtEnd(11);
		
		list.printList();
		
		LinkedListNode<Integer> temp = list.getHead();
		
		
		
	}
	
}
