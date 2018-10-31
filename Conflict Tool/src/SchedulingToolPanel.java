import java.awt.*;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.StringTokenizer;
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
	private JButton constraintsButton;
	private JTextArea textField;
	private JTextArea textFieldReport;
	private File constraints;
	private ArrayList<Course> courseList = new ArrayList<Course>();
	
	SchedulingToolPanel(){
	
		constraints = new File("resources/textConstraints.txt");
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
		constraintsButton = new JButton("Constraints");
		buttonPanel.add(constraintsButton);
		buttonPanel.add(importButton);
		buttonPanel.add(exportButton);
		
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
		constraintsButton.addActionListener(this);
		
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
		}else if(src == constraintsButton) {
			constraints = constraintsFile();
		}
		
		
	}
	
	private File chooseFile(){
		
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		
		textFieldReport.setText("New Import File:\n" + fc.getSelectedFile().getPath());
		
		return fc.getSelectedFile();
		
	}
	
	// opens Constraints file location.
	private File constraintsFile(){
		
		final JFileChooser fc = new JFileChooser();
		File constraintsArea = new File("C:\\Users\\jolsen\\eclipse-workspace\\ConflictMngTool\\Resources");
		fc.setCurrentDirectory(constraintsArea);
		fc.showOpenDialog(this);
		
		textFieldReport.setText("New Constraints File:\n" + fc.getSelectedFile().getPath());
		
		return fc.getSelectedFile();
		
	}
	
	private void loadConstraints(){
		
		FileReader fr = null;
		try {
			fr = new FileReader(constraints);
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
        int[] columnDeleteIndex = {31,30,29,28,27,26,25,23,22,21,20,18,15,14,13,12,11,10,9,8,6,5,4,3};
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
        }
        inputStream.close();
        
        // runs through sheet, adding all rows that have time slots as courses into CourseList
        sheet.createFreezePane(0, 1);
        for ( int r=0; r < sheet.getLastRowNum(); r++ ) {
            sheet.autoSizeColumn(r);
        }
        for (Row myRow: sheet) {
    		if (myRow.getCell(4) == null || myRow.getRowNum() == 0) {
    		}else {	
            	courseList.add(addCourse(myRow));
        	}
        }
        checkProfessorsRooms(courseList,workbook);
        
        FileOutputStream outputStream = new FileOutputStream("/Users/jolsen/Documents/" + "temp.xlsx");
        workbook.write(outputStream);
//        workbook.close();
        textFieldReport.setText("New Export File:\n" + "C:\\Users\\jolsen\\Documents\\" + "temp.xlsx");
        outputStream.close();

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
	
	// Breaks down time Slot value down to individual hours and minutes so that it can be
	// entered into a the Course as a java Time value.  Should account for pm values.
	// Currently prints the course to be added to the console for error checking.
	private static Course addCourse(Row newCourse) {
			char[] timeBreak = newCourse.getCell(4).toString().toCharArray();
			int startHour = timeBreak[1]-'0' + (timeBreak[0]-'0')*10;
			int endHour = timeBreak[10]-'0' + (timeBreak[9]-'0')*10;
			int startMinute = timeBreak[4]-'0' + (timeBreak[3]-'0')*10;
			int endMinute = timeBreak[13]-'0' + (timeBreak[12]-'0')*10;
			int[] dayArray = new int[5];
			String days = null;
			StringTokenizer daysBreak = new StringTokenizer(newCourse.getCell(4).toString());
			while (daysBreak.hasMoreElements()) {
				days = (String)daysBreak.nextElement();
			}
			if (timeBreak[6] == 'p' && startHour != 12) {
				startHour+=12;
			}
			if (timeBreak[15] == 'p' && endHour != 12) {
				endHour+=12;
			}
			if (days.contains("Mo")) {dayArray[0] = 1;}
			if (days.contains("Tu")) {dayArray[1] = 1;}
			if (days.contains("We")) {dayArray[2] = 1;}
			if (days.contains("Th")) {dayArray[3] = 1;}
			if (days.contains("Fr")) {dayArray[4] = 1;}
			
			Time startTime = new Time(startHour, startMinute, 0);
			Time endTime = new Time(endHour, endMinute, 0);
			Course course = new Course(newCourse.getRowNum(),newCourse.getCell(1).getNumericCellValue(),newCourse.getCell(2).toString(), startTime, endTime,newCourse.getCell(5).toString(), days, dayArray, newCourse.getCell(7).toString(), newCourse.getCell(6).toString());
			System.out.print(course.toString());
			return course;
	}
	
	// Takes courseList and workbook for comparisons and highlighting fields that are in Conflict.
	private static void checkProfessorsRooms(ArrayList<Course> courseList,XSSFWorkbook workbook) {
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);
		
		XSSFCellStyle style2 = workbook.createCellStyle();
		XSSFFont font2 = workbook.createFont();
		font2.setColor(IndexedColors.BLUE.getIndex());
		font2.setBold(true);
		style2.setFont(font2);
		
		for(Course currentCourse:courseList) {
			for(Course checkCourse:courseList) {
				if (currentCourse.conflict(checkCourse)) {
					if (currentCourse.graduateLevel(checkCourse)) {
						Row conflictRow = sheet.getRow(currentCourse.getRowID());
        				Cell conflictCell = conflictRow.createCell(conflictRow.getLastCellNum());
        				conflictCell.setCellValue("Potential Conflict Or Graduate Class");
	            		for (Cell testcell : conflictRow){
	        				testcell.setCellStyle(style2);

	            		}	
					} else {
						Row conflictRow = sheet.getRow(currentCourse.getRowID());
        				Cell conflictCell = conflictRow.createCell(conflictRow.getLastCellNum());
        				conflictCell.setCellValue("Conflict");
	            		for (Cell testcell : conflictRow){
	        				testcell.setCellStyle(style);
	            		}	
					}
				}
			}
		}
	}
}
