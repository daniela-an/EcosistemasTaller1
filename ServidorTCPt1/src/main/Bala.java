package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Bala {

	private PVector pos;
	private PApplet app;
    private PImage img;
    private int vel;
	
	public Bala(PApplet app, PVector pos, PImage img, int vel) {
		this.app = app;
		this.pos = pos;
        this.img = img;
        this.vel = vel == 2? -5 : 5;
	}
	
	public void pintar() {
		app.imageMode(PApplet.CENTER);
		app.image(this.img, this.pos.x, this.pos.y);
	}

	public void mover(){
		this.pos.x = this.pos.x + this.vel;
	}

	public PVector getPos(){
		return pos;
	}
}