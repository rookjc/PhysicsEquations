public class Main {
	public static NodeDisplay window;
	
	public static void main(String[] args) {
		window = new NodeDisplay("Testing");
	}
	
	// Easy print method for debugging
	public static void print(Object... obs) {
		for (Object ob : obs)
			System.out.println(ob);
	}
}
