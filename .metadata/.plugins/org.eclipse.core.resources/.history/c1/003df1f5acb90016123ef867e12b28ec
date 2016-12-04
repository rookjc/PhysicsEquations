import java.util.HashSet;
import java.util.Set;

public class Equation {
	Expression left;
	Expression right;
	
	private Equation() {}
	
	// Parse string into equation. Return null if problem.
	public static Equation parse(String s) {
		Set<String> variables = new HashSet<>();
		
		s.replaceAll("\\s", "");
		int equals = s.indexOf('=');
		Equation result = new Equation();
		result.left = Expression.parse(s.substring(0, equals));
		result.right = Expression.parse(s.substring(equals+1));
		
		if (result.left == null || result.right == null)
			return null; // parse error
		
		variables.addAll(result.left.variables);
		variables.addAll(result.right.variables);
		
		return result;
	}
}
