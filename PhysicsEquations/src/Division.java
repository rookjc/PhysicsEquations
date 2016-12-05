import java.util.HashMap;

public class Division extends BinaryExpression {
	@Override
	public double evaluate(HashMap<String, Double> values) {
		return left.evaluate(values) / right.evaluate(values);
	}
	
	public Division(Expression l, Expression r) {
		super(l, r);
	}
}
