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
public class LoginBean implements IAction {

	private Logger log = Logger.getLogger(LoginBean.class);
	
	/**
	 * 
	 */
	@Override
	public String execute(IActionForm form, HttpServletRequest req, HttpServletResponse resp) {
		
		FormLogin f = (FormLogin) form;
		String nextPage = "";
		
		log.debug("LoginBean.execute");
		if (form.validate()) {
			log.debug("LoginBean.execute : formulaire valide.");
			if (f.getLogin().equals("toto") && f.getPwd().equals("titi") 
					&&f.getCodeNum() == 123) {
				req.getSession().removeAttribute("message");
				nextPage = "succes.jsp";
			} else {
				log.debug("LoginBean.execute : authentification invalide.");
				req.getSession().setAttribute("message","Login invalide");
				nextPage = "connexion.jsp";
			}
		} else {
			log.debug("LoginBean.execute : authentification invalide.");
			req.getSession().setAttribute("message","Login invalide");
			nextPage = "connexion.jsp";
		}
		
		return nextPage;
	}

}
