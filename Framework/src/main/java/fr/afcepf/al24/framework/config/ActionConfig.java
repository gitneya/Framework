/**
 * 
 */
package fr.afcepf.al24.framework.config;

/**
 * @author yanick
 *
 */
public class ActionConfig {

	private String nom;
	private String urlAssociee;
	private String nomForm;
	
	
	/**
	 * 
	 */
	public ActionConfig() {
	}
	/**
	 * @param nom
	 * @param urlAssociee
	 * @param nomFormu
	 */
	public ActionConfig(String nom, String urlAssociee, String nomFormu) {
		this.nom = nom;
		this.urlAssociee = urlAssociee;
		this.nomForm = nomFormu;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the urlAssociee
	 */
	public String getUrlAssociee() {
		return urlAssociee;
	}
	/**
	 * @param urlAssociee the urlAssociee to set
	 */
	public void setUrlAssociee(String urlAssociee) {
		this.urlAssociee = urlAssociee;
	}
	/**
	 * @return the nomFormu
	 */
	public String getNomForm() {
		return nomForm;
	}
	/**
	 * @param nomFormu the nomFormu to set
	 */
	public void setNomForm(String nomFormu) {
		this.nomForm = nomFormu;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActionConfig [nom=" + nom + ", urlAssociee=" + urlAssociee
				+ ", nomForm=" + nomForm + "]";
	}
}
