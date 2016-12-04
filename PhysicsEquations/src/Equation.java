import java.util.HashSet;
import java.util.Set;

public class Equation {
	Expression left;
	Expression right;
	Set<String> variables;
	
	private Equation() {
		variables = new HashSet<>();
	}
	
	public Equation(Expression l, Expression r) {
		this();
		left = l; right = r;
		variables.addAll(l.variables);
		variables.addAll(r.variables);
	}
	
	// Parse string into equation. Return null if problem.
	public static Equation parse(String s) {
		s = s.replaceAll("\\s", "");
		int equals = s.indexOf('=');
		Equation result = new Equation();
		result.left = Expression.parse(s.substring(0, equals));
		result.right = Expression.parse(s.substring(equals+1));
		
		if (result.left == null || result.right == null)
			return null; // parse error
		
		result.variables.addAll(result.left.variables);
		result.variables.addAll(result.right.variables);
		
		return result;
	}
	
	public Equation flip() {
		Equation result = new Equation();
		result.left = this.right;
		result.right = this.left;
		result.variables = this.variables;
		return result;
	}
}
