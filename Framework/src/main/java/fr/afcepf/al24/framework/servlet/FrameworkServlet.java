/**
 * 
 */
package fr.afcepf.al24.framework.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import fr.afcepf.al24.framework.action.IAction;
import fr.afcepf.al24.framework.actionForm.IActionForm;
import fr.afcepf.al24.framework.exception.FrameworkException;
import fr.afcepf.al24.framework.factory.ActionFactory;
import fr.afcepf.al24.framework.util.PopulateBean;


/**
 * @author yanick
 *
 */
public class FrameworkServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3914869053538437779L;
	private Logger log = Logger.getLogger(FrameworkServlet.class);

	private ActionFactory actionfactory;
	private PopulateBean populateBean;

	/**
	 * 
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {

		log.info("FrameworkServlet.init : ");
		actionfactory = ActionFactory.getActionFactory();
		populateBean = PopulateBean.getPopulateBean();

	}
	/**
	 * 
	 * @param session
	 * @param formName
	 * @return
	 */
	protected boolean checkFormAlreadyInstanciated(HttpSession session, String formName) {
		
		boolean instanciated = false;
		IActionForm form  = (IActionForm) session.getAttribute(formName);
		if (form != null) {
			instanciated  = true;
		}
		
		return instanciated;
	}
	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext context = req.getSession().getServletContext();
		//InputStream resourceContent = context.getResourceAsStream("/WEB-INF/framework.xml");
		log.info("FrameworkServlet.handleRequest Servlet context path = " + context.getContextPath());

		//Enumerate parameters
		Enumeration<String> paramNames = req.getParameterNames();
		for (Enumeration<String> e = paramNames; e.hasMoreElements();) {
			String paramName = e.nextElement();
			String [] paramValues =  req.getParameterValues(paramName);
			String paramValuesStr = "";
			for (int i = 0; i < paramValues.length; i++) {
				paramValuesStr = paramValuesStr + paramValues[i]; 
			}
			
			log.info("FrameworkServlet.handleRequest : param : " + paramName + " = " + paramValuesStr);
		}
		
		String nextPage = "";
		IAction action = null;
		IActionForm form = null;
		
		try {
			
			String formName = actionfactory.getFormCanonicalName(req.getServletPath().replace("/", ""));
			if (checkFormAlreadyInstanciated(req.getSession(), formName)){
				log.debug("FrameworkServlet.handleRequest : Form " + formName + " existe dans la session.");
				form = (IActionForm) req.getSession().getAttribute(formName);
			} else {
				form = actionfactory.createForm(req.getServletPath().replace("/", ""));
				//Met l'objet formulaire dans la session
				req.getSession().setAttribute(form.getClass().getCanonicalName(), form);
			}
			
			//Fill form with parameters
			Map<String, String[]> parametersMap = req.getParameterMap();
			populateBean.setAttributes(parametersMap, form);
			
			form.validate();
			
			action = actionfactory.createAction(req.getServletPath().replace("/", ""));
			
			nextPage = action.execute(form, req, resp);
			
		} catch (FrameworkException e) {
			log.debug("FrameworkServlet.handleRequest : Exception : " + e.getMessage());
			e.printStackTrace();
			nextPage = "internalError.jsp";
		}

		log.debug("RequestDispatcher.............");
		RequestDispatcher requestDispatcher = req.getRequestDispatcher(nextPage);
		requestDispatcher.forward(req, resp);
	}
	/**
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.debug("FrameworkServlet.doGet request : " + req);
		log.debug("FrameworkServlet.doGet URI : " + req.getRequestURI());
		log.debug("FrameworkServlet.doGet URL : " + req.getRequestURL());
		log.debug("FrameworkServlet.doGet : Id Session : " + req.getSession().getId());
		log.debug("FrameworkServlet.doGet : Servlet Path : " + req.getServletPath());

		handleRequest(req, resp);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.debug("FrameworkServlet.doPost request : " + req);
		log.debug("FrameworkServlet.doPost URI : " + req.getRequestURI());
		log.debug("FrameworkServlet.doPost URL : " + req.getRequestURL());
		log.debug("FrameworkServlet.doPost : Id Session : " + req.getSession().getId());
		log.debug("FrameworkServlet.doPost : Servlet Path : " + req.getServletPath());

		handleRequest(req, resp);
	}
}
