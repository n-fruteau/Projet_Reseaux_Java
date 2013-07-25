package fr.uha.ensisa.ir.tp2.network.services;

import java.net.*;
import java.io.*;

import fr.uha.ensisa.ir.tp2.network.common.Element;
import fr.uha.ensisa.ir.tp2.network.communication.Reader;
import fr.uha.ensisa.ir.tp2.network.communication.Writer;

 
public class Me {
 
    private Socket socket = null;
    public boolean state;
     
    public Me(Socket s,boolean me, Element meBrute, Element b1,Element b2,Element b3){
        socket = s;
        state = false;
        
        try {
            
            Writer myWriter = new Writer(socket.getOutputStream());
            Reader myReader = new Reader(socket.getInputStream());
            char discr;
            if(me) discr='b'; else discr='B';
            myWriter.writeDiscr(discr);
            if(myReader.readServer())
            {
            	System.out.println("Brute trouvée");
            	
            	//Brute
            	myReader.read();
            	meBrute.setName(myReader.getString());
            	
            	myReader.read();
            	meBrute.setLevel(myReader.getInt());
            	
            	myReader.read();
            	meBrute.setLife(myReader.getInt());
            	
            	myReader.read();
            	meBrute.setStrench(myReader.getInt());
            	
            	myReader.read();
            	meBrute.setSpeed(myReader.getInt());
           
            	
            	
            	/*meBrute = new Element(0, name, level, life, strench, speed); 
            	 * Ne marche pas ‡ cause de la portÈe des variable
            	 */
            	
            	//Bonus1
	            myReader.read();
	        	b1.setName(myReader.getString());
	        	
	        	myReader.read();
	        	b1.setLevel(myReader.getInt());
	        	
	        	myReader.read();
	        	b1.setLife(myReader.getInt());
	        	
	        	myReader.read();
	        	b1.setStrench(myReader.getInt());
	        	
	        	myReader.read();
	        	b1.setSpeed(myReader.getInt());
            	
            	//Bonus2
	        	myReader.read();
	        	b2.setName(myReader.getString());
	        	
	        	myReader.read();
	        	b2.setLevel(myReader.getInt());
	        	
	        	myReader.read();
	        	b2.setLife(myReader.getInt());
	        	
	        	myReader.read();
	        	b2.setStrench(myReader.getInt());
	        	
	        	myReader.read();
	        	b2.setSpeed(myReader.getInt());
            	
            	//Bonus3
	        	myReader.read();
	        	b3.setName(myReader.getString());
	        	
	        	myReader.read();
	        	b3.setLevel(myReader.getInt());
	        	
	        	myReader.read();
	        	b3.setLife(myReader.getInt());
	        	
	        	myReader.read();
	        	b3.setStrench(myReader.getInt());
	        	
	        	myReader.read();
	        	b3.setSpeed(myReader.getInt());
            	
            	state = true;
            }
            else
            {
            	System.out.println("Brute indisponible");
            }
             
            } catch (IOException e) {
                 
                System.err.println("Le serveur ne répond plus ");
            }
            
    }

 
}