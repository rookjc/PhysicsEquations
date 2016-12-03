import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	Rectangle dimensions;
	
	public static class Nodes extends JPanel {
		private static final long serialVersionUID = 1L;

		static LinkedList<Node> nodes = new LinkedList<>();
		static Font font;

		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(200, 200, 200, 200);
            
            for (Node n : nodes) {
            	n.draw(g, font);
            }
		}
	}
	
	private static final long serialVersionUID = 1L;
	
	private JButton button;
	private JLabel label;

	public NodeDisplay(String title) {
		Nodes.font = new Font("Serif", Font.PLAIN, 20);
		createComponents();
		setSize(600, 600);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createResizeListener(this);
		setVisible(true);
	}
	
	private void createComponents() {
		JPanel panel = new Nodes();
		button = new JButton("DO NOT PUSH");
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				label.setText(label.getText() + "e");
			}
		});
		
		label = new JLabel("push this pls");
		panel.add(button);
		panel.add(label);
		add(panel);
	}
	
	// Adds a listener to maintain a variable for the window dimensions
	private void createResizeListener(NodeDisplay window) {
		this.addComponentListener(new ComponentListener() {
			// Maintains the window dimension variable
		    public void componentResized(ComponentEvent event) {
		    	if (window != null)
			    	dimensions = window.getBounds();
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
