// Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

class VNaughtFrame extends JFrame {
	private int coefficientFric;
	private static final double KPH_CONVERTER = 1.60934; //From http://www.asknumbers.com/MilesToKilometersConversion.aspx
	private static final double GRAVITY = 9.80665; //From https://en.wikipedia.org/wiki/Gravity_of_Earth
	private static final double FT_TO_M = 0.3048; //From http://www.rapidtables.com/convert/length/feet-to-meter.htm
	private static final double M_TO_MILE = 0.000621371192237; //From http://www.convertunits.com/from/meters/to/miles
	private Calculate CBHandler;
	private Draw VSHandler;
	private Conditions conditionH;
	private JComboBox conversionCB, conditionsCB;
	private JTextField arcHeightTF, distanceTF, velocityTF, angleTF;
	Dimension dimensions = Toolkit.getDefaultToolkit().getScreenSize();
	
	public VNaughtFrame() {
		init();
	}
	public void init() {
	   
	   setBounds(0,0,1000,500);
	   setLocation(dimensions.width/2 - getSize().width/2, dimensions.height/2 - getSize().height/2);
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   
	   //Event/Item handlers
	   CBHandler = new Calculate();
	   VSHandler = new Draw();
	   conditionH = new Conditions();
      
	   //Labels
	   JLabel velocityTitle, velocityJL, distanceJL, arcHeightJL, conditionsJL, angleJL,
	   buffer1 = new JLabel(""), buffer2 = new JLabel(""), buffer3 = new JLabel(""), buffer4 = new JLabel(""),
	   buffer5 = new JLabel(""), buffer6 = new JLabel(""), buffer7 = new JLabel(""), buffer8 = new JLabel(""),
	   buffer9 = new JLabel(""), buffer10 = new JLabel(""), buffer11 = new JLabel("");
      
	   Container panel = getContentPane();
      
	   //Arrays for conditions/conversions
	   String[] conditions = {"Sunny & Dry","Light Rain","Moderate Rain","Heavy Rain","Ice","Snow"};
	   String[] conversions = {"MPH","KMH"};
      
	   panel.setLayout(new GridLayout(8,3,4,4));
      
	   //JButtons
	   JButton calculateJB = new JButton("Calculate");
			   calculateJB.setBorderPainted(false);
	   JButton visualJB = new JButton("Draw a Visual");
	   		   visualJB.setBorderPainted(false);
      
	   //Conditions
	   conditionsCB = new JComboBox(conditions);
	   conversionCB = new JComboBox(conversions);
      
	   //JLabels
	   velocityTitle = new JLabel("Velocity Calculator",SwingConstants.CENTER);
	   velocityJL = new JLabel("Velocity",SwingConstants.CENTER);
	   conditionsJL = new JLabel("Conditions",SwingConstants.CENTER);
	   distanceJL = new JLabel("Distance between beggining and end points of skid mark",SwingConstants.CENTER);
	   arcHeightJL = new JLabel("Height of arc",SwingConstants.CENTER);
	   angleJL = new JLabel("Angle of elevation of vehicle",SwingConstants.CENTER);
      
	   //JTextFields
	   velocityTF = new JTextField("");
	   distanceTF = new JTextField("<In Feet>");
	   arcHeightTF = new JTextField("<In Feet>");
	   angleTF = new JTextField("<In Degrees>");
      
	   //Adding listeners
	   conditionsCB.addItemListener(conditionH);
	   calculateJB.addActionListener(CBHandler);
	   visualJB.addActionListener(VSHandler);
      
	   panel.add(buffer1);
	   panel.add(velocityTitle);
	   panel.add(buffer2);
	   panel.add(distanceJL);
	   panel.add(conditionsJL);
	   panel.add(arcHeightJL);
	   panel.add(distanceTF);
	   panel.add(conditionsCB);
	   panel.add(arcHeightTF);
	   panel.add(buffer3);
	   panel.add(angleJL);
	   panel.add(buffer4);
	   panel.add(buffer5);
	   panel.add(angleTF);
	   panel.add(buffer6);
	   panel.add(buffer7);
	   panel.add(velocityJL);
	   panel.add(buffer8);
	   panel.add(buffer9);
	   panel.add(velocityTF);
	   panel.add(buffer10);
	   panel.add(buffer11);
	   panel.add(calculateJB);
	   panel.add(visualJB);
   	}
	
	private String convert(double velocity) {
	   double v = velocity;
	   String vString;
   
	   if (conversionCB.getSelectedIndex() == 1) {
		   v *= KPH_CONVERTER;
	   }
	   vString = String.format("%1$.1f",v);
	   return vString;
	}
   
	private double toMeter(double feet) {
	   double meters;
	   meters = feet * FT_TO_M;
	   return meters;
	}
   
	private double toMiles(double meter) {
		double miles;
		miles = meter * M_TO_MILE;
		return miles;
	}
    //Finds Initial Velocity of collision based on user input.
	private class Calculate implements ActionListener {
   
	   public void actionPerformed(ActionEvent e) {
		   	double velocity;
		   	double height = Double.parseDouble(arcHeightTF.getText());
		   	double distance = Double.parseDouble(distanceTF.getText());
		   	double radius;
		   	double angleOfElevation = Double.parseDouble(angleTF.getText());
		   	double frictCalculation;
         
		   	radius = ((height / 2) + ((Math.pow(distance,2)) / (8 * height)));
		   	frictCalculation = ((coefficientFric * Math.cos(angleOfElevation)) + Math.sin(angleOfElevation));
		   	velocity = Math.sqrt((toMeter(radius) * GRAVITY * frictCalculation));
		   	velocityTF.setText(velocity + " Miles Per Hour");
	   }                                                
	}
	//Draws a Helpful Visual based on user input.
	private class Draw implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			try {
				double height = Double.parseDouble(arcHeightTF.getText());
			   	double distance = Double.parseDouble(distanceTF.getText());
			   	double angleOfElevation = Double.parseDouble(angleTF.getText());
			   	double radius = ((height / 2) + ((Math.pow(distance,2)) / (8 * height)));
			   	double frictCalculation = ((coefficientFric * Math.cos(angleOfElevation)) + Math.sin(angleOfElevation));
			   	double velocity = Math.sqrt((toMeter(radius) * GRAVITY * frictCalculation));
			   	double angularVelocity = (velocity/(toMeter(radius)));
				
				JFrame drawFrame = new JFrame();
				drawFrame.setBounds(0,0,300,300);
				drawFrame.setLocation(dimensions.width/2 - getSize().width/2 + 700, dimensions.height/2 - getSize().height/2 + 200);
				drawFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				drawFrame.setVisible(true);
				VNaughtPanel myPanel = new VNaughtPanel();
				myPanel.setSize(new Dimension(250, 250));
				drawFrame.getContentPane().add(myPanel , BorderLayout.CENTER);
				myPanel.setCollisionAngle(angularVelocity);
				myPanel.saveImage();//Panel knows how to Save a 250x250 pixel Screenshot
			} catch (NumberFormatException n) {
				System.out.println("Fill Out All Fields");
			}
		}        
	}
    //Custom road conditions, hard coded sample values to be improved upon by testing.
	private class Conditions implements ItemListener {
	   
		public void itemStateChanged(ItemEvent e) {
      
			if (e.getStateChange() == ItemEvent.SELECTED) {
         
				int itemSelected = conditionsCB.getSelectedIndex();
            
				switch (itemSelected) {
            
	    		  	case 0:
	    		  		coefficientFric = 1;
	    		  		break;
	    		  	case 1:
	    		  		coefficientFric = 2;
	    		  		break;
	    		  	case 2:
	    		  		coefficientFric = 3;
	    		  		break;
	    		  	case 3:
	    		  		coefficientFric = 4;
	    		  		break;
	    		  }
	    	  }
	      }
	}
}