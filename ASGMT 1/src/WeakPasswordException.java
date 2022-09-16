
public class WeakPasswordException extends RuntimeException {

	public WeakPasswordException() {
		super("The password is OK but weak - it contains few than 10 characters");
	}
}
