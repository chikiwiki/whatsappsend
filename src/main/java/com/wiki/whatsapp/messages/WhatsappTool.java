package com.wiki.whatsapp.messages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.wiki.whatsapp.properties.Properties;
import com.wiki.whatsapp.util.Esperar;

public class WhatsappTool {

	
	//buscar contacto en whatsapp y abrir conversacion
	public boolean openConversationByNumberInAgenda(WebDriver driver, String contactName, String header)
	{
		//Barra de búsqueda de contactos en la columna izquierda de la ventana
		driver.findElement(By.xpath(Properties.XPATH_SEARCH_CONTACT)).clear();
		driver.findElement(By.xpath(Properties.XPATH_SEARCH_CONTACT)).sendKeys(new CharSequence[] { contactName });
				
		Esperar.waiting(3000L);
		
		//Clicar en el primer contacto de la lista
		try {
			List<WebElement> listChat = driver.findElements(By.className(Properties.CLASS_CONTACT_TITLE_CHAT));
			
			if(listChat != null && listChat.size() > 0)
			{
				listChat.get(listChat.size()-1).click();
				
				//Espera hasta que se cambie la ventana principal de contacto.
				//Con este script se evita que se envíen 2 sms a un mismo contacto
				String newHeader = null;
				do {
					newHeader = getHeaderName(driver);
					Esperar.waiting(3000L);
					if(newHeader == null) {
						return false;
					}
				}while(header != null && newHeader.equals(header));
				
				return true;
			}
		} catch (NoSuchElementException nsee) {
			nsee.printStackTrace();
		}
		
		return false;
	}
	
	//enviar un mensaje de whatsapp solo texto
	public boolean writeMessageText(WebDriver driver, String message)
	{
		try {
			Actions actions = new Actions(driver);
            actions.moveToElement(driver.findElement(By.xpath(Properties.XPATH_SEND_TEXT)));
            actions.click();
            actions.sendKeys(new CharSequence[] { message });
            actions.sendKeys(new CharSequence[] { Keys.ENTER });
            actions.build().perform();
            
            Thread.sleep(6000L);
            
            return true;
          }
          catch (Exception e)
          {
        	  e.printStackTrace();
          }
		
          return false;
	}
	
	//enviar un mensaje de whatsapp texto y foto
	public boolean writeMessageImage(WebDriver driver, String pathImage, String message)
	{
		try
        {
          driver.findElement(By.xpath(Properties.XPATH_SEND_IMG_SEARCH)).click();
          WebElement El = (WebElement)driver.findElements(By.xpath(Properties.XPATH_SEND_IMG_ATTACH)).get(0);
          El.sendKeys(new CharSequence[] { pathImage });
          
          Thread.sleep(4000L);
          
          Actions actions = new Actions(driver);
          actions.moveToElement(driver.findElement(By.xpath(Properties.XPATH_SEND_IMG)));
          actions.click();
          actions.sendKeys(new CharSequence[] { message });
          actions.sendKeys(new CharSequence[] { Keys.ENTER });
          actions.build().perform();
          
          	try {
          		Thread.sleep(10000L);
	  		} catch (InterruptedException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
          	
          return true;
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
        
       return false;
	}
	
	public String getHeaderName(WebDriver driver) {
		WebElement header = null;
		try {
			header = driver.findElement(By.className("_3fs0K"));
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return header.getText();
	}
}
