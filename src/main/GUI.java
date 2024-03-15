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
		    
		    // Create operator buttons.
		    JButton button1 = new JButton("/");
		    button1.setBounds(300, 100, 50, 50);
		    button1.addActionListener(handler);
		        
		    JButton button2 = new JButton("*");
		    button2.setBounds(300, 150, 50, 50);
		    button2.addActionListener(handler);
		    
		    JButton button3 = new JButton("+");
		    button3.setBounds(300, 200, 50, 50);
		    button3.addActionListener(handler);
		    
		    JButton button4 = new JButton("-");
		    button4.setBounds(300, 250, 50, 50);
		    button4.addActionListener(handler);
		    
		    JButton button5 = new JButton("=");
		    button5.setBounds(300, 300, 50, 50);
		    button5.addActionListener(handler);
		    
		    JButton button6 = new JButton(",");
		    button6.setBounds(250, 300, 50, 50);
		    button6.addActionListener(handler);
		   
		    add(textField);
		    add(button1);
		    add(button2);
		    add(button3);
		    add(button4);
		    add(button5);
		    add(button6);
		    
		    // Number buttons.
		    int offset = 50;
		    
		    JButton zeroButton = new JButton("0");
    		zeroButton.setBounds(100, 250, 50 , 50);
    		zeroButton.addActionListener(handler);
    		add(zeroButton);
    		
		    for(int y = 0; y < 3; y++) 
		    {
		    	for(int x = 1; x < 4; x++) 
		    	{
		    		JButton newButton = new JButton(x+(3*y)+"");
		    		newButton.setBounds(x*50, (150)-y*50+offset, 50 , 50);	
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
					Calculator.prepareCalculation(calc);
					double result = Calculator.calculate();
					textField.setText(result+"");
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


