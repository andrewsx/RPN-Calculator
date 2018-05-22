import java.util.Scanner;
public class PostfixCalculator {
	private static ArrayStack<Double> operand = new ArrayStack<>();
	
	public static void main(String[] args) {
		double operand1, operand2, result, answer = 0;
		String input, postfix = "";
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please enter an infix expression: ");
		input = sc.nextLine();
		Converter converter = new Converter(input);
		postfix = converter.getPostfix();
		
		for (String s : postfix.split(" ")) {  //postfix.split should return a string array of strings
			if (s.equals("+")) {   
				operand1 = operand.pop();
				operand2 = operand.pop();
				result = operand1 + operand2;
				operand.push(result);
			} else if (s.equals("-")) {
				operand1 = operand.pop();
				operand2 = operand.pop();
				result = operand2 - operand1;
				operand.push(result);
			} else if (s.equals("*")) {
				operand1 = operand.pop();
				operand2 = operand.pop();
				result = operand1 * operand2;
				operand.push(result);
			} else if (s.equals("/")) {
				operand1 = operand.pop();
				operand2 = operand.pop();
				result = operand2 / operand1;
				operand.push(result);
			} else if (s.equals("^")) {
				operand1 = operand.pop();
				operand2 = operand.pop();
				result = Math.pow(operand2, operand1);
				operand.push(result);
			} else {  //if token is an operand
				operand.push(Double.parseDouble(s));
			}
		}
		answer = operand.pop();
		System.out.println("Converted to postfix expression:\n" + postfix);
		System.out.println("Evaluating postfix expression......\n" + answer);
		sc.close();
	
	}
}
	
	