package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
		
		static XSSFWorkbook wb;
		static XSSFSheet sh1;
		
		public ExcelUtility(String excelPath) {
			try {
				File source= new File(excelPath);
				FileInputStream inputFile=new FileInputStream(source);
				
				wb=new XSSFWorkbook(inputFile);
				
				System.out.println(wb);
			}catch(Exception e) {
			   System.out.println(e.getMessage());
			}
		}
		public String getCellData(String sheetName, int rowNum,int colNum) {
			sh1=wb.getSheet(sheetName);
			System.out.println(sh1);
			String data=sh1.getRow(rowNum).getCell(colNum).getStringCellValue();
			System.out.println(data);
			return data;
		}
		public void setCellData(String sheetName,int rowNum, int colNum,String status) {
			sh1=wb.getSheet(sheetName);
			
			Row r1=sh1.getRow(rowNum);
			Cell c1=r1.getCell(colNum);
			
			if(c1==null) {
				c1=r1.createCell(colNum);
				c1.setCellValue(status);
			}
			else {
				c1.setCellValue(status);
			}	
		}
		
		public int getNumOfRows(String SheetName) {
			
			sh1=wb.getSheet(SheetName);
			
			System.out.println(sh1);
			
			int totalRows=sh1.getLastRowNum()+1;
			
			System.out.println("Total rows are:"+totalRows);
			return totalRows;
		}
		
		public int getNumofCols(String sheetName) {
			sh1=wb.getSheet(sheetName);
			
			int totalCols=sh1.getRow(0).getLastCellNum();
			
			System.out.println("Total columns are:"+ totalCols);
		     return totalCols;	
		}
		public static void writeAndSaveExcel(String excelPath) {
			try {
				File destPath= new File(excelPath);
				FileOutputStream outputFile=new FileOutputStream(destPath);
				wb.write(outputFile);
				outputFile.flush();
				outputFile.close();
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
}
