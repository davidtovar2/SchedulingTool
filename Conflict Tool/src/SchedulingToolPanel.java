import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SchedulingToolPanel extends JPanel implements ActionListener {
	

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
		textFieldPanel.setLayout(new BorderLayout(5,5));
		
		textField = new JTextArea();
		textField.setPreferredSize(new Dimension(600,400));
		textField.setEditable(true);
		textField.setLineWrap(true);
	
		
		textFieldReport = new JTextArea();
		textFieldReport.setPreferredSize(new Dimension(400, 50));
		textFieldReport.setText("Please Select a File - Import");
		textFieldReport.setEditable(false);
		
//		JScrollPane scroll = new JScrollPane (textField);
//		scroll.setPreferredSize(new Dimension(10,200));
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textFieldPanel.add(textField,BorderLayout.CENTER);
		textFieldPanel.add(textFieldReport,BorderLayout.SOUTH);
		
		//Panel For Buttons
		buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.darkGray);
		
		importButton = new JButton("Import");
		exportButton = new JButton("Export");
		csvButton = new JButton("CSV");
		buttonPanel.add(importButton);
		buttonPanel.add(exportButton);
		buttonPanel.add(csvButton);
		
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(Color.DARK_GRAY);
		
		JPanel emptyPanel1 = new JPanel();
		emptyPanel1.setBackground(Color.DARK_GRAY);
		JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setBackground(Color.DARK_GRAY);
		
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new BorderLayout(5,5));
		this.add(textFieldPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.add(emptyPanel, BorderLayout.EAST);
		this.add(emptyPanel1, BorderLayout.WEST);
		this.add(emptyPanel2, BorderLayout.NORTH);
		
		
		importButton.addActionListener(this);
		exportButton.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		JButton src = (JButton) e.getSource();
		
		if (src == importButton){
			
			chooseFile();
			loadConstraints();	
			
		} else if (src == exportButton){
			JOptionPaneTest();
			//test();
			updateWorkbook();
		}else if( src == csvButton){
			
		}
		
		
	}
	
	private File chooseFile(){
		
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		
		textFieldReport.setText("New Import File:\n" + fc.getSelectedFile().getPath());
		
		return fc.getSelectedFile();
		
	}
	
	private void loadConstraints(){
		
		FileReader fr = null;
		File file = new File("resources/textConstraints.txt");
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

	public void test(){
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet sheet = workbook.createSheet("Persons");
		sheet.setColumnWidth(0, 6000);
		sheet.setColumnWidth(1, 4000);
		 
		XSSFRow header = sheet.createRow(0);
		 
		XSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		 
		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		headerStyle.setFont(font);
		 
		XSSFCell headerCell = header.createCell(0);
		headerCell.setCellValue("Name");
		headerCell.setCellStyle(headerStyle);
		 
		headerCell = header.createCell(1);
		headerCell.setCellValue("Age");
		headerCell.setCellStyle(headerStyle);
		
		//File currDir = new File(".");
		//String path = currDir.getAbsolutePath();
		String fileLocation = "/Users/jolsen/Documents/" + "temp.xlsx"; 
	
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileLocation);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			workbook.write(outputStream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void updateWorkbook(){
		
		try {
			
		FileInputStream inputStream = new FileInputStream(new File("/Users/jolsen/Downloads/Spring 2019 Validation Report Example.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        
        XSSFSheet sheet = workbook.getSheetAt(0);
		
        int maxColumn = 0;
        int[] columnDeleteIndex = {31,30,29,28,27,26,25,23,22,21,20,19,18,15,14,13,12,11,10,9,8,6,5,4,3};
        for(int columnToDelete:columnDeleteIndex) {
        	for ( int r=0; r < sheet.getLastRowNum()+1; r++ ){
            
        		XSSFRow row = sheet.getRow( r );
            
        		if ( row == null )
        			continue;
            
        		int lastColumn = row.getLastCellNum();
        		if ( lastColumn > maxColumn )
        			maxColumn = lastColumn;
            
        		if ( lastColumn < columnToDelete )
        			continue;
            
        		for ( int x=columnToDelete+1; x < lastColumn + 1; x++ ){
        			XSSFCell oldCell = row.getCell(x-1);
        			if ( oldCell != null )
        				row.removeCell( oldCell );
                	
        			XSSFCell nextCell = row.getCell( x );
        			if ( nextCell != null ){
        				@SuppressWarnings("deprecation")
        				XSSFCell newCell = row.createCell(x-1, nextCell.getCellType());
        				cloneCell(newCell, nextCell);
        			}
        		}
        	}
            inputStream.close();
            
            for (Row myrow : sheet){
                sheet.setColumnWidth(myrow.getRowNum(), 6000);;
                for (Cell mycell : myrow){
                    if (myrow.getRowNum() == 1 || myrow.getRowNum() == 19) {
                		XSSFCellStyle style = workbook.createCellStyle();
                        XSSFFont font = workbook.createFont();
                        font.setColor(IndexedColors.RED.getIndex());
                		font.setBold(true);
                		style.setFont(font);
                        mycell.setCellStyle(style);
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream("/Users/jolsen/Documents/" + "temp.xlsx");
            workbook.write(outputStream);
//            workbook.close();
            textFieldReport.setText("New Export File:\n" + "C:\\Users\\jolsen\\Documents\\" + "temp.xlsx");
            outputStream.close();

        }
        
		} catch (IOException | EncryptedDocumentException e){
			// TODO
			e.printStackTrace();
		} 
	}

	private static void cloneCell( XSSFCell cNew, XSSFCell cOld ){
	       
			cNew.setCellComment( cOld.getCellComment() );
	        cNew.setCellStyle( cOld.getCellStyle() );
	        
	        if (CellType.BOOLEAN == cNew.getCellTypeEnum()) {
	            cNew.setCellValue(cOld.getBooleanCellValue());
	        } else if (CellType.NUMERIC == cNew.getCellTypeEnum()) {
	            cNew.setCellValue(cOld.getNumericCellValue());
	        } else if (CellType.STRING == cNew.getCellTypeEnum()) {
	            cNew.setCellValue(cOld.getStringCellValue());
	        } else if (CellType.ERROR == cNew.getCellTypeEnum()) {
	            cNew.setCellValue(cOld.getErrorCellValue());
	        } else if (CellType.FORMULA == cNew.getCellTypeEnum()) {
	            cNew.setCellValue(cOld.getCellFormula());
	        } else {
	        	cNew.setCellValue(cOld.getNumericCellValue());
	        }

	    }
	private static void JOptionPaneTest() {
		    JDialog.setDefaultLookAndFeelDecorated(true);
		    int response = JOptionPane.showConfirmDialog(null, "Do you want to save Constraints?", "Constraints Changed",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      
		    } else if (response == JOptionPane.YES_OPTION) {
		      
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		    	
		    }
		  
		}
}
