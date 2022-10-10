import java.util.*;

// Javadoc does not load due to override notation
/**
 * 
 * @author B. Leigh Vining
 *
 * @param <T>  Defines the type for the generic class
 */
public class MyQueue<T> implements QueueInterface<T> {
		
		private ArrayList<T> queueList;
		private int size;
		
		public MyQueue() {
			queueList = new ArrayList<>();
		}
		
		/**
		 * 
		 * @param size  the size of the queue
		 */
		public MyQueue(int size) {
			this.size = size;
			queueList = new ArrayList<>(size);
		}
		
		@Override
		public boolean isEmpty() {
			return queueList.isEmpty();
		}
		
		@Override
		public boolean isFull() {
			if (queueList.size() >= this.size)
			{
				return true;
			}
			else return false;
		}
		
		@Override
		public T dequeue() 
				throws QueueUnderflowException {	
			if (queueList.isEmpty())
			{
				throw new QueueUnderflowException();
			}
			else {
				T e = queueList.get(0);
				queueList.remove(0);
				return e;
			}
		}

		@Override
		public int size() {
			return queueList.size();
		}
		
		@Override
		public boolean enqueue(T e)
				throws QueueOverflowException {
			boolean added = false;
			
			if (queueList.size() >= this.size)
			{
				throw new QueueOverflowException();
			}
			else {
				queueList.add(e);
				added = true;
			}
			
			return added;
		}
		
		@Override
		public String toString() {
			String result = "";
			
			for (int i = 0; i < queueList.size(); i++)
			{
				result += (queueList.get(i));
			}
			return result;
		}
		
		@Override
		public String toString(String delimiter) {
			String result = "";
			
			for (int i = 0; i < queueList.size(); i++)
			{
				result += (queueList.get(i) + delimiter);
			}
			
			result = result.substring(0, (result.length() - 1));
			return result;
		}
		
		@Override
		public void fill(ArrayList<T> list) {
			
			for (int i = 0; i < list.size(); i++)
			{
				T e = list.get(i);
				queueList.add(e);
			}
		}

}
