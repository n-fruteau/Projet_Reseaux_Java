package fr.uha.ensisa.ir.tp2.network.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class MyJdbc {
	
	  private Connection connect = null;
	  private Statement statement = null;
	  private ResultSet resultSet = null;
	  final String url = "jdbc:mysql://srv2.xpenradio.com/tp_brutes";
	 
	  
	  
	  public int  existU(String user, String mdp) throws Exception {
		    try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");

		      // PreparedStatements can use variables and are more efficient
		      String query = "SELECT * FROM users WHERE users.user=? and users.password=? ";
		      
		      PreparedStatement search = null;
		      search =connect.prepareStatement(query);

		      search.setString(1,user);
		      search.setString(2,mdp);
		      resultSet = search.executeQuery();
		      if( resultSet.next()){
		    	   return resultSet.getInt("id");
		      }
		      else {
		    	  return -1;
		      }         
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    } 
	  }
	  
	  public Element searchUB(int id, int idb) throws Exception {  // retourne une brute
		    try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");
		      
		      String query;
		      // PreparedStatements can use variables and are more efficient
		      if (idb<=0){
		    	  query = "SELECT * FROM brutes INNER JOIN users_brute ON brutes.`id brute` = users_brute.id_brute " +
		      		"INNER JOIN users ON users_brute.id_user=users.id  WHERE id=? ORDER BY RAND() LIMIT 1 ";
		      }
		      else {
		    	  query = "SELECT * FROM brutes INNER JOIN users_brute ON brutes.`id brute` = users_brute.id_brute " +
				      		"INNER JOIN users ON users_brute.id_user=users.id  WHERE id=? AND brutes.`id brute`!= ? ORDER BY RAND() LIMIT 1 ";
			  }
		      PreparedStatement prep = connect.prepareStatement(query);
		    
		      prep.setInt(1,id);
		      if (idb>0){
		    	  prep.setInt(2,idb);
		      }
		      
		      resultSet = prep.executeQuery();
		      if( resultSet.next()){
		    	  idb=resultSet.getInt("id brute");
		    	  Element brute = new Element(resultSet.getInt("id brute"),resultSet.getString("Nom"), resultSet.getInt("level"),resultSet.getInt("life"),resultSet.getInt("strench"), resultSet.getInt("speed"));
		    	   return brute;
		      }
		      else {
		    	  System.out.println("Brute non trouvée");
			      if (idb!=0){
			    	  query = "SELECT * FROM brutes INNER JOIN users_brute ON brutes.`id brute` = users_brute.id_brute " +
			      		"INNER JOIN users ON users_brute.id_user=users.id  WHERE id=? ORDER BY RAND() LIMIT 1 ";
				      PreparedStatement prep2 = connect.prepareStatement(query);
					    
				      prep2.setInt(1,id);
				      resultSet = prep2.executeQuery();
				      if( resultSet.next()){
				    	  idb=resultSet.getInt("id brute");
				    	  Element brute = new Element(resultSet.getInt("id brute"),resultSet.getString("Nom"), resultSet.getInt("level"),resultSet.getInt("life"),resultSet.getInt("strench"), resultSet.getInt("speed"));
				    	   return brute;
				      }
			      }
		    	  return null;
		      }
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    } 
	  }
	  
	  public Element notMe(int id,int idb) throws Exception { //retourne une brute adverse
		    try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");
		      
		      String query;
		      if (idb<0){
		      query = "SELECT * FROM brutes INNER JOIN users_brute ON brutes.`id brute` = users_brute.`id_brute` " +
		      		"INNER JOIN users ON users.`id` = users_brute.`id_user` WHERE users.id !=? ORDER BY RAND() LIMIT 1 ";
		      }
		      else{
		    	  query = "SELECT * FROM brutes INNER JOIN users_brute ON brutes.`id brute` = users_brute.`id_brute` " +
				      		"INNER JOIN users ON users.`id` = users_brute.`id_user` WHERE users.id !=? AND brutes.`id brute`!= ? ORDER BY RAND() LIMIT 1 ";
			  }
		      
		      PreparedStatement notMe = connect.prepareStatement(query);
		    
		      notMe.setInt(1,id);
		      if (idb>=0){
		    	  notMe.setInt(2,idb);
		      }
		      
		      resultSet = notMe.executeQuery();
		      
		      if( resultSet.next()){
		    	  Element brute = new Element(resultSet.getInt("id brute"),resultSet.getString("Nom"), resultSet.getInt("level"),resultSet.getInt("life"),resultSet.getInt("strench"), resultSet.getInt("speed"));
		    	   return brute;
		      }
		      else {
		    	  System.out.println("Brute non trouvée");
		    	  return null;
		      }
		        
		    } catch (Exception e) {
		      throw e;
		    } finally {
			      close();
			    } 
	  }
	  
	  
	  public Element searchBrute(int id) throws Exception {   //par rapport a son id brute
		    try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");

		      // PreparedStatements can use variables and are more efficient
		      String query = "SELECT * FROM brutes WHERE brutes.`id brute` =? ";
		      
		      PreparedStatement search = connect.prepareStatement(query);
		    
		      search.setInt(1,id);
		     
		       resultSet = search.executeQuery();
		       if( resultSet.first()){
		    	   Element brute = new Element(resultSet.getInt("id brute"),resultSet.getString("Nom"), resultSet.getInt("level"),resultSet.getInt("life"),resultSet.getInt("strench"), resultSet.getInt("speed"));
		    	   return brute;
		       }
		       System.out.println("Brute non trouvée");
		       return null;
		     
		    } catch (Exception e) {
		      throw e;
		    } finally {
			      close();
			    } 
	  }
	  
	  public  byte[] searchBruteImg(int id) throws Exception {   //par rapport a son id brute
		    try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");

		      // PreparedStatements can use variables and are more efficient
		      String query = "SELECT * FROM brutes_img WHERE brutes_img.`id_brute` =? ";
		      
		      PreparedStatement search = connect.prepareStatement(query);
		    
		      search.setInt(1,id);
		     
		       resultSet = search.executeQuery();
		       if( resultSet.next()){
		    	   byte[] img = resultSet.getBytes(2);
		    	   return img;
		       }
		       return null;
		     
		    } catch (Exception e) {
		      throw e;
		    } finally {
			      close();
			    } 
	  }
	  
	  
	  public  ArrayList<byte[]> searchBonusImg(int id) throws Exception {   //par rapport a son id brute
		    try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");

		      // PreparedStatements can use variables and are more efficient
		      String query = "SELECT bonuses_img.bonus_img FROM bonuses_img INNER JOIN bonuses " +
		      		"ON bonuses.id=bonuses_img.id_bonus INNER JOIN brute_bonus ON bonuses.id=brute_bonus.id_bonus " +
		      		"INNER JOIN brutes ON brute_bonus.id_brute=brutes.`id brute` WHERE id_brute=? ";
		      
		      PreparedStatement search = connect.prepareStatement(query);
		    
		      search.setInt(1,id);
		     
		       resultSet = search.executeQuery();
		       ArrayList<byte[]> array = new ArrayList<byte[]>();
			      int i = 0;
		       while( resultSet.next()){
		    	   byte[] img = resultSet.getBytes(1);
		    	   array.add(img);
		    	   i++; 
		       }
		       
		       switch (i) {
				
				case 3 :
					break;
				case 2 :
			    	array.add(null);
					break;
				case 1 :
					array.add(null);
					array.add(null);
					break;
				case 0 :
					array.add(null);
					array.add(null);
					array.add(null);
					break; 
		    }
		       return array;
		       
		    } catch (Exception e) {
		      throw e;
		    } finally {
			      close();
			    } 
	  }
	  
	  
	  public ArrayList<Element> searchBonus(int id) throws Exception { //par rapport a l'id de la brute 
		    try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");

		      // PreparedStatements can use variables and are more efficient
		      String query = "SELECT * FROM bonuses INNER JOIN brute_bonus ON bonuses.`id` = brute_bonus.`id_bonus` " +
		      		"INNER JOIN brutes ON brutes.`id brute`= brute_bonus.`id_brute` WHERE brutes.`id brute` = ?";
		      
		      PreparedStatement srch = connect.prepareStatement(query);
		    
		      srch.setInt(1,id);
		     
		      resultSet = srch.executeQuery();
		      ArrayList<Element> array = new ArrayList<Element>();
		      int i = 0;
		      while( resultSet.next()){
	    		  Element bonus = new Element(resultSet.getInt("id"),resultSet.getString("Nom"), resultSet.getInt("level"), resultSet.getInt("life"),resultSet.getInt("strench"), resultSet.getInt("speed"));
	    	  	 array.add(bonus);
	    	  	 i++;
	      	 }
		      
		      switch (i) {
				
				case 3 :
					break;
				case 2 :
			    	array.add( new Element());
					break;
				case 1 :
					array.add( new Element());
					array.add( new Element());
					break;
				case 0 :
					array.add( new Element());
					array.add( new Element());
					array.add( new Element());
					break;

		    }
		      return array;
		      } catch (Exception e) {
		      throw e;
		    } finally {
			      close();
			    } 
	  }
	  
	  
	  
	  
	  
	  
	  
	
	  
	  
	  /*public void changeBrute(int champ , int valeur) throws Exception{
		  try {
		    	
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      connect = DriverManager.getConnection(url, "tp_brutes", "frugabraukhou");

		      // PreparedStatements can use variables and are more efficient
		      String query = " UPDATE `brutes` SET `?`=? WHERE `brutes`.`id brute`=? ";
		      PreparedStatement addb = connect.prepareStatement(query);
		      String choix=null;
		      
		      switch (champ) {
				
				case 1 :
					choix="level";
					break;
				case 2 :
					choix="life";
					break;
				case 3 :
					choix="strench";
					break;
				case 4 :
					choix="speed";
					break;
		      }
		      if(choix!=null){
		      System.out.println(choix);
		      addb.setString(1, choix);
		      addb.setInt(2, valeur);
		      addb.setInt(3, idBrute);
		      addb.execute();
		      }
		      
		      
		    } catch (Exception e) {
		      throw e;
		    } finally {
		      close();
		    } 
		  
	  }*/
	  
	  
	  private void close() {
	    try {
	      if (resultSet != null) {
	        resultSet.close();
	      }

	      if (statement != null) {
	        statement.close();
	      }

	      if (connect != null) {
	        connect.close();
	      }
	    } catch (Exception e) {

	    }
	  }

	
	
	
	
}
