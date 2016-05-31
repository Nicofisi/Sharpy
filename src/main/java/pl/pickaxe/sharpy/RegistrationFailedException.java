package pl.pickaxe.sharpy;

public class RegistrationFailedException extends Exception {
	private static final long serialVersionUID = -625497187168190058L;

	public RegistrationFailedException() {
		super();
	}

	public RegistrationFailedException(String message) {
		super(message);
	}

	public RegistrationFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RegistrationFailedException(Throwable cause) {
		super(cause);
	}
}
