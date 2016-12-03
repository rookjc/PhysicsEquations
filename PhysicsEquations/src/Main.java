import java.awt.Font;
import java.awt.Rectangle;

public class Main {
	public static NodeDisplay window;
	public static Rectangle dimensions;
	public static Font font;
	
	public static void main(String[] args) {
		font = new Font("Serif", Font.PLAIN, 20000);
		window = new NodeDisplay("Testing");
	}
	
	// Easy print method for debugging
	public static void print(Object... obs) {
		for (Object ob : obs)
			System.out.println(ob);
	}
	
}
