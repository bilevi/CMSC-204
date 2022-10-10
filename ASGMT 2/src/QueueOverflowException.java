
public class QueueOverflowException extends RuntimeException {
	
	public QueueOverflowException() {
		super("The queue is full, can't add any more elements");
	}
}
