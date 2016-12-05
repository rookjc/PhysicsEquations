import java.util.HashMap;

public class Addition extends BinaryExpression {
	@Override
	public double evaluate(HashMap<String, Double> values) {
		return left.evaluate(values) + right.evaluate(values);
	}
	
	public Addition(Expression l, Expression r) {
		super(l, r);
	}
}
