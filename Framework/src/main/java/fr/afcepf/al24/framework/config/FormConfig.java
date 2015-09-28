/**
 * 
 */
package fr.afcepf.al24.framework.config;

/**
 * @author Stagiaire
 *
 */
public class FormConfig {

	private String nomClasse;
	private String nomForm;
	
	/**
	 * 
	 */
	public FormConfig() {
	}
	/**
	 * @param nomClasse
	 * @param nomForm
	 */
	public FormConfig(String nomClasse, String nomForm) {
		this.nomClasse = nomClasse;
		this.nomForm = nomForm;
	}
	/**
	 * @return the nomClasse
	 */
	public String getNomClasse() {
		return nomClasse;
	}
	/**
	 * @param nomClasse the nomClasse to set
	 */
	public void setNomClasse(String nomClasse) {
		this.nomClasse = nomClasse;
	}
	/**
	 * @return the nomForm
	 */
	public String getNomForm() {
		return nomForm;
	}
	/**
	 * @param nomForm the nomForm to set
	 */
	public void setNomForm(String nomForm) {
		this.nomForm = nomForm;
	}
	@Override
	public String toString() {
		return "FormConfig [nomClasse=" + nomClasse + ", nomForm=" + nomForm
				+ "]";
	}
	
}
