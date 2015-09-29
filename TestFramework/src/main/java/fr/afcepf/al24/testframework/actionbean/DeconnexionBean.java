/**
 * 
 */
package fr.afcepf.al24.testframework.actionbean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.afcepf.al24.framework.action.IAction;
import fr.afcepf.al24.framework.actionForm.IActionForm;
import fr.afcepf.al24.testframework.actionform.FormLogin;

/**
 * @author yanick
 *
 */
public class DeconnexionBean implements IAction {

	private Logger log = Logger.getLogger(DeconnexionBean.class);
	/* (non-Javadoc)
	 * @see fr.afcepf.al24.framework.action.IAction#execute(fr.afcepf.al24.framework.actionForm.IActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String execute(IActionForm form, HttpServletRequest req,
			HttpServletResponse resp) {
		log.debug("DeconnexionBean.execute.");
		FormLogin f = (FormLogin)form;
		f.reset();
		//Close session
		req.getSession().invalidate();
		form = null;
		return "index.html";
	}

}
