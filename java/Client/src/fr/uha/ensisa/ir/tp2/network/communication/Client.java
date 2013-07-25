package fr.uha.ensisa.ir.tp2.network.communication;

import java.io.*;
import java.net.*;

import fr.uha.ensisa.ir.tp2.network.common.Element;
import fr.uha.ensisa.ir.tp2.network.services.*;


 
public class Client {
 
    public static Socket socket = null;
    private static boolean loginState = false;
    public static String login = "nico";
    public static String password ="nicopassword";
    public static String host = "localhost";
   
    public static boolean getLoginState()
    {
    	return loginState;
    }
    
    public static boolean connectClient() {
	        
	        try {
	        	if(!loginState)
	        	{
	        		System.out.println("Demande de connexion");
					socket = new Socket(host, 5678);
					Connexion connexion = new Connexion(socket);
			        Login log = new Login(socket, login, password);
			        loginState = (connexion.state&&log.state);
			        return loginState;
	        	}
	        	else
	        	{
	        		Deconnexion deconnexion = new Deconnexion(socket);
	        		loginState=(!deconnexion.state);
	        		return (loginState);
	        	}
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return false;
	        
    }
    
    public static void getMe(Element meBrute,Element bonus1,Element bonus2,Element bonus3) throws IOException {
    	if(loginState)
    	{
	    	new Me(socket, true, meBrute, bonus1, bonus2, bonus3);
	    	System.out.println("brutes");
    	}
	    
    }
    
    public static void getOther(Element meBrute,Element bonus1,Element bonus2,Element bonus3) throws IOException {
    	if(loginState)
    	{
	    	new Me(socket, false, meBrute, bonus1, bonus2, bonus3);
	    	System.out.println("brutes");
    	}
	    
    }
    
    public static Images getImage(boolean me) throws IOException {
    	Images images = null;
    	if(loginState)
    	{
	    	images = new Images(socket, me);
	    	System.out.println("images");
    	}
    	return images;
	    
    }
    
    public static boolean battle(boolean win) throws IOException {
    	Win bat = null;
    	if(loginState)
    	{
    		bat = new Win(socket, win);
	    	
    		return bat.state;
    	}
    	return false;
    }
 
}