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

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SchedulingToolPanel extends JPanel implements ActionListener{
	

	private JPanel textFieldPanel;
	private JPanel buttonPanel;
	private JButton importButton;
	private JButton exportButton;
	private JButton csvButton;
	private JTextArea textField;
	private JTextArea textFieldReport;
	
	SchedulingToolPanel(){
	
		//Panel For Text Fields
		textFieldPanel = new JPanel();
		textFieldPanel.setBackground(Color.darkGray);
		
		textField = new JTextArea();
		textField.setPreferredSize(new Dimension(400,500));
		textField.setEditable(true);
		textField.setLineWrap(true);
		textField.setAutoscrolls(true);
		textField.setText("Constraints");
		
		int tfHeight = textField.getHeight();
		int tfWidth = textField.getWidth();
		
		textFieldReport = new JTextArea();
		//textFieldReport.setPreferredSize(new Dimension(400, 500));
		textFieldReport.setBounds(0, 510, 
				tfWidth, tfHeight - (tfHeight - 20));
		textFieldReport.setText("Report Generated");
		
//		JScrollPane scroll = new JScrollPane (textField);
//		scroll.setPreferredSize(new Dimension(10,200));
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textFieldPanel.add(textField);
		textFieldPanel.add(textFieldReport);
		
		//Panel For Buttons
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.darkGray);
		
		importButton = new JButton("Import");
		exportButton = new JButton("Export");
		csvButton = new JButton("CSV");
		buttonPanel.add(importButton);
		buttonPanel.add(exportButton);
		buttonPanel.add(csvButton);
		
		this.setLayout(new BorderLayout());
		this.add(textFieldPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		
		importButton.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		JButton src = (JButton) e.getSource();
		
		if (src == importButton){
			
			chooseFile();
		
			/*File file = new File(chooseFile().getPath());
			
			FileReader fr = null;
			try {
				fr = new FileReader(chooseFile().getPath());
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
			b.append(chooseFile().getPath());
					textField.setText(b.toString());
		}else if(src == exportButton){
			
		}else if( src == csvButton){*/
			
		}
		
		/*File file = new File(chooseFile().getAbsolutePath());
		
		FileReader fr = null;
		try {
			fr = new FileReader(chooseFile().getAbsolutePath());
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
		b.append(chooseFile().getAbsolutePath());
				textField.setText(b.toString());*/
	
		
	}
	
	private File chooseFile(){
		
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		
		textFieldReport.setText(fc.getSelectedFile().getPath());
		
		return fc.getSelectedFile();
		
	}

}
