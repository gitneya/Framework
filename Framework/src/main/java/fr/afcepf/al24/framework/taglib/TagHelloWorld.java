/**
 * 
 */
package fr.afcepf.al24.framework.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author yanick
 *
 */
public class TagHelloWorld extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -796295731130797816L;

	@Override
	public int doStartTag() throws JspException {
		try {
			this.pageContext.getOut().println("Hello world");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
