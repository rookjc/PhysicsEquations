import java.util.HashMap;

public class Constant extends Expression {
	double value;

	@Override
	public double evaluate(HashMap<String, Double> values) {
		return value;
	}
	
	public Constant(double value) {
		super();
		this.value = value;
	}
}
