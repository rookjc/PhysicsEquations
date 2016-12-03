import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class VariableNode implements Node {
	private Color color;
	private String label;
	private Ellipse2D.Double node;
	
	public VariableNode(Color c, String l, double x, double y, double r) {
		color = c; label = l;
		node = new Ellipse2D.Double(x, y, r * 2, r * 2);
	}

	@Override
	public void draw(Graphics g, Font f) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.fill(node);
		g2.setColor(Color.BLACK);
		g2.drawString(label, (int) node.x, (int) node.y);
	}
	
}
