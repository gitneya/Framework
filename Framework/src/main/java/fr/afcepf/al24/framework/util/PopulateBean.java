/**
 * 
 */
package fr.afcepf.al24.framework.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import fr.afcepf.al24.framework.exception.FrameworkException;
import fr.afcepf.al24.framework.exception.FrameworkException.Error_code;

/**
 * @author yanick
 *
 */
public class PopulateBean {

	private static Logger log = Logger.getLogger(PopulateBean.class);
	private static final String setter_prefix = "set";
	private static final String getter_prefix = "get";

	private static PopulateBean reference = null;

	/**
	 * 
	 * 
	 * @return
	 */
	protected boolean checkIfSetterExist(String setterMethodName, Object o) {
		boolean exist = false;
		Class<?> c = o.getClass();
		//Get all methods
		Method[] allMethods = c.getDeclaredMethods();
		
		for (Method m : allMethods) {
			//Check method name
			if (m.getName().equals(setterMethodName)) {
				exist = true;
				break;
			}
		}
		return exist;
	}
	/**
	 * Convertit une chaine de caracatere en un type primitif
	 * 
	 * @param type
	 * @param value
	 * @return
	 */
	protected Object primitiveConvertStringParameter(String type, String value) {
		Object o = new Object();
		switch (type) {
		case "int" : o = Integer.parseInt(value); 
		break;

		case "boolean" : o = Boolean.parseBoolean(value);
		break;

		case "double" : o = Double.parseDouble(value);
		break;

		case "float" : o = Float.parseFloat(value);
		break;

		case "char" : o = value.charAt(0);
		break;

		case "byte" : o = Byte.parseByte(value);
		break;

		case "short" : o = Short.parseShort(value);
		break;

		case "long" : o = Long.parseLong(value);
		break;
		}
		//TODO Type Void ???
		return o;
	}
	/**
	 * Cette méthode fixe les attributs d'un objet en appelant les setters de l'objet
	 *  
	 * @param parametersMap : liste des noms de parametres et de leurs valeurs
	 * @param o : objet cible
	 * 
	 */
	public void setAttributes(Map<String, String[]> parametersMap, Object o) throws FrameworkException {
		log.debug("PopulateBean.getPopulateBean setAttributes for " + o.getClass().getCanonicalName());
		//for each parameters, call setter(s)
		Iterator<Entry<String, String[]>> it = parametersMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String,String[]> pair = (Map.Entry<String,String[]> )it.next();
			String attributeName = pair.getKey();
			String [] attributeValues = pair.getValue();
			String setterMethodName = setter_prefix + attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);

			if (!checkIfSetterExist(setterMethodName, o)) {
				throw new FrameworkException(Error_code.ERROR_SETTER_NOT_FOUND, "Impossible de trouver un setter pour l'attribut : "
						+ attributeName + " de la classe : " + o.getClass().getName());
			}
			try {

				Class<?> c = o.getClass();
				//Get all methods
				Method[] allMethods = c.getDeclaredMethods();
				
				for (Method m : allMethods) {
					//Check method name
					if (!m.getName().equals(setterMethodName)) {
						continue;
					}
					//Method is found
					log.debug("Method signature : " + m.toGenericString());
					log.debug("ReturnType : " +  m.getReturnType());
					log.debug("GenericReturnType : " + m.getGenericReturnType());
					//Get parameter types
					Class<?>[] pType  = m.getParameterTypes();
					Type[] gpType = m.getGenericParameterTypes();
					Object [] allParameters = new Object[gpType.length];
					//Debug
					for (int i = 0; i < pType.length; i++) {
						log.debug("ParameterType : " + pType[i].toString());
					}
					for (int i = 0; i < gpType.length; i++) {
						log.debug("GenericParameterType : " + gpType[i].toString());
					}
					//Check if same number of parameters
					if (gpType.length == attributeValues.length) { 
						for (int i = 0; i < gpType.length; i++) {
							
							if (pType[i].isPrimitive()) {
								allParameters[i] = primitiveConvertStringParameter(gpType[i].toString(), attributeValues[i]);
							} else if ( gpType[i].toString().equals("class java.lang.String")) {
								allParameters[i] = attributeValues[i];
							}
						}
					} else {
						log.debug("Nombre de parametres incorrectes !!!!!!!!!");
					}
					
					Class<?>[] xType  = m.getExceptionTypes();
					Type[] gxType = m.getGenericExceptionTypes();
					for (int i = 0; i < xType.length; i++) {
						log.debug("ExceptionType : " + xType[i].toString());
						log.debug("GenericExceptionType : " + gxType[i].toString());
					}
					//Invoke method
					m.invoke(o, allParameters);
					
				}

			} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public static synchronized PopulateBean getPopulateBean() {
		if (reference == null) {
			log.debug("PopulateBean.getPopulateBean new instance !!");
			reference = new PopulateBean();
		} else {
			log.debug("PopulateBean.getPopulateBean instance already exists !!");
		}

		return reference;
	}
}
