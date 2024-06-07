package main;
import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUI extends JFrame 
{
		public JTextField textField;
		public ArrayList<Integer> negations = new ArrayList<Integer>(); // Keeping track of the minus signs.	
		private MyEventHandler handler;
		  
		public GUI() {
			super("Calculator");
			
			handler = new MyEventHandler();
		    
		    Font font1 = new Font("Arial", Font.PLAIN, 32);    
		    textField = new JTextField(15);
		    
		    textField.setDocument(new PlainDocument());
		    ((AbstractDocument) textField.getDocument()).setDocumentFilter(new TextLengthFilter());
		      
		    textField.setFont(font1);
		   
		    textField.setEditable(false);
			    
			Locale locale = new Locale("English", "IN");	
		    textField.setLocale(locale);
		    
		    // Create operator buttons.  
		    JButton divideButton = new JButton("/");
		    divideButton.addActionListener(handler);
		        
		    JButton multiplyButton = new JButton("*");
		    multiplyButton.addActionListener(handler);
		    
		    JButton addButton = new JButton("+");
		    addButton.addActionListener(handler);
		    
		    JButton subtractButton = new JButton("-");
		    subtractButton.addActionListener(handler);
		    
		    JButton equalsButton = new JButton("=");
		    equalsButton.addActionListener(handler);
		    
		    JButton decimalButton = new JButton(".");
		    decimalButton.addActionListener(handler);
		    	    
		    JButton clearButton = new JButton("C");
		    clearButton.addActionListener(handler);
		    
		    JButton percentButton = new JButton("%");
		    percentButton.addActionListener(handler);
		    
		    JButton groupButton = new JButton("()");
		    groupButton.addActionListener(handler);
		    
		    JButton deleteButton = new JButton("X");
		    deleteButton.addActionListener(handler);
		        
		    JButton negativeButton = new JButton("+/-");	
		    negativeButton.addActionListener(handler);
		   
		    JButton squareRootButton = new JButton("√");
		    squareRootButton.addActionListener(handler);
		    
		    JButton squaredButton = new JButton("x²");
		    squaredButton.addActionListener(handler);
		    
		    JButton PiButton = new JButton("π");
		    PiButton.addActionListener(handler);
		    
			// Create a panel with GridBagLayout
		    JPanel panel = new JPanel();
		    GridBagLayout layout = new GridBagLayout();    
		    panel.setLayout(layout);
		    
	        // Set constraints for each component
	        GridBagConstraints constraints = new GridBagConstraints();
	        constraints.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
	        constraints.weightx = 1; // Expand horizontally
	        constraints.weighty = 1; // Expand vertically
	        constraints.ipadx = 20;
	        constraints.ipady = 20;
	       
	        // text field        
	        addObjects(textField, panel, layout, constraints, 0, 0, 4, 1);
	        
	        // top-row buttons
	        addObjects(clearButton, panel, layout, constraints, 0, 2, 1, 1);
	        addObjects(percentButton, panel, layout, constraints, 2, 2, 1, 1); 
	        addObjects(groupButton, panel, layout, constraints, 1, 2, 1, 1);  	       
	        addObjects(deleteButton, panel, layout, constraints, 3, 1, 1, 1);     
	        addObjects(squareRootButton, panel, layout, constraints, 2,1,1,1);
	        addObjects(squaredButton, panel, layout, constraints, 1,1,1,1);
	        addObjects(PiButton, panel, layout, constraints, 0,1,1,1);
	        
	        // side-bar buttons
	        addObjects(divideButton, panel, layout, constraints, 3, 2, 1, 1);
	        addObjects(multiplyButton, panel, layout, constraints, 3, 3, 1, 1);
	        addObjects(addButton, panel, layout, constraints, 3, 4, 1, 1);
	        addObjects(subtractButton, panel, layout, constraints, 3, 5, 1, 1); 
	        addObjects(equalsButton, panel, layout, constraints, 3, 6, 1, 1);
	        
	        // bottom-row buttons
	        addObjects(decimalButton, panel, layout, constraints, 2, 6, 1, 1);   
	        addObjects(negativeButton, panel, layout, constraints, 0, 6, 1, 1);
	        
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
		
		class TextLengthFilter extends DocumentFilter {
		   
		    public void insertString(FilterBypass fb, int offset, String text, javax.swing.text.AttributeSet attr) throws BadLocationException {
		         
		        Pattern pattern = Pattern.compile("[0-9]{16}|\\.[0-9]{11}");
		        Matcher matcher = pattern.matcher(text);
		        boolean matchFound = matcher.find();
		        
		        if(!matchFound) {
		        	 super.insertString(fb, offset, text, attr);
		        }
		    }
 
		    public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
		      
		        Pattern pattern = Pattern.compile("[0-9]{16}|\\.[0-9]{11}");
		        Matcher matcher = pattern.matcher(text);
		        boolean matchFound = matcher.find();
		        
		        if(!matchFound) {
		        	 super.replace(fb, offset, length, text, attrs);
		        }
		    }
		}

		private class MyEventHandler implements ActionListener 
		{	
			public void actionPerformed(ActionEvent event) 
			{
				if(event.getActionCommand() == "=") {
					String calc = textField.getText().replace(",", "");
					
					StringBuilder sb = new StringBuilder(calc);
				
					if(sb.charAt(0) == '-') 
					{
						sb.setCharAt(0, 'n');	
					}
					
					for(int n = 1; n < sb.length(); n++) 
					{
						if(sb.charAt(n) == '-' && sb.charAt(n-1) != ')' && Character.getNumericValue(sb.charAt(n-1)) < 0) 
						{
							sb.setCharAt(n, 'n');	
						}
					}
					
					double result = Calculator.prepareCalculation(sb.toString().strip());
					
					Locale locale = new Locale("English", "IN");
					Locale currentLocale = locale;//Locale.getDefault();
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
				else if(event.getActionCommand() == "√") 
				{
					String text = textField.getText();
					
					if(text.length() > 0) 
					{
						char ch = (text.charAt(text.length()-1));
						
						if(ch == ')' || Character.getNumericValue(ch) > -1)
						{
							textField.setText(text + "*");
							
						}
					}
					
					textField.setText(textField.getText() + event.getActionCommand() + "(");	
				}
				else if(event.getActionCommand() == "x²") 
				{
					textField.setText(textField.getText() + "^(2)");	
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
	       // component1.setFont(new Font("Arial", Font.PLAIN, 12));
	        container1.add(component1);
	    }
}


