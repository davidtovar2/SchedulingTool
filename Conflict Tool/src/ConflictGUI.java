import java.awt.*;
import javax.swing.*;

public class ConflictGUI {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(400,400));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.darkGray);
		frame.getContentPane().add(panel);
		JTextArea textField = new JTextArea();
		textField.setPreferredSize(new Dimension(350,300));
		textField.setEditable(true);
		textField.setLineWrap(true);
		textField.setAutoscrolls(true);
//		JScrollPane scroll = new JScrollPane (textField);
//		scroll.setPreferredSize(new Dimension(10,200));
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		textField.setText("temp Strings");
		panel.add(textField);
		JButton importButton = new JButton("Import");
		JButton exportButton = new JButton("Export");
		JButton csvButton = new JButton("CSV");
		panel.add(importButton);
		panel.add(exportButton);
		panel.add(csvButton);
		
		frame.setLocationRelativeTo(null);
		frame.setTitle("Simple Mockup Design");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
