package fr.uha.ensisa.ir.tp2.network.ihm;

import javax.imageio.ImageIO;

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import fr.uha.ensisa.ir.tp2.network.common.Element;
import fr.uha.ensisa.ir.tp2.network.communication.Client;
import fr.uha.ensisa.ir.tp2.network.services.ImageUtils;
import fr.uha.ensisa.ir.tp2.network.services.Images;

public class BruteIHM extends JFrame implements ActionListener{
     /**
	 * 
	 */
	private static final long serialVersionUID = 9102814663967400286L;
	private int nbclics=0;
     private JLabel l=new JLabel(" "+nbclics );
     public JButton connectButton;
     public JLabel userName;
     public JPanel p;
     public BufferedImage meImageView, bonus1Picture, bonus2Picture,bonus3Picture, otherImageView, otherbonus1Picture, otherbonus2Picture, otherbonus3Picture;
     public JLabel otherpicBonus1,otherpicBonus2,otherpicBonus3,meName,picBonus1, picBonus2, picBonus3, meImageLabel,otherImageLabel, meLevel, meLife, meStrength, meSpeed, otherName, otherLevel, otherLife, otherStrength, otherSpeed ;
     
     public BruteIHM(){
    	  setTitle("Les Brutes");
          setSize(800,450);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          
          p=new JPanel(new BorderLayout());
          
          Font bold = new Font("serif", Font.BOLD, 20);
          
         
          addWindowListener(new WindowAdapter() {
              public void windowClosing(WindowEvent e) {
            	  //FERMETURE DE LA SOCKET A LA FERMETURE DE LA FENETRE
            	  if(Client.getLoginState()){
        	  		Client.connectClient();
          		}
                System.exit(0);
              }
            });
          
          //------------------------------\\
          //------- HEADER CONTROLS ------\\
          //------------------------------\\
          JLabel nameTitle=new JLabel("Name" );
          nameTitle.setBounds(20, 30, 50, 30);
          p.add(nameTitle);
          
          
          userName=new JLabel("..." );
          userName.setBounds(70, 30, 50, 30);
          p.add(userName);
          
          JButton meButton=new JButton("Me");
          meButton.setBounds(150, 30, 100, 30);
          meButton.addActionListener(new meAction());
          p.add(meButton);
          
          JButton otherButton=new JButton("Other");
          otherButton.setBounds(250, 30, 100, 30);
          otherButton.addActionListener(new otherAction());
          p.add(otherButton);
          
          JButton winButton=new JButton("Win");
          winButton.setBounds(350, 30, 100, 30);
          winButton.addActionListener(new winAction());
          p.add(winButton);
          
          JButton looseButton=new JButton("Loose");
          looseButton.setBounds(450, 30, 100, 30);
          looseButton.addActionListener(new looseAction());
          p.add(looseButton);
          
          JLabel stateTitle=new JLabel("State" );
          stateTitle.setBounds(650, 30, 50, 30);
          p.add(stateTitle);

          connectButton=new JButton("Connect");
          connectButton.setBounds(690, 30, 100, 30);
          connectButton.addActionListener(new connectAction());
          p.add(connectButton);
          
          
          //------------------------------\\
          //--------- HEADER TABLE -------\\
          //------------------------------\\
          
          JLabel meTitle=new JLabel("Me" );
          meTitle.setBounds(20, 80, 100, 30);
          meTitle.setFont(bold);
          p.add(meTitle);
          
          JLabel otherTitle=new JLabel("Other" );
          otherTitle.setBounds(400, 80, 100, 30);
          otherTitle.setFont(bold);
          p.add(otherTitle);
          
        //------------------------------\\
        //---------- LEFT TABLE --------\\
        //------------------------------\\
          
          meName=new JLabel("Name : ----" );
          meName.setBounds(50, 110, 200, 30);
          p.add(meName);
          
          meLevel=new JLabel("Level : 0" );
          meLevel.setBounds(50, 130, 200, 30);
          p.add(meLevel);
          
          meLife=new JLabel("Life : 0" );
          meLife.setBounds(50, 150, 200, 30);
          p.add(meLife);
          
          meStrength=new JLabel("Strength : 0" );
          meStrength.setBounds(50, 170, 200, 30);
          p.add(meStrength);
          
          meSpeed=new JLabel("Speed : 0" );
          meSpeed.setBounds(50, 190, 200, 30);
          p.add(meSpeed);
          
          JLabel meBonuses=new JLabel("Bonuses" );
          meBonuses.setBounds(20, 270, 200, 30);
          p.add(meBonuses);
          
          
          
   		try {
			meImageView = ImageIO.read(new File("blank.png"));
			meImageLabel = new JLabel(new ImageIcon( meImageView ));
			meImageLabel.setBounds(199,97,120,170);
			p.add(meImageLabel);
			
			bonus1Picture = ImageIO.read(new File("blank.png"));
			picBonus1 = new JLabel(new ImageIcon( bonus1Picture ));
			picBonus1.setBounds(20,320,70,70);
			p.add(picBonus1);
			
			bonus2Picture = ImageIO.read(new File("blank.png"));
			picBonus2 = new JLabel(new ImageIcon( bonus2Picture ));
			picBonus2.setBounds(100,320,70,70);
			p.add(picBonus2);
			
			bonus3Picture = ImageIO.read(new File("blank.png"));
			picBonus3 = new JLabel(new ImageIcon( bonus3Picture ));
			picBonus3.setBounds(180,320,70,70);
			p.add(picBonus3);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		
   		//------------------------------\\
        //---------- RIGHT TABLE -------\\
        //------------------------------\\
          
          otherName=new JLabel("Name : -----" );
          otherName.setBounds(450, 110, 200, 30);
          p.add(otherName);
          
          otherLevel=new JLabel("Level : 0" );
          otherLevel.setBounds(450, 130, 200, 30);
          p.add(otherLevel);
          
          otherLife=new JLabel("Life : 0" );
          otherLife.setBounds(450, 150, 200, 30);
          p.add(otherLife);
          
          otherStrength=new JLabel("Strength : 0" );
          otherStrength.setBounds(450, 170, 200, 30);
          p.add(otherStrength);
          
          otherSpeed=new JLabel("Speed : 0" );
          otherSpeed.setBounds(450, 190, 200, 30);
          p.add(otherSpeed);
          
          JLabel otherBonuses=new JLabel("Bonuses" );
          otherBonuses.setBounds(450, 270, 200, 30);
          p.add(otherBonuses);
          
          //IMAGE DE FOND (INTERFACE)
   		try {
			otherImageView = ImageIO.read(new File("blank.png"));
			otherImageLabel = new JLabel(new ImageIcon( otherImageView ));
			otherImageLabel.setBounds(599,97,120,170);
			p.add(otherImageLabel);
			
			
			otherbonus1Picture = ImageIO.read(new File("blank.png"));
			otherpicBonus1 = new JLabel(new ImageIcon( otherbonus1Picture ));
			otherpicBonus1.setBounds(450,320,70,70);
			p.add(otherpicBonus1);
			
			otherbonus2Picture = ImageIO.read(new File("blank.png"));
			otherpicBonus2 = new JLabel(new ImageIcon( otherbonus2Picture ));
			otherpicBonus2.setBounds(530,320,70,70);
			p.add(otherpicBonus2);
			
			otherbonus3Picture = ImageIO.read(new File("blank.png"));
			otherpicBonus3 = new JLabel(new ImageIcon( otherbonus3Picture ));
			otherpicBonus3.setBounds(610,320,70,70);
			p.add(otherpicBonus3);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
   	 try {
			BufferedImage myPicture = ImageIO.read(new File("interface.png"));
			JLabel picLabel10 = new JLabel(new ImageIcon( myPicture ));
			picLabel10.setBounds(0,0,811,458);
			p.add(picLabel10);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
          p.add(l);
          setContentPane(p);
          this.setVisible(true);
          
          
          
}
     
     
     //------------------------------\\
     //---------- LISTERNERS --------\\
     //------------------------------\\
     
     
     public void actionPerformed(ActionEvent e){
          nbclics++;
          l.setText("nombre de clics: "+Integer.toString
          (nbclics));
     }
     
     
     class meAction implements ActionListener {
    	  
    	    public void actionPerformed(ActionEvent e) {
    	        try {
					Element meBrute = new Element();
					Element bonus1 = new Element();
					Element bonus2 = new Element();
					Element bonus3 = new Element();
					Client.getMe(meBrute, bonus1, bonus2, bonus3);
					
					meName.setText("Name : "+meBrute.getName());
					meLevel.setText("Level : "+meBrute.getLevel());
					meLife.setText("Life : "+meBrute.getLife());
					meStrength.setText("Strength : "+meBrute.getStrench());
					meSpeed.setText("Speed : "+meBrute.getSpeed());
					
					Images images = Client.getImage(true);
					byte[] imageBrute = images.getImageBrute();
					byte[] imageB1 = images.getImageB1();
					byte[] imageB2 = images.getImageB2();
					byte[] imageB3 = images.getImageB3();
					
					meImageView = ImageUtils.decodeToImage(imageBrute);
					meImageLabel.setIcon(new ImageIcon(meImageView));
					p.revalidate();
					
					bonus1Picture = ImageUtils.decodeToImage(imageB1);
					picBonus1.setIcon(new ImageIcon(bonus1Picture));
					p.revalidate();
					
					bonus2Picture = ImageUtils.decodeToImage(imageB2);
					picBonus2.setIcon(new ImageIcon(bonus2Picture));
					p.revalidate();
					
					bonus3Picture = ImageUtils.decodeToImage(imageB3);
					picBonus3.setIcon(new ImageIcon(bonus3Picture));
					p.revalidate();
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    }
     
    class otherAction implements ActionListener {
   	  
 	    public void actionPerformed(ActionEvent e) {
	        try {
				Element meBrute = new Element();
				Element bonus1 = new Element();
				Element bonus2 = new Element();
				Element bonus3 = new Element();
				Client.getOther(meBrute, bonus1, bonus2, bonus3);
				
				otherName.setText("Name : "+meBrute.getName());
				otherLevel.setText("Level : "+meBrute.getLevel());
				otherLife.setText("Life : "+meBrute.getLife());
				otherStrength.setText("Strength : "+meBrute.getStrench());
				otherSpeed.setText("Speed : "+meBrute.getSpeed());
				
				Images images = Client.getImage(false);
				byte[] imageBrute = images.getImageBrute();
				byte[] imageB1 = images.getImageB1();
				byte[] imageB2 = images.getImageB2();
				byte[] imageB3 = images.getImageB3();
				
				otherImageView = ImageUtils.decodeToImage(imageBrute);
				otherImageLabel.setIcon(new ImageIcon(otherImageView));
				p.revalidate();
				
				otherbonus1Picture = ImageUtils.decodeToImage(imageB1);
				otherpicBonus1.setIcon(new ImageIcon(otherbonus1Picture));
				p.revalidate();
				
				otherbonus2Picture = ImageUtils.decodeToImage(imageB2);
				otherpicBonus2.setIcon(new ImageIcon(otherbonus2Picture));
				p.revalidate();
				
				otherbonus3Picture = ImageUtils.decodeToImage(imageB3);
				otherpicBonus3.setIcon(new ImageIcon(otherbonus3Picture));
				p.revalidate();
				
				
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
    }
    
    class winAction implements ActionListener {
     	  
 	    public void actionPerformed(ActionEvent e) {
  	       try {
			if(Client.battle(true)){
				JOptionPane.showMessageDialog(null, "Ho my god ! You win ;)");
			   }
			   else{
				   JOptionPane.showMessageDialog(null, "Erreur : vous ne pouvez pas gagner ");
			   }
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erreur : vous ne pouvez pas gagner ");
			e1.printStackTrace();
		}
  	    }
    }
    
    class looseAction implements ActionListener {
    	public void actionPerformed(ActionEvent e) {
	       try {
			if(Client.battle(false)){
				JOptionPane.showMessageDialog(null, "Vous perdez, mais ne restez pas sur un echec ...");
			   }
			   else{
				   JOptionPane.showMessageDialog(null, "Erreur : vous ne pouvez pas perdre ");
			   }
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Erreur : vous ne pouvez pas perdre ");
			e1.printStackTrace();
		}
	    }
    }
    
    class connectAction implements ActionListener {
     	 
 	    public void actionPerformed(ActionEvent e) {
 	       if(Client.connectClient()){
 	    		connectButton.setText("Logout");
 	    		userName.setText(Client.login);
 	       }
 	       else{
 	    	  connectButton.setText("Connect");
 	    	  
 	    	  try {
				otherImageView =  ImageIO.read(new File("blank.png"));
			
				otherImageLabel.setIcon(new ImageIcon(otherImageView));
				
				otherpicBonus1.setIcon(new ImageIcon(otherImageView));
				
				otherpicBonus2.setIcon(new ImageIcon(otherImageView));
				
				otherpicBonus3.setIcon(new ImageIcon(otherImageView));
				
				
				meImageLabel.setIcon(new ImageIcon(otherImageView));
				
				picBonus1.setIcon(new ImageIcon(otherImageView));
				
				picBonus2.setIcon(new ImageIcon(otherImageView));
				
				picBonus3.setIcon(new ImageIcon(otherImageView));
				
				
 	    	 } catch (IOException e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}
 	    	  
 	    	 otherLevel.setText("Level : 0");
 	    	otherLife.setText("Life : 0");
 	    	otherSpeed.setText("Speed : 0");
 	    	otherStrength.setText("Strength : 0");
 	    	otherName.setText("Name : -----");
 	    	
 	    	meLevel.setText("Level : 0");
 	    	meLife.setText("Life : 0");
 	    	meSpeed.setText("Speed : 0");
 	    	meStrength.setText("Strength : 0");
 	    	meName.setText("Name : -----");
 	    	 p.revalidate();
 	    	  
 	    	  
 	       }
 	       p.revalidate();
 	    }

    }

     public static void main(String[] args) 
     {
    	 try {
			UIManager.setLookAndFeel(
			            UIManager.getSystemLookAndFeelClassName());
			new BruteIHM();
    	 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     } 
     
}