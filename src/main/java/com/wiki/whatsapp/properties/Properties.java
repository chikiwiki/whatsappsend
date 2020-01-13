package com.wiki.whatsapp.properties;

import java.util.Locale;
import java.util.ResourceBundle;

public class Properties {
	
	static ResourceBundle prop = ResourceBundle.getBundle("com.wiki.whatsapp.resources.config", new Locale("es","ES"));
	
	public static String CLASS_NAME_OPEN 			= prop.getString("class.name.open");
	public static String ID_NAME_OPEN 				= prop.getString("id.name.open");
	
	public static String XPATH_SEARCH_CONTACT 		= prop.getString("xpath.search.contact");
	public static String CLASS_CONTACT_TITLE_CHAT 	= prop.getString("class.contact.title.chat");
	public static String XPATH_CONTACT_TITLE_INI 	= prop.getString("xpath.contact.title.ini");
	public static String XPATH_CONTACT_TITLE_FIN 	= prop.getString("xpath.contact.title.fin");
	
	public static String XPATH_SEND_TEXT 			= prop.getString("xpath.send.text");
	
	public static String XPATH_SEND_IMG 			= prop.getString("xpath.send.img");
	public static String XPATH_SEND_IMG_SEARCH		= prop.getString("xpath.send.img.search");
	public static String XPATH_SEND_IMG_ATTACH 		= prop.getString("xpath.send.img.attach");
	
}
