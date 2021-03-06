import java.util.HashMap;

public class Variable extends Expression {
	String symbol;
	
	@Override
	public double evaluate(HashMap<String, Double> values) {
		return values.get(symbol); // assuming values has the symbol if this was called
	}
	
	public Variable(String s) {
		symbol = s;
		variables.add(symbol);
	}
}
