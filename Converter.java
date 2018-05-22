import java.util.List;

public class Converter {
	
	private String infix = "";
	private String postfix = "";
	private ArrayStack<String> stack = new ArrayStack<>();
	
	public Converter(String s) {
		infix = s;
		toPostFix(infix);
	}
	
	public String getPostfix() {
		return postfix;
	}
	
	public String toPostFix(String infix) {
		char[] c = new char[infix.length()];
	    for (int i = 0; i < infix.length(); i++) {
			c[i] = infix.charAt(i);
		}
	
		List<String> list = ParserHelper.parse(c);
		String[] strings = new String[list.size()]; 
		int j = 0;
		for (String item : list) {
			strings[j] = item;
			j++;
		}
		for (int i = 0; i < strings.length; i++) {
			
			if (strings[i].equals("(")) {
				stack.push(strings[i]);
				
			} else if (strings[i].equals("^")) {   //Highest Priority
				while (stack.peek() != null && (stack.peek().equals("^"))) {   //stack.peek != null prevents NullPointerException because peek will check and see String at top of empty stack is null (isEmpty() returns null)
					postfix += stack.pop() + " ";
				}
				stack.push(strings[i]);
				
				
				
			} else if (strings[i].equals("*") || strings[i].equals("/")) {
				while (stack.peek() != null && (stack.peek().equals("^") || stack.peek().equals("*") || stack.peek().equals("/"))) {   //stack.peek != null needs to be in the left side of the && statement. Otherwise, stack.peek() will be checked first and may return nullpointerexception
					postfix += stack.pop() + " ";
				}
				stack.push(strings[i]);
				
				
			} else if (strings[i].equals("+") || strings[i].equals("-")) {
				while (stack.peek() != null && (stack.peek().equals("*") || stack.peek().equals("/") || stack.peek().equals("^") || stack.peek().equals("+") || stack.peek().equals("-"))) {
					postfix += stack.pop() + " ";
				}
				stack.push(strings[i]);
			
			} else if (strings[i].equals(")")) {
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					postfix += stack.pop() + " ";
				}
				stack.pop();  //get rid of '('
			
				
			} else {  //if operand
				postfix += strings[i] + " ";
			}
			
			
		}
		while (!stack.isEmpty()) {
			postfix += stack.pop() + " ";
		}
		return postfix;
	}
}