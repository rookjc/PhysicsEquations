import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextEntry extends JFrame {
	private static Pattern varLabelMatch = Pattern.compile("(.*)\\((.{1,3})\\)");
	
	public TextEntry(String title, int sizeX, int sizeY, Node subject) {
		createComponents(this, subject);
		setSize(sizeX, sizeY);
		setTitle(title);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setVisible(true);
	}
	
	private JButton OK;
	private JTextField input;
	
	private void createComponents(JFrame frame, Node subject) {
		JPanel bottom = new JPanel();
		
		OK = new JButton("OK");
		OK.setFont(Main.font);
		input = new JTextField("whatever");
		input.setFont(Main.font);
		input.setColumns(25);
		input.setText(subject.getLabel());
		
		OK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (subject instanceof VariableNode) {
					Matcher m = varLabelMatch.matcher(input.getText());
					if (!m.matches()) {
						JOptionPane.showMessageDialog(null, "Parse error.", "Parse Error", JOptionPane.ERROR_MESSAGE);
					} else {
						((VariableNode)subject).setName(m.group(1));
						((VariableNode)subject).setLabel(m.group(2));
						frame.dispose();
						Main.window.repaint();
					}
				} else {
					((EquationNode)subject).resetWidth();
					((EquationNode)subject).setLabel(input.getText());
					frame.dispose();
					Main.window.repaint();
				}
			}
		});
		
		bottom.add(input);
		bottom.add(OK);
		bottom.setLayout(new GridBagLayout());
		add(bottom, BorderLayout.CENTER);
	}
	
}
