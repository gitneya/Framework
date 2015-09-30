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
     *  * Identifiant unique.
     */
	private static final long serialVersionUID = -8439260224299384574L;
	private String message;
	private Error_code code;
	/**
	 * @author Stagiaire
	 *
	 */
	public enum Error_code {
		ERROR_CODE,
		ERROR_FORM_NOT_EXIST,
		ERROR_ACTION_NOT_EXIST,
		ERROR_SETTER_NOT_FOUND
	}

	/**
	 * FrameworkException : constructeur.
	 * @param paramCode : code d'erreur.
	 * @param paramMessage : message d'erreur.
	 */
	public FrameworkException(Error_code paramCode, String paramMessage) {
		super();
		this.message = paramMessage;
		this.code = paramCode;
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
