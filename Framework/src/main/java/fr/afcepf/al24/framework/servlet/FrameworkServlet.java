/**
 * 
 */
package fr.afcepf.al24.framework.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import fr.afcepf.al24.framework.factory.ActionFactory;


/**
 * @author Stagiaire
 *
 */
public class FrameworkServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3914869053538437779L;
	private Logger log = Logger.getLogger(FrameworkServlet.class);
	
	private ActionFactory actionfactory;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		log.info("FrameworkServlet.init : ");
		actionfactory = ActionFactory.getActionFactory();
		
	}
	
	private void simpleresponse(HttpServletResponse resp) {
		
		resp.setContentType("text/html");
	    PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    out.println("<html>");
	    out.println("<body>");
	    out.println("<head>");
	    out.println("<title>First Example</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Hello World!</h1>");
	    out.println("</body>");
	    out.println("</html>");
	    out.close();
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
		
		ServletContext context = req.getSession().getServletContext();
		//InputStream resourceContent = context.getResourceAsStream("/WEB-INF/framework.xml");
		log.info("FrameworkServlet.doGet Servlet context path = " + context.getContextPath());
		
		//Enumerate parameters
		Enumeration<String> paramNames = req.getParameterNames();
		for (Enumeration<String> e = paramNames; e.hasMoreElements();) {
			log.info("FrameworkServlet.doGet : param : " + e.nextElement());
		}
		
		
		//resp.sendRedirect("/TestFramework/PremierePage.jsp");
		//resp.sendRedirect("/DeuxiemePage.jsp");
		/*
		log.debug("RequestDispatcher.............");
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/DeuxiemePage.jsp");
		requestDispatcher.forward(req, resp);
		*/
		simpleresponse(resp);
	    
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
