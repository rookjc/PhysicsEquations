import java.util.HashMap;

public class Subtraction extends BinaryExpression {
	@Override
	public double evaluate(HashMap<String, Double> values) {
		return left.evaluate(values) - right.evaluate(values);
	}
	
	public Subtraction(Expression l, Expression r) {
		super(l, r);
	}
}
