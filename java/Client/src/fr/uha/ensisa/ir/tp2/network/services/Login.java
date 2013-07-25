package fr.uha.ensisa.ir.tp2.network.services;
import java.net.*;
import java.io.*;

import fr.uha.ensisa.ir.tp2.network.communication.Reader;
import fr.uha.ensisa.ir.tp2.network.communication.Writer;
 
public class Login {
 
    private Socket socket = null;
    public boolean state;
     
    public Login(Socket s, String login, String password){
        socket = s;
        state = false;
        
        try {
            
            Writer myWriter = new Writer(socket.getOutputStream());
            Reader myReader = new Reader(socket.getInputStream());
            
            myWriter.writeDiscr('C');
            myWriter.writeUTF(login);
            myWriter.writeUTF(password);
            
            if(myReader.readServer())
            {
            	System.out.println("loged");
            	state = true;
            }
            else
            {
            	System.out.println("login failed");
            }
             
            } catch (IOException e) {
                 
                System.err.println("Le serveur ne répond plus ");
            }
            
    }
     
 
}