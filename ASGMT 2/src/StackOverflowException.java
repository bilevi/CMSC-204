
public class StackOverflowException extends RuntimeException {

	public StackOverflowException() {
		super("The stack is full, can't add any more elements");
	}
}
