package fr.uha.ensisa.ir.tp2.network.common;

public class Element {
	
	private int id;
	private String name;
	private int life;
	private int level;
	private int strench;
	private int speed;
	
	
public Element(int id, String name, int level, int life, int strench, int speed){
	this.id=id;
	this.name=name;
	this.life=life;
	this.level=level;
	this.strench=strench;
	this.speed=speed;
}

public Element(){  //constructeur vide
	this.id=0;
	this.name="";
	this.life=0;
	this.level=0;
	this.strench=0;
	this.speed=0;
}

public int getId(){
	return id;
}

public String getName(){
	return name;
}
public int getLife(){
	return life;
}
public int getLevel(){
	return level;
}
public int getStrench(){
	return strench;
}
public int getSpeed(){
	return speed;
}

public void setId(int id) {
	this.id = id;
}

public void setName(String name) {
	this.name = name;
}

public void setLife(int life) {
	this.life = life;
}

public void setLevel(int level) {
	this.level = level;
}

public void setStrench(int strench) {
	this.strench = strench;
}

public void setSpeed(int speed) {
	this.speed = speed;
}


}
