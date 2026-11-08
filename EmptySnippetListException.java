package Exception;

public class EmptySnippetListException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public EmptySnippetListException(String message) {
        super(message);
    }
}
