
public class QueueUnderflowException extends RuntimeException {

	public QueueUnderflowException() {
		super("The queue is empty, can't show or remove any elements");
	}
}
