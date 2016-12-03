
public class Division extends BinaryExpression {
	@Override
	public double evaluate() {
		return left.evaluate() / right.evaluate();
	}
	
	public Division(Expression l, Expression r) {
		super(l, r);
	}
}
