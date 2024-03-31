package main;
import java.util.*;

import javax.swing.JFrame;

public class Calculator {
	
	public static void main(String[] args) {
		GUI gui1 = new GUI();
		gui1.pack();
		gui1. setVisible(true);
		gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static double prepareCalculation(String c) 
	{
		ArrayList<Operator> operators = new ArrayList<Operator>();
		boolean closedParenthesis = false;
		double parenthesisResult = 0;
		
		for(int i = 0; i < c.length(); i++) {
				
			// Check parenthesis.
			if(c.charAt(i) == '(') 
			{		
				int j = indexOfClosedParenthesis(i, c);
					
				// Recursive call and return calculated value.
				parenthesisResult=prepareCalculation(c.substring(i+1, j));
					
				// Jump forward to position after parentheses.
				i=j;
				closedParenthesis = true;
				
				if(i == c.length()-1) {
					operators.get(operators.size() - 1).secondValue = parenthesisResult;
				}
			}
			
			if(isOperator(c.charAt(i)))
			{	
				Operator op1 = new Operator();
				
				if(closedParenthesis) {
					
					op1.firstValue = parenthesisResult;
					closedParenthesis = false;
					
					if(operators.size() > 0) {		
						operators.get(operators.size() - 1).secondValue = op1.firstValue;		
					}
				}
				else {	
					op1.firstValue=stringToDecimalLeft(c, i-1);	
				}
				
				if(c.charAt(i+1) != '(') {		
					op1.secondValue = stringToDecimalRight(c, i+1);
					
					if(c.charAt(i) == '-') {
						op1.secondValue *= -1;
					}
				}
				
				op1.operator=Character.toString(c.charAt(i));
				
				operators.add(op1);	
			}
		}	
		
		return calculate(operators);
	}
	
	// Where possibly nested parenthesis closes.
	private static int indexOfClosedParenthesis(int startIndex, String str) {
		int counter = 0;
		int index = startIndex;
		
		while(index < str.length()) {
			
			if(str.charAt(index) == '(') {
				counter++;
			}
			
			if(str.charAt(index) == ')') {
				counter--;	
			}
				
			if(counter == 0) {
				break;
			}
			
			index++;
		} 
		
		return index;
	}

	private static double stringToDecimalLeft(String c, int i) {
		
		try {
			double number=0;
			int decimal=1;
				
			while(i >= 0) 
			{
				if(isOperator(c.charAt(i))){
					if(c.charAt(i) == '-') {
						number *= -1;
					}
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
		catch (Exception ex) {
			System.out.print(ex);
			return 0;
		}
	}
	
	private static double stringToDecimalRight(String c, int i) {
			
		try {
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
		catch (Exception ex) {
			System.out.print(ex.getMessage());
			return 0;
		}
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
