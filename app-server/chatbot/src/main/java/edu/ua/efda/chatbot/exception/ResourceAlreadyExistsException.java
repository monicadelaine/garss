package edu.ua.efda.chatbot.exception;

public class ResourceAlreadyExistsException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 3269224365100742642L;

	public ResourceAlreadyExistsException() {
    }
 
    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }

}
