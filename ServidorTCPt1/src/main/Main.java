package main;

import comm.ComunicacionTCP;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Main extends PApplet{
	public static void main(final String[] args) {
		PApplet.main("main.Main");
	}
	
	ComunicacionTCP comm;
	String winner = "", loser = "";
	Logica log;
	PImage inst, win;
	int pantalla = 0;

	public void settings() {
		size(1200,700);
	}
	
	public void setup() {
		comm = new ComunicacionTCP();
		log = new Logica(this);
		comm.addObserver(log);
		Thread thread = new Thread(comm);
		thread.start();
		
		inst = loadImage("src/data/inst.png");
		win = loadImage("src/data/winner.png");
	}
	
	public void draw() {
		if(log.getP2Score()>0){
			comm.mandarMensaje(log.getP2Score()+"");
			log.setP2Score(0);
		}
		switch (pantalla) {
			case 0:
				imageMode(CORNER);
				image(inst, 0, 0);
				break;

			case 1:
				log.pintar();
				thereIsWinner();
				break;
			
			case 2:
				imageMode(CORNER);
				image(win, 0, 0);
				text(loser, 290, 590);
				text(winner, width-350, 590);
				break;
		
			default:
				break;
		}
	}

	public void thereIsWinner(){
		int val = log.getLoser();
		if( val > 0) {
			winner = "Jugador "+ (val ==2? 1 : 2);
			loser = "Jugador "+ val;
			pantalla = 2;
		}
	}

	public void mousePressed(){
		//System.out.println(mouseX + " : " + mouseY);
		if(mouseX > 528 && mouseX < 670 && mouseY > 598 && mouseY < 656 && pantalla == 0){
			pantalla = 1;
		}
	}

	public void keyPressed(){

		switch (keyCode) {
			case RIGHT:
				log.mover(new PVector(1,0));
				break;
			
			case LEFT:
				log.mover(new PVector(-1,0));
				break;

			case UP:
				log.mover(new PVector(0,-1));
			break;
			
			case DOWN:
				log.mover(new PVector(0,1));
				break;

			case 32:
				log.dispararP1();
				break;
		
			default:
				break;
		}
	}
}
