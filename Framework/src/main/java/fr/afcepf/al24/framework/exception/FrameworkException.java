/**
 * 
 */
package fr.afcepf.al24.framework.exception;

/**
 * @author yanick
 *
 */
public class FrameworkException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439260224299384574L;
	private String message;
	
	public enum Error_code {
		ERROR_CODE
	}

	public FrameworkException(Error_code code, String message) {
		super();
		this.message = message;
	}; 

	
	
}
