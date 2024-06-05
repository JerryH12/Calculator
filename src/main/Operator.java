package main;

public class Operator {
	public String operator = "";
	public double firstValue=0;
	public double secondValue=0;
	public int order = 0;
	
	public Operator(String op, double value1, double value2) {
		operator=op;
		firstValue=value1;
		secondValue=value2;
	}
	
	public Operator() {
		
	}
	
	// Set the operator and order of operations.
	public void setOperator(String op) {
		operator = op;
		
		switch(operator) {
		case "^":
			order = 0;
			break;
		case "*":
			order = 1;
			break;
		case "/":
			order = 1;
			break;
		case "+":
			order = 2;
			break;
		case "-":
			order = 2;
			break;
		default:
			order=0;
		}
	}
	
	public double calculate() {
		double result;
		switch(operator) {
			case "^":
				result = power();
				break;
			case "*":
				result= multiply();
				break;
			case "/":
				result=divide();
				break;
			case "+":
				result=add();
				break;
			case "-":
				result= subtract();
				//result=add();
				break;
			default:
				result=0;
		}
		return result;
	}
	
	public double power() {
		return Math.pow(firstValue, secondValue);
	}
	
	public double multiply() {
		return firstValue*secondValue;
	}
	
	public double divide() {
		return firstValue/secondValue;
	}
	
	public double add() {
		return firstValue+secondValue;
	}
	
	public double subtract() {
		return firstValue-secondValue;
	}
	
	public void setFirstValue(double value1) {
		firstValue=value1;
	}
	
	public void setSecondValue(double value2) {
		secondValue=value2;
	}
	
	public double getFirstValue() {
		return firstValue;
	}
	
	public double getSecondValue() {
		return secondValue;
	}
}


