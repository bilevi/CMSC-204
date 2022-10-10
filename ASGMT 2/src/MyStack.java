import java.util.*;

//Javadoc does not load due to override notation
/**
 * 
 * @author B. Leigh Vining
 *
 * @param <T>  Defines the type for the generic class
 */
public class MyStack<T> implements StackInterface<T> {
		
		private ArrayList<T> stackList;
		private int size;
		
		public MyStack() {
			stackList = new ArrayList<>();
		}
		
		/**
		 * 
		 * @param size  The size of the stack
		 */
		public MyStack(int size) {
			this.size = size;
			stackList = new ArrayList<>(size);
		}

		@Override
		public boolean isEmpty() {
			return stackList.isEmpty();
		}
		
		@Override
		public boolean isFull() {
			if (stackList.size() >= this.size)
			{
				return true;
			}
			else return false;
			
		}
		
		@Override
		public T pop()
				throws StackUnderflowException {
			if (stackList.isEmpty())
			{
				throw new StackUnderflowException();
			}
			else {
				T e = stackList.get(stackList.size() - 1);
				stackList.remove(stackList.size() - 1);
				return e;
			}
		}

		@Override
		public T top() 
				throws StackUnderflowException {
			if (stackList.isEmpty())
			{
				throw new StackUnderflowException();
			}
			else {
				T e = stackList.get(stackList.size() - 1);
				stackList.get(stackList.size() - 1);
				return e;
			}
		}
		
		@Override
		public int size() {
			return stackList.size();
		}
		
		@Override
		public boolean push(T e) 
				throws StackOverflowException {
			boolean isPushed = false;
			
			if (stackList.size() >= this.size) {
				throw new StackOverflowException();
			}
			else {
				stackList.add(e);
				isPushed = true;
			}
			
			return isPushed;
		}

		@Override
		public String toString() {
			String result = "";
			
			for (int i = 0; i < stackList.size(); i++)
			{
				result += (stackList.get(i));
			}
			return result;
		}
		
		@Override
		public String toString(String delimiter) {
			String result = "";
			
			for (int i = 0; i < stackList.size(); i++)
			{
				result += (stackList.get(i) + delimiter);
			}
			
			result = result.substring(0, (result.length() - 1));
			return result;
		}
		
		@Override
		public void fill(ArrayList<T> list) {
			
			for (int i = 0; i < list.size(); i++)
			{
				T e = list.get(i);
				stackList.add(e);
			}
		}
}