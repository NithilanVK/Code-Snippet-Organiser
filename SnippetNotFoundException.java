package Exception;

public class SnippetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SnippetNotFoundException() {
		super("Snippet Not Found !");
	}
	
	public SnippetNotFoundException (String message) {
		super(message);
	}	
}
