/**
 * 
 */
package fr.afcepf.al24.testframework.actionbean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.afcepf.al24.framework.action.IAction;
import fr.afcepf.al24.framework.actionForm.IActionForm;

/**
 * @author yanick
 *
 */
public class ClientBean implements IAction {

	/* (non-Javadoc)
	 * @see fr.afcepf.al24.framework.action.IAction#execute(fr.afcepf.al24.framework.actionForm.IActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(IActionForm form, HttpServletRequest req,
			HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

}
