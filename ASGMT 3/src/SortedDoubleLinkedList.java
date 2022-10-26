import java.util.*;
/**
 * 
 * @author bilev
 *
 * @param <T>  Generic class indicator
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	Comparator<T> comp;
	
	/**
	 * 
	 * @param compareableObject  a comparable object from the comparator class
	 */
	public SortedDoubleLinkedList (Comparator<T> compareableObject) {
		super();
		comp = compareableObject;
	}
	
	/**
	 * 
	 * @param data  data to be added to the list
	 */
	public void add(T data) {
		Node entry = new Node(data);
		Node current = head;

		if (head == null) //if list is empty
		{
			head = entry; 
			tail = head;
		}
		else
		{
			// if entry should go before the head of list
			if (comp.compare(current.data, entry.data) > 0) 
			{
				entry.next = head; // item after entry points to head
				head.prev = entry; // item before head points to entry
				head = entry; // head is now entry
			}
			// if entry should go at tail of list
			else if (comp.compare(tail.data, entry.data) < 0)
			{
				tail.next = entry; // item after tail points to entry
				entry.prev = tail; // item before entry points to tail
				tail = entry; // tail is now entry
			}

			else { //otherwise traverse
				//while the current data is less than the entry
				while (comp.compare(current.next.data, entry.data) < 0)
				{
					current = current.next; //go to the next data/node
				}
				
				entry.next = current.next; //item after entry points to item after current
				entry.next.prev = entry; //item after entry, the back node points to entry
				current.next = entry; //item after current is entry
				entry.prev = current; //item before entry is current
			}
		}
		size++;
	}
	
	@Override
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	@Override
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();
	}
	
	@Override
	public BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);
	}
}
