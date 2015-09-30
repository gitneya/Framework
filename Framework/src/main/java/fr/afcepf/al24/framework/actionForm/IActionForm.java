/**
 * 
 */
package fr.afcepf.al24.framework.actionForm;

import fr.afcepf.al24.framework.exception.FrameworkException;

/**
 * @author yanick
 *
 */
public interface IActionForm {

	public boolean validate();
	public void reset();
}
