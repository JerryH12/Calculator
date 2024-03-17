package main;
import java.util.*;

import javax.swing.JFrame;

public class Calculator {
	
	public static void main(String[] args) {
		GUI gui1 = new GUI();
		gui1. setVisible(true);
		gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static double prepareCalculation(String c) 
	{
		ArrayList<Operator> operators = new ArrayList<Operator>();
		
		for(int i=0; i<c.length(); i++) {
			
			if(isOperator(c.charAt(i)))
			{
				Operator op1 = new Operator();				
				op1.firstValue=stringToDecimalLeft(c, i-1);	
				op1.operator=Character.toString(c.charAt(i));
				
				// Check parenthesis.
				if(c.charAt(i+1) == '(') 
				{		
					int j = c.lastIndexOf(')');
					
					// Recursive call and return calculated value.
					op1.secondValue=prepareCalculation(c.substring(i+2, j));
					
					i = j+1; // Jump forward to position after parentheses.
						
					Operator op2 = new Operator();
					op2.firstValue = op1.secondValue;
					op2.operator=Character.toString(c.charAt(i));
					op2.secondValue = stringToDecimalRight(c, i+1);
					
					operators.add(op1);
					operators.add(op2);
				}
				else
				{	
					// Without parentheses.		
					op1.secondValue=stringToDecimalRight(c, i+1);
					operators.add(op1);
				}	
			}
		}	
		
		return calculate(operators);
	}
	
	private static double stringToDecimalLeft(String c, int i) {
		
		double number=0;
		int decimal=1;
			
		while(i >= 0) 
		{
			if(isOperator(c.charAt(i))){
				break;
			}
			
			if(c.charAt(i) == ',') 
			{
				number = (number / decimal);
				decimal = 1;
				i--;
				continue;
			}
			
			number+=Character.getNumericValue(c.charAt(i))*decimal;
			decimal*=10;
			
			i--;
		}
		
		return number;
	}
	
	private static double stringToDecimalRight(String c, int i) {
			
		String stringNumber = "";
		
		while(i < c.length()) 
		{
			if(isOperator(c.charAt(i)))
			{
				break;
			}
			
			stringNumber += c.charAt(i);
			i++;
		}
		
		double number = stringToDecimalLeft(stringNumber, stringNumber.length()-1);
		return number;
	}

	private static boolean isOperator(char c) {
		if(c=='*' || c=='/' || c=='+' || c=='-') {
			return true;
		}
		return false;
	}
	
	public static double calculate(ArrayList<Operator> operators) 
	{	
		String[] op={"*","/","+","-"};
	
		int j;
		
		if(operators.size()>1) {
			
			for(int i=0; i < 4; i++) {
				j=0;
				while(j<operators.size()){
					
					if(operators.get(j).operator.equals(op[i])) {
						
						double value=operators.get(j).calculate();
						if(j>0) {
							operators.get(j-1).secondValue=value;
						}
						
						if(j<operators.size()-1) {
							operators.get(j+1).firstValue=value;
						}
						
						if(operators.size()>1) {
							operators.remove(j);
							continue;	
						}			
					}	
					j++;
				}
			}
		}
		
		//System.out.println("="+operators.get(0).calculate());
		return operators.get(0).calculate();
		
	}
	
	public static void add(int a, int b) {
		System.out.println(String.format("%d+%d=%d",a,b,a+b));
		
	}
	
	public static void subtract(int a, int b) {
		System.out.println(String.format("%d-%d=%d",a,b,a-b));
	}
	
	public static void multiply(int a, int b) {
		System.out.println(String.format("%d*%d=%d",a,b,a*b));
	}
	
	public static void divide(int a, int b) {
		System.out.println(String.format("%d/%d=%d",a,b,a/b));
	}

}
