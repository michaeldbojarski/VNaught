import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class VNaughtPanel extends JPanel {
	
	private double collisionAngle;//+Setter+Getter
	public void setCollisionAngle(double collisionAngleIn) {
		collisionAngle = collisionAngleIn;
	}
	public double getCollisionAngle() {
		return collisionAngle;
	}
	
	VNaughtPanel() {
		super();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);//Color of Hit Vehicle
		g.drawRect(140, 150, 40, 60);//Draw Hit Vehicle
		g.setColor(Color.RED);//Color of Colliding Vehicle
		g.drawRect(50, 150, 60, 40);//Draw Colliding Vehicle
		g.drawLine(110, 170, 140, 170);//Draw Collision Course Arrow
		g.drawLine(130, 160, 140, 170);
		g.drawLine(130, 180, 140, 170);
		
		//Tire Mark Arc Design
		double width = 30;
		double height = 70/getCollisionAngle();
		double x = 160 - (width/2);
		double y = 150 - (height/2);
		g.setColor(Color.BLACK);
		
		//Draw Trajectory based off Collision
		if (collisionAngle <= 0.5) {
			g.drawArc((int) x, (int) y, (int) width, (int) height, 180, -90);
		} else if (collisionAngle <= 1) {
			g.drawArc((int) x, (int) y, (int) width, (int) height, 180, -70);
		} else {
			g.drawArc((int) x, (int) y, (int) width, (int) height, 180, -50);
		}
	}
	
	public void saveImage(){
	    BufferedImage imagebuf=null;;
	    try {
	        imagebuf = new Robot().createScreenCapture(getBounds());
	    } catch (AWTException a) {
	    	a.getSuppressed();
	    }  
	    Graphics2D graphics2D = imagebuf.createGraphics();
	    paint(graphics2D);
	    try {
	        ImageIO.write(imagebuf,"jpeg", new File("save.jpeg"));
	    } catch (Exception e) {
	        System.out.println("Error Saving");
	    }
	}
}