/**
 * 
 */
package fr.afcepf.al24.testframework.actionform;

import fr.afcepf.al24.framework.actionForm.IActionForm;
import fr.afcepf.al24.framework.annotation.FormAction;

/**
 * @author yanick
 *
 */
@FormAction
public class FormClient implements IActionForm {

	/* (non-Javadoc)
	 * @see fr.afcepf.al24.framework.actionForm.IActionForm#validate()
	 */
	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.afcepf.al24.framework.actionForm.IActionForm#reset()
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

}
