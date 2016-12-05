import java.util.HashMap;

public class Multiplication extends BinaryExpression {
	@Override
	public double evaluate(HashMap<String, Double> values) {
		return left.evaluate(values) * right.evaluate(values);
	}
	
	public Multiplication(Expression l, Expression r) {
		super(l, r);
	}
}
