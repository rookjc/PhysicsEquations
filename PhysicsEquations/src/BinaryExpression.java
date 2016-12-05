
public class BinaryExpression extends Expression {
	protected Expression left, right;
	
	protected BinaryExpression(Expression l, Expression r) {
		super();
		left = l; right = r;
		variables.addAll(l.variables);
		variables.addAll(r.variables);
	}
}
