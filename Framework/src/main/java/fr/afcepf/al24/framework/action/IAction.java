/**
 * 
 */
package fr.afcepf.al24.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.al24.framework.actionForm.IActionForm;

/**
 * @author yanick
 *
 */
public interface IAction {

	/**
	 * 
	 * @param req request
	 * @param resp response
	 * @return url for navigation
	 */
	public String execute(IActionForm form, HttpServletRequest req, HttpServletResponse resp);
	
}
