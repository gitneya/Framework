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
	private Error_code code;
	
	public enum Error_code {
		ERROR_CODE,
		ERROR_FORM_NOT_EXIST,
		ERROR_ACTION_NOT_EXIST,
		ERROR_SETTER_NOT_FOUND
	}

	/**
	 * 
	 * @param code
	 * @param message
	 */
	public FrameworkException(Error_code code, String message) {
		super();
		this.message = message;
		this.code = code;
	}; 

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	/**
	 * @return the code
	 */
	public Error_code getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Error_code code) {
		this.code = code;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
