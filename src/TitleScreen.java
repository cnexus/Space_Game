import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * @author Carlos Gonzales
 * Block Y
 * This program:
 *
 */

public class TitleScreen extends JPanel{
	private static final long serialVersionUID = 1L;
	private int delaySeconds;
	private String[] instructions;

	public TitleScreen(GravityGame parent){
		initInstructions();
		repaint();
		delaySeconds = 5;
		this.setBounds(parent.getBounds());
		this.setVisible(true);
		try {
			Thread.sleep(1000*delaySeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("after");
		parent.remove(this);
	}

	private void initInstructions() {
		instructions = new String[9];
		instructions[0] = "INSTRUCTIONS FOR THE GRAVITY GAME:";
		instructions[1] = "Move with the AWSD keys.";
		instructions[2] = "Other Keys: ";
		instructions[3] = " - Z: Brake";
		instructions[4] = " - X: Increase Game Speed";
		instructions[5] = " - C: Clear Ship Trace";
		instructions[6] = " - R: Add New Holes to Map";
		instructions[7] = "";
		instructions[8] = "Press \"V\" to go onto the next screen...";
	}

	public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		for(int i = 0; i < instructions.length; i++){
			g2d.setStroke(new BasicStroke((float) (Math.random()*1.3), BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2d.drawString(instructions[i], 20, (this.getHeight()/instructions.length)*i);
		}
	}
}
