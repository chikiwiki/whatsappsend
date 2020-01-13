package com.wiki.whatsapp.messages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.wiki.whatsapp.model.Contact;
import com.wiki.whatsapp.properties.Properties;
import com.wiki.whatsapp.util.Esperar;
import com.wiki.whatsapp.util.Log;

public class W {

	//lanzar proceso envio de whatsapp
	public void sendSpam(WebDriver driver, List<Contact> listContact, String message, String pathImage, boolean hasImage)
	{
		WhatsappTool wt = new WhatsappTool();
		
		for(Contact contact : listContact)
		{
			try {
				if(isValidNumber(contact.getNumberMobile())) {
					
					//Abrir conversacion por nombre
					String strHeader = wt.getHeaderName(driver); //almacena el nombre del contacto que aparece en la cabecera de la ventana principal
					boolean isConversationOk = wt.openConversationByNumberInAgenda(driver, contact.getNumberMobile(), strHeader);
					
					if(isConversationOk) {
						//Editar message
						String messageEdited = editMessage(message, contact.getNickName());
						
						if(!hasImage) {
							//Enviar message
							wt.writeMessageText(driver, messageEdited);
							System.out.println("Enviado a "+contact.getNickName()+" - "+contact.getNumberMobile());
						} else {
							//Enviar message + image
							wt.writeMessageImage(driver, pathImage, messageEdited);
							System.out.println("Enviado a "+contact.getNickName()+" - "+contact.getNumberMobile());
						}
					} else {
						System.out.println("******* ERROR numero de telefono no almacenado en agenda - sms no enviado a "+contact.getNickName()+" - "+contact.getNumberMobile());
					}
				} else {
					System.out.println("******* ERROR numero de telefono incorrecto [34ddddddddd] - sms no enviado a "+contact.getNickName()+" - "+contact.getNumberMobile());
				}
			} catch(Exception e) {
				System.out.println("******* ERROR no controlado - sms no enviado a "+contact.getNickName()+" - "+contact.getNumberMobile());
				e.printStackTrace();
			}
		}
		
	}
	
	
	//abrir whatsapp
	public WebDriver openWhatsapp(String pathDriver)
	{
		System.setProperty("webdriver.chrome.driver", pathDriver);
	     
		WebDriver driver = new ChromeDriver();
	     
	    driver.get("https://web.whatsapp.com/");
	    
	    do
	    {
	    	Esperar.waiting(5000L);
	    }
	    while (driver.findElements(By.className(Properties.CLASS_NAME_OPEN)).size() == 0 &&
	    		driver.findElements(By.id(Properties.ID_NAME_OPEN)).size() == 0);
	     
	     return driver;
	}
	
	//cerrar whatsapp
	public void closeWhatsapp(WebDriver driver)
	{
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	     
	     driver.quit();
	}
	

	//leer contactos de la agenda y guardarlos en una list<Contact>
	public List<Contact> readContact(String pathFile)
	{
		List<Contact> listContact = new ArrayList<Contact>();
		List<String> listLine = Log.readFile(pathFile);
		
		for(String line : listLine)
		{
			try {
				if(!line.trim().startsWith("//"))
				{
				    String[] arrayContact = line.split(";");
				    Contact contact = new Contact();
				    
				    if(arrayContact != null && arrayContact.length == 2)
				    {
				    	contact.setNickName(arrayContact[0]);
				    	contact.setNumberMobile(arrayContact[1]);
				    	listContact.add(contact);
				    } else {
				    	System.out.println("******* ERROR linea con numero de argumentos diferente a 2 [s*;34ddddddddd] al leer la agenda - linea "+line);
				    }
				}
			} catch(Exception e) {
				System.out.println("******* ERROR no controlado al leer la agenda - linea "+line);
				e.printStackTrace();
			}
		}
		
		return listContact;
	}
	
	public String editMessage(String message, String alias)
	{
		message = message.replace("{}", alias);
		
		return message;
	}
	
	public boolean isValidNumber(String phone) {
		
		if(phone == null || phone.length() != 11) {
			return false;
		} else if(!phone.startsWith("34")) {
			return false;
		}
		
		return true;
	}
	
	public boolean isPopupError(WebDriver driver) {
		
		if(driver.findElements(By.cssSelector("div[data-animate-modal-popup='true']")) == null || 
				driver.findElements(By.cssSelector("div[data-animate-modal-popup='true']")).size() == 0) {
			return false;
		}
		return true;
	}
	
	
}
