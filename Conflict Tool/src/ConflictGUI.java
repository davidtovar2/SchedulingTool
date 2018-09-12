import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;


import javax.swing.*;

public class ConflictGUI extends Frame implements ActionListener{
	
	private JFrame frame;
	private JPanel panel;
	private JButton importButton;
	private JButton exportButton;
	private JButton csvButton;
	private JTextArea textField;
	
	ConflictGUI(){
		frame = new JFrame();
		frame.setSize(new Dimension(400,400));
		
		panel = new JPanel();
		panel.setBackground(Color.darkGray);
		frame.getContentPane().add(panel);
		textField = new JTextArea();
		textField.setPreferredSize(new Dimension(350,300));
		textField.setEditable(true);
		textField.setLineWrap(true);
		textField.setAutoscrolls(true);
//		JScrollPane scroll = new JScrollPane (textField);
//		scroll.setPreferredSize(new Dimension(10,200));
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		textField.setText("temp Strings");
		panel.add(textField);
		importButton = new JButton("Import");
		exportButton = new JButton("Export");
		csvButton = new JButton("CSV");
		panel.add(importButton);
		panel.add(exportButton);
		panel.add(csvButton);
		
		frame.setLocationRelativeTo(null);
		frame.setTitle("Simple Mockup Design");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		importButton.addActionListener(this);
	}
	public static void main(String[] args) {
		ConflictGUI gui = new ConflictGUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		//textField.setText("Hello");
		File file = new File("resources/textConstraints.txt");
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader myInput = new BufferedReader(fr);

		String s;
		StringBuffer b = new StringBuffer();
		try {
			while ((s = myInput.readLine()) != null) {
			b.append(s);
			b.append("\n");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				textField.setText(b.toString());
		
	}

}
