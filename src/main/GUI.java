package main;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GUI extends JFrame 
{
		public JTextField textField;	
		private MyEventHandler handler;
		String input="";
		
		public GUI() {
			super("Calculator");
			
			setSize(400, 600);
		    setLayout(null);
		   handler = new MyEventHandler();
		    
		    Font font1 = new Font("Arial", Font.PLAIN, 40);
		    
		    textField = new JTextField();
		    textField.setBounds(0, 0, 400, 50);
		    textField.setFont(font1);
		    
		    int XOffset = 50;
		    int YOffset = 100;
		    
		    // Create operator buttons.
		    JButton button1 = new JButton("/");
		    button1.setBounds(200, YOffset, 50, 50);
		    button1.addActionListener(handler);
		        
		    JButton button2 = new JButton("*");
		    button2.setBounds(200, YOffset+50, 50, 50);
		    button2.addActionListener(handler);
		    
		    JButton button3 = new JButton("+");
		    button3.setBounds(200, YOffset+100, 50, 50);
		    button3.addActionListener(handler);
		    
		    JButton button4 = new JButton("-");
		    button4.setBounds(200, YOffset+150, 50, 50);
		    button4.addActionListener(handler);
		    
		    JButton button5 = new JButton("=");
		    button5.setBounds(200, YOffset+200, 50, 50);
		    button5.addActionListener(handler);
		    
		    JButton button6 = new JButton(",");
		    button6.setBounds(150, YOffset+200, 50, 50);
		    button6.addActionListener(handler);
		    
		    
		    JButton button7 = new JButton("C");
		    button7.setBounds(50, YOffset, 50, 50);
		    button7.addActionListener(handler);
		    
		    JButton button8 = new JButton("(");
		    button8.setBounds(100, YOffset, 50, 50);
		    button8.addActionListener(handler);
		    
		    JButton button9 = new JButton(")");
		    button9.setBounds(150, YOffset, 50, 50);
		    button9.addActionListener(handler);
		    
		    JButton button10 = new JButton("X");
		    button10.setBounds(200, YOffset-50, 50, 50);
		    button10.addActionListener(handler);
		   
		    add(textField);
		    
		    add(button1);
		    add(button2);
		    add(button3);
		    add(button4);
		    add(button5);
		    add(button6);
		    add(button7);
		    add(button8);
		    add(button9);
		    add(button10);
		    
		    // Number buttons.	    
		    JButton zeroButton = new JButton("0");
    		zeroButton.setBounds(100, YOffset+200, 50 , 50);
    		zeroButton.addActionListener(handler);
    		add(zeroButton);
    		
		    for(int y = 0; y < 3; y++) 
		    {
		    	for(int x = 1; x < 4; x++) 
		    	{
		    		JButton newButton = new JButton(x+(3*y)+"");
		    		newButton.setBounds(x*50+XOffset-50, YOffset-y*50+YOffset+50, 50 , 50);	
		    		newButton.addActionListener(handler);
		    		add(newButton);
		    	}
		    }
		}	
		
		private class MyEventHandler implements ActionListener 
		{	
			public void actionPerformed(ActionEvent event) 
			{
				if(event.getActionCommand() == "=") {
					String calc = textField.getText();
					double result = Calculator.prepareCalculation(calc);
					//double result = Calculator.calculate();
					textField.setText(result+"");
				}
				else if(event.getActionCommand() == "C")
				{
					textField.setText("");
					input = "";
				}
				else if(event.getActionCommand() == "X")
				{
					input = input.substring(0, input.length() - 1);
					textField.setText(input);
				}
				else
				{
					input += event.getActionCommand();
					//JOptionPane.showMessageDialog(null, input);
					textField.setText(input);
				}
			}
		}
}


