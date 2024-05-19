package main;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GUI extends JFrame 
{
		public JTextField textField;
		public ArrayList<Integer> negations = new ArrayList<Integer>(); // Keeping track of the minus signs.	
		private MyEventHandler handler;
		  
		public GUI() {
			super("Calculator");
			
			// TODO: decimal point.
			//DecimalFormat df2 = new DecimalFormat("#,##");           
			//df2.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.GERMANY));
			Locale.setDefault(Locale.GERMANY);
			
			//setSize(400, 600);
		  //  setLayout(null);
		   handler = new MyEventHandler();
		    
		    Font font1 = new Font("Arial", Font.PLAIN, 40);    
		    textField = new JTextField();
		    textField.setFont(font1);
		   // textField.setEnabled(false);
		  textField.setEditable(false);
			    
		    // Create operator buttons.  
		    JButton button1 = new JButton("/");
		
		    button1.setBounds(0, 0, 50, 50);
		    button1.addActionListener(handler);
		        
		    JButton button2 = new JButton("*");
		 
		    button2.addActionListener(handler);
		    
		    JButton button3 = new JButton("+");
	
		    button3.addActionListener(handler);
		    
		    JButton button4 = new JButton("-");
		
		    button4.addActionListener(handler);
		    
		    JButton button5 = new JButton("=");
		 
		    button5.addActionListener(handler);
		    
		    JButton button6 = new JButton(",");
		
		    button6.addActionListener(handler);
		    
		    
		    JButton button7 = new JButton("C");
		
		    button7.addActionListener(handler);
		    
		    JButton button8 = new JButton("()");
		
		    button8.addActionListener(handler);
		    
		   // JButton button9 = new JButton(")");
		
		  //  button9.addActionListener(handler);
		    
		    JButton button10 = new JButton("X");
		
		    button10.addActionListener(handler);
		    
		    
		    JButton button11 = new JButton("+/-");	
		    button11.addActionListener(handler);
		   
		    JButton button12 = new JButton("√");
		    button12.addActionListener(handler);
		    
			// Create a panel with GridBagLayout
		    JPanel panel = new JPanel();
		    GridBagLayout layout = new GridBagLayout();    
		    panel.setLayout(layout);
		    
	        // Set constraints for each component
	        GridBagConstraints constraints = new GridBagConstraints();
	        constraints.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
	        constraints.weightx = 1; // Expand horizontally
	        constraints.weighty = 1; // Expand vertically
	     
	        // text field        
	        addObjects(textField, panel, layout, constraints, 0, 0, 4, 1);
	        
	        // top-row buttons
	        addObjects(button7, panel, layout, constraints, 0, 2, 1, 1);
	        addObjects(button8, panel, layout, constraints, 1, 2, 1, 1);  
	       // addObjects(button9, panel, layout, constraints, 2, 2, 1, 1);
	        addObjects(button10, panel, layout, constraints, 3, 1, 1, 1);
	        
	        addObjects(button12, panel, layout, constraints, 2,1,1,1);
	        
	        // side-bar buttons
	        addObjects(button1, panel, layout, constraints, 3, 2, 1, 1);
	        addObjects(button2, panel, layout, constraints, 3, 3, 1, 1);
	        addObjects(button3, panel, layout, constraints, 3, 4, 1, 1);
	        addObjects(button4, panel, layout, constraints, 3, 5, 1, 1); 
	        addObjects(button5, panel, layout, constraints, 3, 6, 1, 1);
	        
	        // bottom-row buttons
	        addObjects(button6, panel, layout, constraints, 2, 6, 1, 1);   
	        addObjects(button11, panel, layout, constraints, 0, 6, 1, 1);
	        
	        // number buttons
	        JButton btn7 = new JButton("7");
    		btn7.addActionListener(handler);	
    		addObjects(btn7, panel, layout, constraints, 0, 3, 1, 1);
    		
	        JButton btn8 = new JButton("8");
    		btn8.addActionListener(handler);
    		addObjects(btn8, panel, layout, constraints, 1, 3, 1, 1);
    		
	        JButton btn9 = new JButton("9");
    		btn9.addActionListener(handler);	
    		addObjects(btn9, panel, layout, constraints, 2, 3, 1, 1);
    		
	        JButton btn4 = new JButton("4");
    		btn4.addActionListener(handler);
    		addObjects(btn4, panel, layout, constraints, 0, 4, 1, 1);
    		
	        JButton btn5 = new JButton("5");
    		btn5.addActionListener(handler);
    		addObjects(btn5, panel, layout, constraints, 1, 4, 1, 1);
    		
	        JButton btn6 = new JButton("6");
    		btn6.addActionListener(handler);
    		addObjects(btn6, panel, layout, constraints, 2, 4, 1, 1);
    		
	        JButton btn1 = new JButton("1");
    		btn1.addActionListener(handler);	
    		addObjects(btn1, panel, layout, constraints, 0, 5, 1, 1);
    		
	        JButton btn2 = new JButton("2");
    		btn2.addActionListener(handler);
    		addObjects(btn2, panel, layout, constraints, 1, 5, 1, 1);
    		
	        JButton btn3 = new JButton("3");
    		btn3.addActionListener(handler);	
    		addObjects(btn3, panel, layout, constraints, 2, 5, 1, 1);
    			
    		JButton zeroButton = new JButton("0");
     		zeroButton.addActionListener(handler);
     		addObjects(zeroButton, panel, layout, constraints, 1, 6, 1, 1);
     		
		    add(panel);
		}	
		
		private class MyEventHandler implements ActionListener 
		{	
			public void actionPerformed(ActionEvent event) 
			{
				//textField.setEnabled(true);
				if(event.getActionCommand() == "=") {
					String calc = textField.getText();
					StringBuilder sb = new StringBuilder(calc);
					
					// convert
					for(int index : negations)
					{		
						sb.setCharAt(index, 'n');			
					}
					
					//calc = sb.toString();
					double result = Calculator.prepareCalculation(sb.toString());
					
					
					Locale currentLocale = Locale.getDefault();
					NumberFormat nf_out = NumberFormat.getNumberInstance(currentLocale);
					nf_out.setMaximumFractionDigits(10);
					String output = nf_out.format(result);
					
					
					textField.setText(output);
					negations.clear();
				}
				else if(event.getActionCommand() == "C")
				{
					textField.setText("");
				}
				else if(event.getActionCommand() == "X")
				{
					String text = textField.getText();
					if(text.length() > 0) {
						textField.setText(text.substring(0, text.length()-1));
					}
				} 
				else if(event.getActionCommand() == "()")
				{
					String text = textField.getText();
							
					if(text.length() > 0) 
					{
						char ch = (text.charAt(text.length()-1));
						
						if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '√' || ch == '(')
						{
							textField.setText(text + "(");
							
						} // Previous a numeric value or closed parenthesis.
						else if(ParenthesisDepth() == 0 && (Character.getNumericValue(ch) > -1 || ch==')')) 
						{
							textField.setText(text + "*(");
						}
						else
						{
							textField.setText(text + ")");
						}
					}
					else 
					{
						textField.setText("(");	
					}
					
				}
				else if(event.getActionCommand() == "+/-") 
				{
					// Convert minus signs internally to separate them from operators.
					textField.setText(textField.getText() + "-");
					int index = textField.getText().length() - 1;
					negations.add(index);
				}
				else
				{	
					textField.setText(textField.getText() + event.getActionCommand());		
				}
			}
			
			public int ParenthesisDepth() 
			{
				String text = textField.getText();
				int depth = 0;
				
				for(int n = 0; n < text.length(); n++) {
					if(text.charAt(n) == '(') {
						depth++;
					}
					
					if(text.charAt(n) == ')') {
						depth--;
					}
				}
				return depth;
			}
		}
		
		public void addObjects(Component component1, Container container1, GridBagLayout layout, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight) {

	        gbc.gridx = gridx;
	        gbc.gridy = gridy;

	        gbc.gridwidth = gridwidth;
	        gbc.gridheight = gridheight;

	        layout.setConstraints(component1, gbc);
	        container1.add(component1);
	    }
}


