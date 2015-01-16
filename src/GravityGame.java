import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GravityGame extends JFrame implements KeyListener{
	private boolean pressed;
	private static final long serialVersionUID = 1L;
	public GravityGame(String name){
		super(name);
		//System.out.println("here");
	}

	public static void main(String[] args) {
		GravityGame jf = new GravityGame("Gravity Game v0.7b");
		jf.setBounds(0,0, 800, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.add(new TitleScreen(jf));
		jf.add(new Game());
	}

	public boolean getPressed(){
		return pressed;
	}

	public void keyPressed(KeyEvent e) {
		char c = Character.toUpperCase(e.getKeyChar());
		System.out.println("c = " + c + " in jframe");
		if(c == 'V'){
			pressed = true;
		}
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
