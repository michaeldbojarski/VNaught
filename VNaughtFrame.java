// Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

class VNaughtFrame extends JFrame {
   int coefficientFric;
   //Got Constant From:
   //http://www.asknumbers.com/MilesToKilometersConversion.aspx
   static final double KPH_CONVERTER = 1.60934;
   //Got Constant From:
   // https://en.wikipedia.org/wiki/Gravity_of_Earth
   static final double GRAVITY = 9.80665;
   //Got Constant From:
   //http://www.rapidtables.com/convert/length/feet-to-meter.htm
   static final double FT_TO_M = 0.3048;
   //Got Constant From:
   //http://www.convertunits.com/from/meters/to/miles
   static final double M_TO_MILE = 0.000621371192237;

   Calculate CBHandler;
   Conditions conditionH;
   
   JComboBox conversionCB, conditionsCB;
   
   JTextField arcHeightTF, distanceTF, velocityTF, angleTF;
   
   public VNaughtFrame() {
	   init();
   }
   public void init() {
      //Objects
      JButton calculateJB, exitJB;
      
      //Event/Item handlers
      CBHandler = new Calculate();
      conditionH = new Conditions();
      
      JLabel velocityTitle, velocityJL, distanceJL, arcHeightJL, conditionsJL, angleJL;
      JLabel buffer1 = new JLabel(""), buffer2 = new JLabel(""), buffer3 = new JLabel(""), buffer4 = new JLabel("");
      JLabel buffer5 = new JLabel(""), buffer6 = new JLabel(""), buffer7 = new JLabel(""), buffer8 = new JLabel("");
      JLabel buffer9 = new JLabel(""), buffer10 = new JLabel(""), buffer11 = new JLabel(""), buffer12 = new JLabel("");
      JLabel buffer13 = new JLabel("");
      
      Container panel = getContentPane();
      
      //array for conditions/conversions
      String[] conditions = {"Sunny & Dry","Light Rain","Moderate Rain","Heavy Rain","Ice","Snow"};
	  String[] conversions = {"MPH","KMH"};
      
      panel.setLayout(new GridLayout(8,3,4,4));
      
      //JButton
      calculateJB = new JButton("Calculate");
      
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
      panel.add(buffer5);
      panel.add(buffer6);
      panel.add(angleTF);
      panel.add(buffer8);
      panel.add(buffer9);
      panel.add(velocityJL);
      panel.add(buffer10);
      panel.add(buffer11);
      panel.add(velocityTF);
      panel.add(buffer12);
      panel.add(buffer13);
      panel.add(calculateJB);
      
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
   
   private class Calculate implements ActionListener {
   
      public void actionPerformed(ActionEvent e) {
         double velocity = 0.0;
         double height = Double.parseDouble(arcHeightTF.getText());
         double distance = Double.parseDouble(distanceTF.getText());
         double radius = 0;
         double angleOfElevation = Double.parseDouble(angleTF.getText());
         double frictCalculation = 0;
         
         radius = ((height / 2) + ((Math.pow(distance,2)) / (8 * height)));
         frictCalculation = ((coefficientFric * Math.cos(angleOfElevation)) + Math.sin(angleOfElevation));
      
         velocity = Math.sqrt((toMeter(radius) * GRAVITY * frictCalculation));
      
         velocityTF.setText(convert(toMiles(velocity)) + " Miles Per Hour");
      }                                                
   
   }
   
   private class Conditions implements ItemListener {
   
      public void itemStateChanged(ItemEvent e) {
      
         if (e.getStateChange() == ItemEvent.SELECTED) {
         
            int itemSelected = conditionsCB.getSelectedIndex();
            
            switch (itemSelected) {
            
               case 0:
                  coefficientFric = 0;
               break;
               case 1:
                  coefficientFric = 1;
               break;
               case 2:
                  coefficientFric = 2;
               break;
               case 3:
                  coefficientFric = 3;
               break;
            
            }
         
         }
      
      }
   
   }
}