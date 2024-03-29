package fr.uha.ensisa.ir.tp2.network.communication;
import java.io.*;
import java.net.*;
import java.util.Scanner;
 
 
public class Brute_ClientServeur implements Runnable {
 
    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc;
    private Thread t3, t4;
     
    public Brute_ClientServeur(Socket s){
        socket = s;
    }
     
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             
            sc = new Scanner(System.in);
             
            Thread t4 = new Thread(new Emission(out));
            t4.start();
            Thread t3 = new Thread(new Reception(in));
            t3.start();
         
            
             
        } catch (IOException e) {
            System.err.println("Le serveur distant s'est d�connect� !");
        }
    }
 
}