/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infra_stock;
import java.awt.List;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFontFormatting;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class test2 {

	public static void readXLSFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream("test.xls");
		HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

		HSSFSheet sheet=wb.getSheetAt(0);
		HSSFRow row; 
		HSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(HSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			
			while (cells.hasNext())
			{
				cell=(HSSFCell) cells.next();
		
				if (cell.getCellType() == CellType.STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == CellType.NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}
	
	}
	
	public static void writeXLSFile() throws IOException {
		
		String excelFileName = "Test2.xls";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName) ;
                

                HSSFCellStyle style=wb.createCellStyle();
                style.setBorderBottom(BorderStyle.THIN);
                style.setBorderTop(BorderStyle.THIN);
                style.setBorderRight(BorderStyle.THIN);
                style.setBorderLeft(BorderStyle.THIN);
                
                
                HSSFCellStyle merged_style=wb.createCellStyle();
                style.setAlignment(HorizontalAlignment.LEFT);
                
                 HSSFCellStyle first_row=wb.createCellStyle();
                first_row.setAlignment(HorizontalAlignment.CENTER);
                
                style.getFont(wb).setFontName("Times New Roman");
                style.getFont(wb).setBold(true);
                style.getFont(wb).setFontHeightInPoints((short)14);
                
                     try {
            java.sql.Connection conn1 = Connect.ConnectDB("infra2");
            Statement stm = conn1.createStatement();
            ResultSet rs = stm.executeQuery("select * from Produit where sous='BFA' and type_C_N='C'");
            
           
            
            
            //iterating r number of rows
		for (int r=0;r < 3; r++ )
		{
			HSSFRow row = sheet.createRow(r);
                        row.setRowStyle(first_row);
			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				HSSFCell cell = row.createCell(c);
				cell.setCellStyle(style);
                                
                               
				
                                
			}
                        sheet.addMergedRegion(new CellRangeAddress(r, r, 0, 4));
                     }   
                
                        sheet.autoSizeColumn(0, true);
                        sheet.autoSizeColumn(1, true);
                        sheet.autoSizeColumn(2, true);
                        sheet.autoSizeColumn(3, true);
                        sheet.autoSizeColumn(4, true);
                       sheet.getRow(0).getCell(0).setCellValue("ETAT  DE  STOCK   BFA");
                       sheet.getRow(1).getCell(0).setCellValue("حالة العتاد المستهلك");
                       sheet.getRow(2).getCell(0).setCellValue("30/09/2018");
                       HSSFRow header_row = sheet.createRow(3);
                       HSSFCell cell1 = header_row.createCell(0);
                       cell1.setCellValue("N°");
                       HSSFCell cell2 = header_row.createCell(1);
                       cell2.setCellStyle(merged_style);
                       cell2.setCellValue("NATURE  DU  MATERIEL");
                       
                       HSSFCell cell3 = header_row.createCell(2);
                       cell3.setCellValue("QUANTITE");
                       HSSFCell cell4 = header_row.createCell(3);
                       cell4.setCellValue("P.U");
                       HSSFCell cell5 = header_row.createCell(4);
                       cell5.setCellValue("DECOMPTE");
            int row_count=3;
            int id = 1;
            
            while (rs.next()) {
                int prix=rs.getInt("prix");
                int quantity=rs.getInt("quantity");
                
                HSSFRow row = sheet.createRow(row_count);
                HSSFCell cell_id = row.createCell(0);
		cell_id.setCellStyle(style);
                cell_id.setCellValue(id);
                
                HSSFCell cell_nature = row.createCell(1);
		cell_nature.setCellStyle(style);
                cell_nature.setCellValue(rs.getString("designation"));
                
                HSSFCell cell_quantite = row.createCell(2);
		cell_quantite.setCellStyle(style);
                cell_quantite.setCellValue(quantity);
                
                HSSFCell cell_prix = row.createCell(3);
		cell_prix.setCellStyle(style);
                cell_prix.setCellValue(prix);
                
               HSSFCell cell_decompte = row.createCell(4);
                cell_decompte.setCellStyle(style);
                cell_decompte.setCellValue(prix*quantity);
                 
                  //iterating r number of rows to write to excel
		
                  row_count++;
                  id++;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.getSQLState();
            e.printStackTrace();
        }
                
                
                

		
		
                
		FileOutputStream fileOut = new FileOutputStream(excelFileName);
		
		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
	
	public static void readXLSXFile() throws IOException
	{
		InputStream ExcelFileToRead = new FileInputStream("bfa 10 octobre.xlsx");
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
		
		XSSFWorkbook test = new XSSFWorkbook(); 
		
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row; 
		XSSFCell cell;

		Iterator rows = sheet.rowIterator();

		while (rows.hasNext())
		{
			row=(XSSFRow) rows.next();
			Iterator cells = row.cellIterator();
			while (cells.hasNext())
			{
				cell=(XSSFCell) cells.next();
		
				if (cell.getCellType() == CellType.STRING)
				{
					System.out.print(cell.getStringCellValue()+" ");
				}
				else if(cell.getCellType() == CellType.NUMERIC)
				{
					System.out.print(cell.getNumericCellValue()+" ");
				}
				else
				{
					//U Can Handel Boolean, Formula, Errors
				}
			}
			System.out.println();
		}
	
	}
        
	
	public static void writeXLSXFile() throws IOException {
		
		String excelFileName = "Test2.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;

		//iterating r number of rows
		for (int r=0;r < 5; r++ )
		{
			XSSFRow row = sheet.createRow(r);

			//iterating c number of columns
			for (int c=0;c < 5; c++ )
			{
				XSSFCell cell = row.createCell(c);
	
				cell.setCellValue("Cell "+r+" "+c);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public static void main(String[] args) throws IOException {
		
		readXLSXFile();
		//readXLSFile();
		
		//writeXLSXFile();
		//readXLSXFile();

	}

}






/*
public class test2
{  
 
    public static void main(String[] args)  {
       /* Workbook work=new HSSFWorkbook();
        
        Sheet sh=work.createSheet();
        Sheet sh2=work.createSheet("السلام");
        
        try {
            FileOutputStream fo=new FileOutputStream("test.xls");
            try {
                work.write(fo);
                fo.close();
            } catch (IOException ex) {
                Logger.getLogger(test2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
    }
      

}*/