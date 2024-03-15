package main;
import java.util.*;

import javax.swing.JFrame;

public class Calculator {

	private static ArrayList<Operator> operators;
	
	public static void main(String[] args) {
		GUI gui1 = new GUI();
		gui1. setVisible(true);
		gui1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//run();
	}
	
	public static void run() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ange en beräkning. Exempelvis (n+n).");
		String calc=sc.next();
		sc.close();
		
		prepareCalculation(calc);
		calculate();
	}
	
	public static void prepareCalculation(String c) {
			
		operators = new ArrayList<Operator>();
		
		for(int i=0; i<c.length(); i++) {
			
			
			if(isOperator(c.charAt(i)))
			{
				Operator op = new Operator();
				
				
				op.firstValue=stringToDecimalLeft(c, i-1);
				
				op.operator=Character.toString(c.charAt(i));
				
				
				op.secondValue=stringToDecimalRight(c, i+1);
				operators.add(op);
				
			
			}
		}	
	}
	
	private static double stringToDecimalLeft(String c, int i) {
		
		double number=0;
		int decimal=1;
			
		while(i >= 0) 
		{
			if(isOperator(c.charAt(i))){
				break;
			}
			
			if(c.charAt(i) == ',') {
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
			if(isOperator(c.charAt(i))){
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
	
	public static double calculate() {
		
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
