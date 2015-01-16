import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;


public class Game extends JPanel {

	int gameSpeed = 5;

	Thread th = new Thread() {
		public void run() {
			while(true) {
				
				ships.get(0).updateMove(bodies);
				ships.get(0).checkEdge();
				requestFocusInWindow();
				repaint();

				try {
					Thread.sleep(gameSpeed);
				} catch (InterruptedException e) {

				}

				//ships.get(0).moveX(10);
				//System.out.println("asdf");

			}
		}
	};


	public void init(){
		th.start();
	}


	ArrayList<Body> bodies = new ArrayList<Body>();
	ArrayList<Ship> ships = new ArrayList<Ship>();

	public Game() {
		Ship s = new Ship(100,100,0);
		addShip(s);
		this.addKeyListener(new ShipMover(s,this));
		this.addKeyListener(new ShipMover(s,this));
		populateBodies(2);

		setBackground(Color.WHITE);

		init();

	}
	
	public void populateBodies(int i){
		bodies.clear();
		for(i = 0; i<2; i++){
			addBody(new Body((int)(Math.random()*50),(int)(Math.random()*800),(int)(Math.random()*600)));
		}
	}

	public void paint(Graphics g) {

		
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;

		for(int i = 0;i<bodies.size();i++){

			if(bodies.get(i).getForce()>0){
				g2d.fillOval(bodies.get(i).getX(), bodies.get(i).getY(), 10,10);
			}
			else{
				g2d.drawOval(bodies.get(i).getX(), bodies.get(i).getY(), 10,10);
			}
			String forceString = "" + Math.abs(bodies.get(i).getForce());
			g2d.drawString(forceString, bodies.get(i).getX(),bodies.get(i).getY());

		}

		for(int i = 0;i<ships.size();i++){

			g2d.fillRect((int)ships.get(i).getX(),(int) ships.get(i).getY(), 10,10);
			for(int j = 0; j<ships.get(i).trace.size();j++){
				g2d.drawOval((int)ships.get(i).trace.get(j)[0], (int)ships.get(i).trace.get(j)[1], 1, 1);
			}

		}
	}


	public void addBody(Body b){

		bodies.add(b);

	}

	public void addShip(Ship s){

		ships.add(s);

	}

}

class ShipMover implements KeyListener{
	private Ship ship;
	private Game game;
	public ShipMover(Ship s, Game g){
		ship = s;
		game = g;
	}
	
	public void keyPressed(KeyEvent k) {
		int pAmount = 50;
		char c = Character.toUpperCase(k.getKeyChar());
		//System.out.println("Key = " + c);
		switch(c){
		case 'W':
			ship.moveY(-10);
			break;
		case 'S':
			ship.moveY(10);
			break;
		case 'A':
			ship.moveX(-10);
			break;
		case 'D':
			ship.moveX(10);
			break;
		case 'Z':
			ship.brake();
			break;
		case 'X':
			game.gameSpeed = 0;
			break;
		case 'C':
			ship.clearTrace();
			break;
		case 'R':
			ship.brake();
			ship.brake();
			ship.brake();
			game.populateBodies(3);
		}
	}

	public void keyReleased(KeyEvent k) {
		char c = Character.toUpperCase(k.getKeyChar());
		switch(c){
		case 'X':
			game.gameSpeed = 5;
		}
		
	}

	public void keyTyped(KeyEvent k) {
	}
	
}
