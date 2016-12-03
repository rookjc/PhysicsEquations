
public class Variable extends Expression {
	String symbol;
	
	@Override
	public boolean canEvaluate() {
		return isKnown() || Main.canFind(symbol);
	}
	
	@Override
	public double evaluate() {
		if (isKnown()) {
			return Main.names.get(symbol).getValue();
		} else {
			return 0;
			// Should start trying to find it here...
		}
	}
	
	public Variable(String s) {
		symbol = s;
		variables.add(symbol);
	}
	
	private boolean isKnown() {
		return Main.names.get(symbol).isKnown();
	}
}
