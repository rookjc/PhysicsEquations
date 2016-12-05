
public class BinaryExpression extends Expression {
	protected Expression left, right;
	
	@Override
	public boolean canEvaluate() {
		return left.canEvaluate() && right.canEvaluate();
	}
	
	protected BinaryExpression(Expression l, Expression r) {
		super();
		left = l; right = r;
		variables.addAll(l.variables);
		variables.addAll(r.variables);
	}
}
