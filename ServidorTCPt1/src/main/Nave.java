package main;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Nave {

	private PVector pos;
	private PApplet app;
	private PImage img, bala, star;
	private ArrayList<Bala> balas = new ArrayList<Bala>();
	private int player, score;
	private float vida;
	
	public Nave(PApplet app, float x, float y, int player) {
		this.app = app;
		this.player = player;
		this.pos = new PVector(x,y);
		this.img = app.loadImage("src/data/Nave"+player+".png");
		this.bala = app.loadImage("src/data/Bala"+player+".png");
		this.star = app.loadImage("src/data/Vida"+player+".png");
		this.vida = 5;
		this.score = 10000;
	}
	
	public void pintar() {
		this.app.imageMode(PApplet.CENTER);
		paintBalas();		
		this.app.image(this.img, this.pos.x, this.pos.y);
		paintVida();
	}

	public void paintVida(){
		for (int i = 0; i < Math.ceil(vida); i++) {
			app.image(star, player==2? (app.width + ((-30*i) - 47)) : ((30*i) + 47), app.height-38);
		}
	}

	public void restarVida(){
		vida -= .3;
		score -= 100;
	}

	public void paintBalas(){
		for (int i = 0; i < this.balas.size(); i++) {
			this.balas.get(i).mover();
			balas.get(i).pintar();
			
			if(this.balas.get(i).getPos().x > this.app.width || this.balas.get(i).getPos().x < 0){
				this.balas.remove(i);
				return;
			}
		}
	}

	public void disparar(){
		this.balas.add(new Bala(this.app, pos.copy(), this.bala, this.player));
	}

	public void mover(PVector dir){
		dir.mult(10);
		PVector target = pos.add(dir);
		dir.normalize();
		dir.mult(0.000005f);
		while(pos.x != target.x && pos.y != target.y){
			pos.add(dir);
		}
	}

	public ArrayList<Bala> getBalas(){
		return balas;
	}

	public void setBalas(ArrayList<Bala> balas){
		this.balas = balas;
	}

	public PVector getPos(){
		return pos;
	}

	public float getVida(){
		return vida;
	}

	public int getPlayer(){
		return player;
	}

	public int getScore(){
		return score;
	}
}
