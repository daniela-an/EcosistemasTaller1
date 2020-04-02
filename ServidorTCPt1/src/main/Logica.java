package main;

import java.io.Console;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Logica  implements Observer{
	
	private PApplet app;
	private Nave p1, p2;
	private PImage background;
	private int loser, p2Score;
	
	public Logica(PApplet app) {
		this.app = app;
		this.p1 = new Nave(app, 100, app.height/2, 1);
		this.p2 = new Nave(app, app.width-150, app.height/2, 2);
		this.background = app.loadImage("src/data/juego.png");
		loser = 0;
		p2Score = 0;
	}

	public void pintar() {
		app.imageMode(PApplet.CORNER);
		app.image(background, 0, 0);
		this.p1.pintar();
		this.p2.pintar();

		valColsión(p1, p2);
		valColsión(p2, p1);

		valLoser(p1);
		valLoser(p2);

		app.textSize(30);
		app.text(p1.getScore()+"", 150, 60);
		app.text(p2.getScore()+"", 950, 60);
	}

	private void valLoser(Nave p){
		if(p.getVida() <= 0){
			loser = p.getPlayer();
		}
	}

	public void valColsión(Nave p, Nave e){
		ArrayList<Bala> balasP = p.getBalas();
		for (int i = 0; i < balasP.size(); i++) {
			if(PApplet.dist(balasP.get(i).getPos().x, balasP.get(i).getPos().y, e.getPos().x, e.getPos().y) < 50){
				e.restarVida();
				balasP.remove(i);
				p.setBalas(balasP);
				return;
			}
		}
	}

	public void mover(PVector dir){
		p1.mover(dir);
	}

	public void dispararP1(){
		p1.disparar();
	}

	public int getLoser(){
		return loser;
	}
	
	public int getP2Score(){
		return p2Score;
	}

	public void setP2Score(int p2Score){
		this.p2Score = p2Score;
	}

	@Override
	public void update(Observable o, Object arg) {
		String line = (String) arg;

		if(line.equals("up") ) {
			p2.mover(new PVector(1,0));

		}else if(line.equals("left") ){
			p2.mover(new PVector(-1,0));

		}else if(line.equals("right") ){
			p2.mover(new PVector(0,-1));

		}else if(line.equals("down") ){
			p2.mover(new PVector(0,1));

		}else if(line.equals("shoot") ){
			p2.disparar();

		}else if(line.equals("score")){
			p2Score = p2.getScore();
		}
		
	}

}
