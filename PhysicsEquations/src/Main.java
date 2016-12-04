import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static HashMap<String, VariableNode> names = new HashMap<>();
	
	public static int scale = 40;
	
	public static NodeDisplay window;
	public static Rectangle dimensions;
	public static Font font;
	
	public static void main(String[] args) {
		font = new Font("Serif", Font.BOLD, scale);
		window = new NodeDisplay("Testing");
	}
	
	// Easy print method for debugging
	public static void print(Object... obs) {
		for (Object ob : obs)
			System.out.println(ob);
	}
	
	public static Color randomColor() {
		return new Color((int)(Math.random() * 128) + 128, (int)(Math.random() * 128) + 128, (int)(Math.random() * 128) + 128);
	}

	public static String getNextAvailableName() {
		for (char c = 'a'; c < 'z'; c++) {
			if (!names.containsKey(c + ""))
				return c + "";
		}
		return "?";
	}
	
	// This should lead to the graph traversal I think
	public static boolean canFind(String variable) {
		return false;
	}
	
	// Make the edges coming out of an equation representative of what 
	public static void updateEdges(EquationNode n, Equation e) {
		Set<String> needed = new HashSet<>(e.variables);
		Set<Edge> toRemove = new HashSet<>();
		
		for (Edge edge : NodeDisplay.Nodes.edges) {
			if (edge.end2 == n) {
				String symbol = ((VariableNode)edge.end1).getSymbol();
				if (needed.contains(symbol))
					needed.remove(symbol);
				else
					toRemove.add(edge);
			}
		}
		
		for (Edge edge : toRemove)
			NodeDisplay.Nodes.edges.remove(edge);
		
		for (String symbol : needed) {
			VariableNode vn = names.get(symbol);
			NodeDisplay.Nodes.edges.add(new Edge(vn.getColor(), vn, n));
		}
		
	}
	
}
