/**
 * 
 */
package fr.afcepf.al24.framework.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Stagiaire
 *
 */
public interface IAction {

	public String execute(HttpServletRequest req, HttpServletResponse resp);
	
}
