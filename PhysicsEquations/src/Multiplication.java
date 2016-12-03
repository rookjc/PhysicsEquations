
public class Multiplication extends BinaryExpression {
	@Override
	public double evaluate() {
		return left.evaluate() * right.evaluate();
	}
	
	public Multiplication(Expression l, Expression r) {
		super(l, r);
	}
}
