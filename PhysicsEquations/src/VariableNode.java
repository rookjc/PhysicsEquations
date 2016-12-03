import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class VariableNode implements Node {
	private Color color;
	private String label;
	private Ellipse2D.Double node;
	private double x, y, r;
	
	public VariableNode(Color c, String l, double x, double y, double r) {
		color = c; label = l; this.x = x; this.y = y; this.r = r;
		resize();
	}

	@Override
	public void draw(Graphics g, FontMetrics fm) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(color);
		g2.fill(node);
		g2.setColor(Color.BLACK);
		
		//center String/text
        int cx = (int) (node.getCenterX() - fm.stringWidth(label) / 2);
        int cy = (int) (node.getCenterY() - fm.getHeight() / 2 + fm.getAscent());
		
		g2.drawString(label, cx, cy);
	}
	
	// Scales the circle (position only) to compensate for resized window
	@Override
	public void resize() {
		node = new Ellipse2D.Double(x * Main.dimensions.getWidth(), y * Main.dimensions.getHeight(),
				r * 2, r * 2);
	}
	
}
