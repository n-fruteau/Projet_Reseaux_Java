package fr.uha.ensisa.ir.tp2.network.ihm;
import javax.swing.*;
import java.awt.*;
  
public class Panneau extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 6525161633002215526L;

public void paintComponent(Graphics g){
	  
	  
	//HEADER CONTROL
    g.drawString("Name", 20, 40);
    g.drawOval(130, 20, 75, 30);
    g.drawString("Me", 160, 40);
    g.drawOval(230, 20, 75, 30);
    g.drawString("Other", 250, 40);
    g.drawOval(330, 20, 75, 30);
    g.drawString("Win", 360, 40);
    g.drawOval(430, 20, 75, 30);
    g.drawString("Loose", 450, 40);
  }               
}