/**
 * 
 */
package fr.afcepf.al24.framework.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.afcepf.al24.framework.action.IAction;
import fr.afcepf.al24.framework.actionForm.IActionForm;
import fr.afcepf.al24.framework.config.ActionConfig;
import fr.afcepf.al24.framework.config.FormConfig;
import fr.afcepf.al24.framework.exception.FrameworkException;
import fr.afcepf.al24.framework.exception.FrameworkException.Error_code;

/**
 * @author yanick
 *
 */
public class ActionFactory {

	private static final String actionsListTag = "actions";
	private static final String actionListTag = "action";

	private static final int actionListTagChildren = 3;
	private static final String actionNameTag = "action-name";
	private static final String urlPatternTag = "url-pattern";
	private static final String formNameTag = "form-name";

	private static final String formsListTag = "forms";
	private static final String formListTag = "form";

	private static final int formListTagChildren = 2;
	private static final String formClassTag = "form-class";

	private static Logger log = Logger.getLogger(ActionFactory.class);
	private static ActionFactory reference;

	private static Map<String,ActionConfig> actionsMap;
	private static Map<String,FormConfig> formsMap;

	/**
	 * 
	 */
	private ActionFactory() {
		log.debug("ActionFactory constructeur");
	}

	/**
	 * 
	 * @param actionurl
	 * @return
	 * @throws FrameworkException
	 */
	public IAction createAction(String actionurl) throws FrameworkException {
		log.debug("ActionFactory.createAction : find action from url : " + actionurl);
		//Check if class action exists in Map
		IAction action = null;
		
		ActionConfig actionConfig = actionsMap.get(actionurl);
		if (actionConfig != null) {
			
			try {
				log.debug("ActionFactory.createAction : create instance of Action : " + actionConfig.getNom());
				action = (IAction) Class.forName(actionConfig.getNom()).newInstance();
				log.debug("ActionFactory.createAction : Action instance :" + action);
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Invoke method
			
		} else {
			log.debug("ActionFactory.createAction : No action found for this url.");
			throw new FrameworkException(Error_code.ERROR_ACTION_NOT_EXIST,"Class Action does not exist !");
		}
		return action; 
	}
	/**
	 * 
	 * @param actionurl
	 * @return
	 * @throws FrameworkException
	 */
	public IActionForm createForm(String actionurl) throws FrameworkException {
		log.debug("ActionFactory.createForm : find action from url : " + actionurl);
		//Check if class action exists in Map
		IActionForm form = null;
		
		ActionConfig actionConfig = actionsMap.get(actionurl);
		if (actionConfig != null) {
			
			try {
				FormConfig formConfig = formsMap.get(actionConfig.getNomForm());
				if (formConfig != null) {
					log.debug("ActionFactory.createForm : create instance of Form : " + formConfig.getNomClasse());
					form = (IActionForm) Class.forName(formConfig.getNomClasse()).newInstance();
					log.debug("ActionFactory.createForm : Form instance :" + form);
				} else {
					log.debug("ActionFactory.createForm : No form found for this url.");
					throw new FrameworkException(Error_code.ERROR_FORM_NOT_EXIST,"Class Form does not exist !");
				}
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Invoke method
			
		} else {
			log.debug("ActionFactory.createForm : No action found for this url.");
			throw new FrameworkException(Error_code.ERROR_CODE,"Class Action does not exist !");
		}
		
		return form; 
	}
	/**
	 * 
	 * @param filename : nom du fichier XML de configuration du framework
	 * @return Element : element racine du fichier xml
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	private static Element getRootFromXmlDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		Element root = null;
		if (is != null) {

			DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = xmlFactory.newDocumentBuilder();
			Document document = builder.parse(is);
			root = document.getDocumentElement();
			is.close();
		}

		return root; 

	}

	/**
	 * 
	 * @param root
	 * @return
	 */
	private static void readXmlConfigurationActions(Element root){

		log.debug("Root : <" + root.getNodeName() + ">");
		NodeList listNodesActions = root.getElementsByTagName(actionsListTag);

		//Traite les balises <actions>
		log.debug("Nombre de noeuds <" + actionsListTag + " > : " + listNodesActions.getLength());

		for (int i = 0; i < listNodesActions.getLength(); i++) {

			//Traite les balises <action>
			Node currentActions = listNodesActions.item(i);
			log.debug("\tTraite le noeud <"+ actionsListTag + "> " + (i + 1) + ") Node name=" + currentActions.getNodeName());

			NodeList listNodesAction = currentActions.getChildNodes();

			log.debug("\tNombre de noeuds enfants : " + listNodesAction.getLength());

			for (int j = 0, indexAction = 1; j < listNodesAction.getLength(); j++) {

				Node childActions = listNodesAction.item(j);

				if (childActions.getNodeType() == Node.ELEMENT_NODE && childActions.getNodeName().equals(actionListTag)) {

					log.debug("\t\tTraite le noeud <" + actionListTag + "> " + ( indexAction++)  + " Node name=" + childActions.getNodeName());

					NodeList elements = childActions.getChildNodes();
					log.debug("\t\tNombre de noeuds enfants : " + elements.getLength());
					ActionConfig ac = new ActionConfig();

					for (int k = 0, nElement = 0; k < elements.getLength(); k++) {

						Node conf = elements.item(k);

						if (conf.getNodeType() == Node.ELEMENT_NODE) {
							log.debug("\t\t\tNoeud de type Element:" + conf.getNodeName());
							if (conf.getNodeName().equals(actionNameTag) ) {
								ac.setNom(conf.getTextContent());
								nElement++;
							}
							if (conf.getNodeName().equals(urlPatternTag) ) {
								ac.setUrlAssociee(conf.getTextContent());
								nElement++;
							}
							if (conf.getNodeName().equals(formNameTag)) {
								ac.setNomForm(conf.getTextContent());
								nElement++;
							}
						}
						if (nElement == actionListTagChildren) {

							actionsMap.put(ac.getUrlAssociee(),ac);
							nElement = 0;
							log.debug("readXmlConfiguration : " + ac.toString());
						}
					}
				}
			}
		}

		log.debug("Nombre d'actions dans le fichier XML : " + actionsMap.size());
		return ;
	}
	/**
	 * 
	 * @param root
	 * @return
	 */
	private static void readXmlConfigurationForms(Element root){

		log.debug("Root : <" + root.getNodeName() + ">");
		NodeList listNodesForms = root.getElementsByTagName(formsListTag);

		//Traite les balises <forms>
		log.debug("Nombre de noeuds <" + formsListTag + " > : " + listNodesForms.getLength());

		for (int i = 0; i < listNodesForms.getLength(); i++) {

			//Traite les balises <form>
			Node currentForms = listNodesForms.item(i);
			log.debug("\tTraite le noeud <"+ formsListTag + "> " + (i + 1) + ") Node name=" + currentForms.getNodeName());

			NodeList listNodesForm = currentForms.getChildNodes();

			log.debug("\tNombre de noeuds enfants : " + listNodesForm.getLength());

			for (int j = 0, indexForm = 1; j < listNodesForm.getLength(); j++) {

				Node childForms = listNodesForm.item(j);

				if (childForms.getNodeType() == Node.ELEMENT_NODE && childForms.getNodeName().equals(formListTag)) {

					log.debug("\t\tTraite le noeud <" + formListTag + "> " + ( indexForm++)  + " Node name=" + childForms.getNodeName());

					NodeList elements = childForms.getChildNodes();
					log.debug("\t\tNombre de noeuds enfants : " + elements.getLength());
					FormConfig fc = new FormConfig();

					for (int k = 0, nElement = 0; k < elements.getLength(); k++) {

						Node conf = elements.item(k);

						if (conf.getNodeType() == Node.ELEMENT_NODE) {
							log.debug("\t\t\tNoeud de type Element:" + conf.getNodeName());
							if (conf.getNodeName().equals(formNameTag) ) {
								fc.setNomForm(conf.getTextContent());
								nElement++;
							}
							if (conf.getNodeName().equals(formClassTag) ) {
								fc.setNomClasse(conf.getTextContent());
								nElement++;
							}
						}
						if (nElement == formListTagChildren) {

							formsMap.put(fc.getNomForm(),fc);
							nElement = 0;
							log.debug("readXmlConfiguration : " + fc.toString());
						}
					}
				}
			}
		}
		log.debug("Nombre de forms dans le fichier XML : " + formsMap.size());
		return ;
	}
	/**
	 * 
	 * @return
	 */
	public static synchronized ActionFactory getActionFactory() {
		if (reference == null) {
			log.debug("ActionFactory.getActionFactory new instance !!");
			reference = new ActionFactory();
			if (reference != null) {
				try {
					actionsMap = new HashMap<String,ActionConfig>();
					formsMap =  new HashMap<String,FormConfig>();
					Element root = getRootFromXmlDocument("framework.xml");
					if (root != null) {
						readXmlConfigurationActions(root);
						readXmlConfigurationForms(root);
					}
				} catch (ParserConfigurationException | SAXException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			log.debug("ActionFactory.getActionFactory instance already exists !!");
		}

		return reference;
	}
}