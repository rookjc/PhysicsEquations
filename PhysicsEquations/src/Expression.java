import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Expression {
	
	// To be overridden
	public double evaluate(HashMap<String, Double> values) {
		return 0;
	}
	
	public Set<String> variables;
	
	protected Expression() {
		variables = new HashSet<>();
	}
	
	public static Expression parse(String s) {
		try {
			Stack<Expression> stack = new Stack<>();
			Expression left, right;
			for (char c : s.toCharArray()) {
				if (c >= '0' && c <= '9') {
					stack.push(new Constant(c - 48));
				} else if ("+-*/".indexOf(c) != -1) {
					right = stack.pop();
					left = stack.pop();
					switch (c) {
					case '+':
						stack.push(new Addition(left, right));
						break;
					case '-':
						stack.push(new Subtraction(left, right));
						break;
					case '*':
						stack.push(new Multiplication(left, right));
						break;
					case '/':
						stack.push(new Division(left, right));
						break;
					}
				} else if (Main.names.containsKey(c + "")) {
					stack.push(new Variable(c + ""));
				} else {
					return null; // parse error
				}
			}
			Expression last = stack.pop();
			return stack.isEmpty() ? last : null;
		} catch (EmptyStackException e) {
			return null; // parse error, handled in the laziest way possible
		}
	}
}
