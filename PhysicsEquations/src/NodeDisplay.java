import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JLabel;
import javax.swing.JPanel;

public class NodeDisplay extends JFrame implements MouseListener, MouseMotionListener {
	boolean dragging;
	Node dragNode;
	static Edge dragWire;
	static int handleX, handleY;
	TextEntry editWindow = null;
	
	Tool tool = Tool.NODE;
	
	public static class Nodes extends JPanel {
		private static final long serialVersionUID = 1L;
		
		static LinkedList<Node> nodes = new LinkedList<>();
		static LinkedList<Edge> edges = new LinkedList<>();

		@Override
        protected void paintComponent(Graphics g) {
        	//Main.print("drawing");
            super.paintComponent(g);
            g.setFont(Main.font);
            FontMetrics fm = g.getFontMetrics(Main.font);
            
            for (Edge e : edges) {
            	if (e == dragWire)
            		e.drawEndDrag(g, handleX, handleY);
            	else
            		e.draw(g);
            }
            
            for (Node n : nodes) {
            	n.draw(g, fm);
            }
		}

	}
	
	private static final long serialVersionUID = 1L;
	
	private JButton addVar;
	private JButton addEq;
	private JButton wireTool;
	private JButton nodeTool;
	private JButton deleteTool;
	private JButton editTool;
	private JButton evaluateTool;
	//private JLabel label;

	public NodeDisplay(String title) {
		createComponents();
		setSize(1500, 1000);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createResizeListener(this);
		setVisible(true);
	}
	
	// Very long, messy method to make buttons at the top / bottom (and their basic functions)
	private void createComponents() {
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JPanel panel = new Nodes();
		addVar = new JButton("Add Variable"); addVar.setFont(Main.font);
		addEq = new JButton("Add Equation"); addEq.setFont(Main.font);
		wireTool = new JButton("Wiring Tool"); wireTool.setFont(Main.font);
		nodeTool = new JButton("Move Tool"); nodeTool.setFont(Main.font);
		deleteTool = new JButton("Delete Tool"); deleteTool.setFont(Main.font);
		editTool = new JButton("Edit Tool"); editTool.setFont(Main.font);
		evaluateTool = new JButton("Evaluate"); evaluateTool.setFont(Main.font);
		
		addVar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String lbl = Main.getNextAvailableName();
				Node newNode = new VariableNode(Main.randomColor(), "unknown", lbl,
						Math.random() * 0.9, Math.random() * 0.7, Main.scale);
				Nodes.nodes.add(newNode);
				Main.names.put(lbl, (VariableNode)newNode);
				repaint();
			}
		});
		
		addEq.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Nodes.nodes.add(new EquationNode("0 = 0",
						Math.random() * 0.9, Math.random() * 0.7, Main.scale * 1.5));
				repaint();
			}
		});
		
		wireTool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				tool = Tool.WIRE;
				panel.setBackground(Color.GRAY);
			}
		});
		
		nodeTool.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				tool = Tool.NODE;
				panel.setBackground(Color.WHITE);
			}
		});
		
		deleteTool.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent event) {
				tool = Tool.DELETE;
				panel.setBackground(new Color(127, 60, 60));
			}
		});
		
		editTool.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent event) {
				tool = Tool.EDIT;
				panel.setBackground(new Color(200, 150, 0));
			}
		});
		
		evaluateTool.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent event) {
				boolean valid = true;
				for (Node n : Nodes.nodes) {
					if (n instanceof EquationNode && !((EquationNode)n).isValid()) {
						valid = false;
						break;
					}
				}
				if (valid) { // Only switch to evaluate tool if all equations are okay
					tool = Tool.EVALUATE;
					panel.setBackground(new Color(90, 128, 110));
				} else {
					JOptionPane.showMessageDialog(null, "Cannot enter evaluation mode if there are invalid equations.",
							"Invalid Equation(s)", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//label = new JLabel("unneeded label :)");
		bottom.add(addVar);
		bottom.add(addEq);
		
		top.add(nodeTool);
		//top.add(wireTool); // deprecated feature
		top.add(deleteTool);
		top.add(editTool);
		top.add(evaluateTool);
		//panel.add(label);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		
		panel.setBackground(Color.WHITE);
		add(panel);
		add(top, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);
	}
	
	// Adds a listener to maintain a variable for the window dimensions
	private void createResizeListener(NodeDisplay window) {
		this.addComponentListener(new ComponentListener() {
			// Maintains the window dimension variable
		    public void componentResized(ComponentEvent event) {
		    	if (window != null) {
			    	Main.dimensions = window.getBounds();
			    	
			    	for (Node n : Nodes.nodes) {
		            	n.resize();
		            }
		    	}
		    }
		    
		    // Extra methods unused for now
			@Override
			public void componentHidden(ComponentEvent arg0) {}
			@Override
			public void componentMoved(ComponentEvent arg0) {}
			@Override
			public void componentShown(ComponentEvent arg0) {}
		});
	}

		

	@Override
	public void mouseDragged(MouseEvent e) {
		if (!dragging)
			return;
		switch (tool) {
		case NODE:
			dragNode.move(e.getX() - handleX, e.getY() - handleY);
			repaint();
			break;
		case WIRE:
			handleX = e.getX();
			handleY = e.getY();
			repaint();
			break;
		case DELETE:
			break;
		case EDIT:
			break;
		case EVALUATE:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Node n;
		switch (tool) {
		case NODE:
			// Set up parameters for dragging a node (if mouse is over one)
			n = getNode(e);
			if (n != null) {
				dragNode = n;
				dragging = true;
				handleX = e.getX();
				handleY = e.getY();
			}
			break;
		case WIRE:
			// Create a wire anchored on the node clicked, with the mouse controlling the other end
			n = getNode(e);
			if (n != null) {
				dragging = true;
				if (n instanceof VariableNode)
					dragWire = new Edge(n.getColor(), n, null);
				else
					dragWire = new Edge(n.getColor(), null, n);
				Nodes.edges.add(dragWire);
				dragNode = n;
			}
			break;
		case DELETE:
			
			break;
		case EDIT:
			break;
		case EVALUATE:
			break;
		}
	}
	
	// Get the node the mouse is over in the specified event
	private Node getNode(MouseEvent e) {
		for (Node n : Nodes.nodes) {
			if (n.contains(e.getX(), e.getY()))
				return n;
		}
		return null;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if (!dragging)
			return;
		switch (tool) {
		case NODE:
			dragNode.finishMove();
			dragNode = null;
			break;
		case WIRE:
			Node n = getNode(e);
			if (n == null || n.getClass().equals(dragNode.getClass()))
				Nodes.edges.remove(dragWire);
			else {
				dragWire.assignEnd(n);
				for (Edge edge : Nodes.edges) {
					if (edge != dragWire && edge.equals(dragWire))
						Nodes.edges.remove(dragWire);
				}
			}
			dragWire = null;
			dragNode = null;
			repaint();
			break;
		case DELETE:
			break;
		default:
			break;
		}
		dragging = false;
	}


	public void mouseClicked(MouseEvent e) {
		Node n;
		switch (tool) {
		case DELETE:
			n = getNode(e);
			if (n != null) {
				Nodes.nodes.remove(n);
				if (n instanceof VariableNode) {
					Main.names.remove(((VariableNode) n).getSymbol());
				}
				LinkedList<Edge> toRemove = new LinkedList<>();
				for (Edge edge : Nodes.edges) {
					if (edge.end1 == n || edge.end2 == n) {
						toRemove.add(edge);
						if (n instanceof VariableNode) {
							((EquationNode)edge.end2).setInvalid();
							Main.print("setting invalid");
						}
					}
				}
				Nodes.edges.removeAll(toRemove);
				repaint();
			} else {
				// check for edge to delete (somehow...)
			}
			break;
		case EDIT:
			if (editWindow != null) {
				editWindow.dispose();
				editWindow = null;
			}
			n = getNode(e);
			if (n != null) {
				editWindow = new TextEntry(n instanceof VariableNode ? "Edit variable" : "Edit equation",
						Main.scale * 30, Main.scale * 4, n);
			}
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	
}
