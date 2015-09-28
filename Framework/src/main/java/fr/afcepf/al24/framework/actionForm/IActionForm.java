/**
 * 
 */
package fr.afcepf.al24.framework.actionForm;

import fr.afcepf.al24.framework.exception.FrameworkException;

/**
 * @author Stagiaire
 *
 */
public interface IActionForm {

	public void validate() throws FrameworkException;
	public void reset();
}
