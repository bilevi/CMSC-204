
public class StackUnderflowException extends RuntimeException {

	public StackUnderflowException() {
		super("The stack is empty, can't show or remove any elements");
	}
}
