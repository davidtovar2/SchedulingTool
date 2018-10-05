import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
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
		textFieldReport.setEditable(false);
		
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
		exportButton.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	
		
		JButton src = (JButton) e.getSource();
		
		if (src == importButton){
			
			chooseFile();
			loadConstraints();	
			
		} else if (src == exportButton){
	
			//test();
			updateWorkbook();
		}else if( src == csvButton){
			
		}
		
		
	}
	
	private File chooseFile(){
		
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		
		textFieldReport.setText(fc.getSelectedFile().getPath());
		
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
		String fileLocation = "/Users/davidtovar/Documents/" + "temp.xlsx"; 
	
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
			
		FileInputStream inputStream = new FileInputStream(new File("/Users/davidtovar/Downloads/Spring 2019 Validation Report Example (1).xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		//XSSFWorkbook workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
        
        XSSFSheet sheet = workbook.getSheetAt(0);
        
        int maxColumn = 0;
        int columnToDelete = 0;
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
            
            inputStream.close();
            
            FileOutputStream outputStream = new FileOutputStream("/Users/davidtovar/Documents/" + "temp.xlsx");
            workbook.write(outputStream);
           // workbook.close();
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
}
