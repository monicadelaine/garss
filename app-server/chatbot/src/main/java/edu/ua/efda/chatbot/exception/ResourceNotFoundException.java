package edu.ua.efda.chatbot.exception;

public class ResourceNotFoundException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3706220392136774922L;

	public ResourceNotFoundException() {
    }
 
    public ResourceNotFoundException(String msg) {
        super(msg);
    }    

}
