import java.awt.FontMetrics;
import java.awt.Graphics;

public interface Node {
	public void draw(Graphics g, FontMetrics fm);
	public void resize();
}
