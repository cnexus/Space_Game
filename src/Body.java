
public class Body {
	
	private int myForce;
	private int myX;
	private int myY;
	
	Body(int force, int x, int y){
		
		myForce = force;
		myX = x;
		myY = y;
		
	}
	
	public int getForce() {
		return myForce;
	}
	
	public int getX(){
		return myX;
	}
	
	public int getY(){
		return myY;
	}

}
