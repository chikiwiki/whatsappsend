 package com.wiki.whatsapp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.io.output.FileWriterWithEncoding;

public class Log
{
  public Log() {}
  
  public static List<String> readFile(String file)
  {
    FileReader fr = null;
    BufferedReader br = null;
    List<String> lineas = new java.util.ArrayList();
    


    try
    {
      br = new BufferedReader(new java.io.InputStreamReader(new FileInputStream(file), "UTF-8"));
      

      String linea;
      
      while ((linea = br.readLine()) != null) {
        lineas.add(linea);
      }
    } catch (Exception e) {
      e.printStackTrace();
      


      try
      {
        if (fr != null) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (fr != null) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return lineas;
  }
  

  public void writeFile(String line, String file)
  {
    FileWriterWithEncoding fichero = null;
    PrintWriter pw = null;
    try
    {
      File myFile = new File(file);
      if (!myFile.exists()) {
        myFile.createNewFile();
      }
      
      fichero = new FileWriterWithEncoding(myFile, "UTF-8", true);
      pw = new PrintWriter(fichero);
      
      pw.println(line);
    }
    catch (Exception e) {
      e.printStackTrace();
      

      try
      {
        if (fichero != null)
          fichero.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (fichero != null)
          fichero.close();
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
  }
  
  public static String readProperty(String property)
  {
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    String file = "config.properties";
    String result = null;
    

    try
    {
      archivo = new File(file);
      fr = new FileReader(archivo);
      br = new BufferedReader(fr);
      
      String linea;
      
      while ((linea = br.readLine()) != null) {
        if (linea.startsWith(property))
        {
          result = linea.split("=")[1];
          result = result.trim();
          break;
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      


      try
      {
        if (fr != null) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (fr != null) {
          fr.close();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
    return result;
  }
}
 
 