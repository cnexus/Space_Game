import java.util.ArrayList;


public class Ship {
	
	private double x,y,dir;
	
	private double dx,dy;
	
	public ArrayList<double[]> trace = new ArrayList<double[]>(10);
	
	
	Ship(int xIn, int yIn, int dirIn){
		x = xIn;
		y = yIn;		
		dir = dirIn;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void moveX(double val){
		x+= val;
	}
	
	public void moveY(double val){
		y+= val;
	}
	
	public void updateMove (ArrayList<Body> bodies){
		double[] temp = new double [2];
		
		temp[0]=x;
		temp[1]=y;
		
		trace.add(temp);
		
		
		
		if(trace.size()>=10000){
			trace.remove(0);
		}
		
		int maxSpeed = 20;
		//int basespeed = 300;
		int dcap = 1;
		double nextForce = 0;
		for(int i = 0; i<bodies.size();i++){
			
			double angle;
			double dis;
			
			Body b = bodies.get(i);
			
			angle = Math.atan2(b.getY()-y,b.getX()-x);
			dis = Math.sqrt((b.getY()-y)*(b.getY()-y)+(b.getX()-x)*(b.getX()-x));
			
			
			
			if(dis == 0){
				nextForce+=0;
			}else{
				nextForce += b.getForce()/dis;
			}
			
			if (Math.abs(nextForce)>= dcap){
				nextForce = dcap * nextForce/Math.abs(nextForce);
			}
			dx += nextForce*Math.cos(angle);
			dy += nextForce*Math.sin(angle);
		}
		double speed = Math.sqrt((dx*dx)+(dy*dy));
//		if(speed >= dcap){
//			
//			double c = dcap/(speed);
//			
//			dx = (int) (dx*c);
//			dy = (int) (dy*c);
//			
//		}
		//System.out.println(x+" "+y);
		
		
		
		moveShip();
		
	}
	
	public void checkEdge(){
		
		if(x>=790){
			x=10;
		}
		if(x<=5){
			x=780;
		}
		if(y>=550){
			y=10;
		}
		if(y<=5){
			y=540;
		}
		//System.out.println("asdf");
		
	}
	
	public void pulse(int xIn, int yIn){
		dx+=xIn;
		dy+=yIn;
		moveShip();
	}

	public void brake(){
		dx/=10;
		dy/=10;
	}
	
	public void moveShip(){
		moveX(dx/100);	
		moveY(dy/100);
	}
	
	public void clearTrace(){
		trace.clear();
		
	}
}
