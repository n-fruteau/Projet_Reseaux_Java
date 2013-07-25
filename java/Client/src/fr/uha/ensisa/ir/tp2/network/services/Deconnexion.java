package fr.uha.ensisa.ir.tp2.network.services;
import java.net.*;
import java.io.*;
import fr.uha.ensisa.ir.tp2.network.communication.Reader;
import fr.uha.ensisa.ir.tp2.network.communication.Writer;

 
public class Deconnexion {
 
    private Socket socket = null;
    public boolean state;
     
    public Deconnexion(Socket s){
        socket = s;
        state = false;
        
        try {
            
            Writer myWriter = new Writer(socket.getOutputStream());
            Reader myReader = new Reader(socket.getInputStream());
            
            myWriter.writeDiscr('q');
            
            if(myReader.readServer())
            {
            	System.out.println("deconnected");
            	state = true;
            }
            else
            {
            	System.out.println("Deconnexion failed");
            }
             
            } catch (IOException e) {
                 
                System.err.println("Le serveur ne répond plus ");
            }
            
    }

 
}