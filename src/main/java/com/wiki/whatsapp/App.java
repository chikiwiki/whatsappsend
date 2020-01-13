package com.wiki.whatsapp;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.wiki.whatsapp.messages.W;
import com.wiki.whatsapp.model.Contact;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	W w = new W();
    	//Editar y poner la ruta de la agenda
    	String agenda = "agenda.csv"; 
    	//Editar ruta del chromedriver
    	String pathDriver = "C:\\Users\\user\\eclipse-workspace\\WhatsappSend\\chromedriver.exe";
    	//Editar sms a enviar
    	String message = "Hola {}, no hay momento más adecuado para decir *gracias* y desearte unas felices fiestas y un *prospero año nuevo* lleno de salud y felicidad.";
    	//Editar foto a enviar
    	String pathImage = "C:\\Users\\user\\Pictures\\bb_cocina.jpg";
    	//Enviar sms con imagen true, sin imagen false;
    	boolean hasImage = true;
    	
    	//abrir whatsapp
        WebDriver driver = w.openWhatsapp(pathDriver);
        
        //Guardar contactos en list
        List<Contact> listContact = w.readContact(agenda);
        
        if(listContact != null && listContact.size() > 0)
        {
	        //enviar mensajes
	        w.sendSpam(driver, listContact, message, pathImage, hasImage);
        }
        
        //cerrar whatsapp
        w.closeWhatsapp(driver);
        
        System.out.println("fin de whatsapp send");
    }
}
