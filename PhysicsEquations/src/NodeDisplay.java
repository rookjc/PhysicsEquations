import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NodeDisplay extends JFrame {
	
	public static class Nodes extends JPanel {
		private static final long serialVersionUID = 1L;
		
		static LinkedList<Node> nodes = new LinkedList<>();

		@Override
        protected void paintComponent(Graphics g) {
        	//Main.print("drawing");
            super.paintComponent(g);
            
            for (Node n : nodes) {
            	n.draw(g, Main.font);
            }
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	private JButton button;
	private JLabel label;

	public NodeDisplay(String title) {
		createComponents();
		setSize(600, 600);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createResizeListener(this);
		setVisible(true);
	}
	
	private void createComponents() {
		JPanel panel = new Nodes();
		button = new JButton("Add Variable");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Nodes.nodes.add(new VariableNode(Color.CYAN, "x",
						Math.random(), Math.random(), 40));
				repaint();
			}
		});
		
		label = new JLabel("unneeded label :)");
		panel.add(button);
		panel.add(label);
		add(panel);
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
	
}
