/**
 * 
 */
package fr.afcepf.al24.testframework.actionform;

import org.apache.log4j.Logger;

import fr.afcepf.al24.framework.actionForm.IActionForm;

/**
 * @author yanick
 *
 */
public class FormLogin implements IActionForm {

	private Logger log = Logger.getLogger(FormLogin.class);
	
	private String login;
	private String pwd;
	private int codeNum;
	
	/* (non-Javadoc)
	 * @see fr.afcepf.al24.framework.actionForm.IActionForm#validate()
	 */
	@Override
	public boolean validate() {

		boolean isValid = false;
		
		log.debug("FormLogin.validate");
		
		if (login != null && pwd != null && !login.isEmpty() && !pwd.isEmpty())
			isValid = true;
		
		return isValid;
	}

	/* (non-Javadoc)
	 * @see fr.afcepf.al24.framework.actionForm.IActionForm#reset()
	 */
	@Override
	public void reset() {
		log.debug("FormLogin.reset");
		login = null;
		pwd = null;
		codeNum = -1;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the codeNum
	 */
	public int getCodeNum() {
		return codeNum;
	}

	/**
	 * @param codeNum the codeNum to set
	 */
	public void setCodeNum(int codeNum) {
		this.codeNum = codeNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FormLogin [login=" + login + ", pwd=" + pwd + ", codeNum="
				+ codeNum + "]";
	}
}
