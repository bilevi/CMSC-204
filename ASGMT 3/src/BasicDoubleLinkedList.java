import java.util.*;
/*
 * Class: CMSC 204
 * Instructor: Monshi
 * Description: A program that creates a double linked list and a sorted double linked list with the required implementation.
 * Due: 10/25/2022
 * Platform/compiler: Eclipse
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: B. Leigh Vining
 */

/**
 * 
 * @author B. Leigh Vining
 *
 * @param <T>
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node head, tail;
	protected int size;
	
	public BasicDoubleLinkedList() {
	head = null;
	tail = head;
	size = 0;
	}
	/**
	 * 
	 * @return the size of the list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * 
	 * @param data  data to be added at end of the list
	 */
	public void addToEnd(T data) {
		Node current = new Node(data);
		
		if (head == null) //if no elements in list
		{
			head = current; //first node points to data node
			tail = head; //tail points to head node
		}
		else 
		{
			tail.next = current; //node after end points to data node
			current.prev = tail; // data node (previous) points to tail
			tail = current; //tail points to data node
		}
		size++;
	}
	
	/**
	 * 
	 * @param data  data to be added at the front of the list
	 */
	public void addToFront(T data) {
		Node current = new Node(data);
		
		if (head == null) //if list is empty
		{
			head = current; //head is current
			tail = head; //tail points to head
		}
		else
		{
			current.next = head; //node after data points to head
			head.prev = current; //node previous to head points to current
			head = current; //head is current
		}
			
		size++;
	}
	
	/**
	 * 
	 * @return the data at the beginning of the list
	 */
	public T getFirst() {
		return head.data;
	}
	
	/**
	 * 
	 * @return  the data at the end of the list
	 */
	public T getLast() {
		return tail.data;
	}
	
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	/**
	 * 
	 * @param targetData  the data that is being targeted for removal
	 * @param comparator  the comparable object from the comparator class
	 * @return  the node that was removed
	 */
	public BasicDoubleLinkedList.Node remove(T targetData, Comparator<T> comparator) {
		Node current = head; //make new node that points to head element
		
		while (current != null) //while element is not empty, go through list
		{
			if (comparator.compare(targetData, current.data) == 0) //if node data is same as target data
			{
				if (current == head) //if node is same as head's element
				{
					head = head.next; //make head be element after this
				}
				else if (current == tail) //else node is same as tail's element
				{
					tail = tail.prev; //make tail be element prior to this
				}
				else current.prev.next = current.next; //otherwise, make previous node to this element point to element after it
			}
			current = current.next; //make this node point to element after it
		}
		size--;
		return current;
	}
	
	/**
	 * 
	 * @return  the first element
	 */
	public T retrieveFirstElement() {
		Node current = head;
		T data = head.data;
		
		head = current.next;
		head.prev = null;
		
		size--;
		return data;
	}
	
	/**
	 * 
	 * @return the last element
	 */
	public T retrieveLastElement() {
		Node current = tail;
		T data = tail.data;
		
		tail = current.prev;
		tail.next = null;
		
		size--;
		return data;
	}
	
	/**
	 * 
	 * @return an array list of all the data
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> listArray = new ArrayList<T>();
		Node current = head;
		
		while (current != null)
		{
			System.out.println(current.data);
			listArray.add(current.data);
			current = current.next;
		}
		
		return listArray;
	}
	
	
	protected class Node {
		protected T data;
		protected Node prev, next;
		
		/**
		 * 
		 * @param dataNode data for new node to be added in
		 */
		public Node(T dataNode) {
			data = dataNode;
			next = null;
			prev = null;
		}
	}
	
	
	protected class DoubleLinkedListIterator implements ListIterator<T> {
		protected Node previous, current;
		
		public DoubleLinkedListIterator() {
			current = head;
			previous = null;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public T next() throws NoSuchElementException {
			if (current != null)
			{
				Node temp = current;
				previous = current;
				current = current.next;
				
				return temp.data;
			}
			else throw new NoSuchElementException();
		}
		
		@Override
		public boolean hasPrevious() {
			return previous != null;
		}
		
		@Override
		public T previous() throws NoSuchElementException {
			if (previous != null)
			{
				Node temp = previous;
				current = temp;
				previous = temp.prev;
				
				return temp.data;
			} else throw new NoSuchElementException();
		}
		
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void add(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void set(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
}
