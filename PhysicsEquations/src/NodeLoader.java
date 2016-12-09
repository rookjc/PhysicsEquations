import java.awt.Point;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/* Data stored as text:
variable node: "var", name, label, local x, local y, value (string, could be '?')
equation node: "eq", label, local x, local y
*/

public class NodeLoader {
	public static void load(String name, Point origin) {
		Scanner in = null;
		try {
			in = new Scanner(new File(name));
			while (in.hasNext()) {
				switch (in.next()) {
				case "var": // variable node
					VariableNode newNode = new VariableNode(Main.randomColor(), in.next(), in.next(),
							in.nextDouble() + origin.getX(), in.nextDouble() + origin.getY(), Main.scale);
					newNode.setValue(in.next());
					Main.names.put(newNode.getSymbol(), newNode);
					NodeDisplay.Nodes.nodes.add(newNode);
					break;
				case "eq": // equation node
					String lbl = in.next();
					EquationNode newNode2 = new EquationNode("0 = 0", in.nextDouble(), in.nextDouble(), Main.scale * 1.5);
					newNode2.resize();
					newNode2.updateEquation(lbl, false);
					NodeDisplay.Nodes.nodes.add(newNode2);
					break;
				default:
					break;
				}
			}
			Main.window.repaint();
		} catch (Exception e) {
			System.err.println("Error loading: " + e);
		} finally {
			in.close();
		}
	}
	
	public static void save(String name, Point origin) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(name));
			// Write variable nodes
			for (Node n : NodeDisplay.Nodes.nodes) {
				if (n instanceof VariableNode) {
					out.println(n); // using overridden toString
				}
			}
			// Write equation nodes (after all variable nodes are done)
			for (Node n : NodeDisplay.Nodes.nodes) {
				if (n instanceof EquationNode) {
					out.println(n); // using overridden toString
				}
			}
		} catch (Exception e) {
			System.err.println("Error saving: " + e);
		} finally {
			out.close();
		}
	}
}
