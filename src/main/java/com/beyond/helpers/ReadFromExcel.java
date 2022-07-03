package com.beyond.helpers;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;


public class ReadFromExcel {

    public int row;
    public XSSFCell cell;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;


//    public static void main(String[] args) throws IOException {
//
//
//        new ReadFromExcel("src/main/resources/DataProvider/programData.xlsx");
//        System.out.println(GetData1("Configuration"));
//        System.out.println(Getcolumn("Configuration"));
//    }

    //1
    public ReadFromExcel(String Excelpath, String sheetName) throws IOException {

        try {
            File file = new File(Excelpath);

            //Create an object of FileInputStream class to read excel file

            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet=getSheetByName(workbook, sheetName);
            row = GetTestDataRandomly(sheet);
        } catch (Exception e) {

        }
    }

    /*public void ExcelDataConfigOut(String Excelpath2) throws IOException {
        // TODO Auto-generated method stub

        try {
            File file = new File(Excelpath2);

            //Create an object of FileInputStream class to read excel file

            FileOutputStream fis = new FileOutputStream(file);

            wb.write(fis);
            fis.flush();
            fis.close();

        } catch (Exception e) {

        }
    }
*/
    //2
    public static XSSFSheet getSheetByName(XSSFWorkbook wb, String sheetName) {
        return wb.getSheet(sheetName);
    }

    //3
    public static int GetTestDataRandomly(XSSFSheet sheet) {
        Random random = new Random();
        int row=random.nextInt(sheet.getLastRowNum()-1)+1;

        return row;
    }

    //4
    public String getCellData(XSSFCell cell) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);

    }
}
// public static String GetData1(String sheetName) {

//
//        XSSFCell cell = sheet1.getRow(row).getCell(ExcelSheetHeaders.CODING.getValue());
//
//
//        String data = formatter.formatCellValue(cell);
//        return data;
//}

// object thats hold enum header data and get it from excel sheet
// method to populate data from excel sheet by enum
//


//    public String writeXLSXFile3(String sheetName, int rows, int colums) {
//        sheet1 = wb.createSheet(sheetName);
//        Random random = new Random();
//        int row = random.nextInt(sheet1.getLastRowNum());
//        XSSFCell cell = sheet1.createRow(row).createCell(colums);
//
//        DataFormatter formatter = new DataFormatter();
//
//
//        String data = formatter.formatCellValue(cell);
//        return data;
//    }


//    // read from sheet method for string cells
//
//    public static String GetData(String sheetName) {
//
//        sheet1 = wb.getSheet(sheetName);
//
//        Random random = new Random();
//        int row = random.nextInt(sheet1.getLastRowNum());
//        String data = sheet1.getRow(row).getCell(0).getStringCellValue();
//        return data;
//
//
//    }
//
//    public static int GetRowCount(String SheetName) {
//
//        int row = wb.getSheet(SheetName).getLastRowNum();
//
//        row = row + 1;
//
//        return row;
//    }
//
//    public static int Getcolumn(String sheetName) {
//
//        sheet1 = wb.getSheet(sheetName);
//        int rowIndex = sheet1.getLastRowNum();
//        XSSFRow row = sheet1.getRow(rowIndex);
//
//        DriverType.valueOf(DriverType.CHROME.name());
//
//
//        return row.getPhysicalNumberOfCells();
//
//    }
//
//
////read from sheet method for boolean cells
//
//    public boolean GetDataBoolean(String sheetName, int Row, int column) {
//// TODO Auto-generated method stub
//        sheet1 = wb.getSheet(sheetName);
//        boolean data = sheet1.getRow(Row).getCell(column).getBooleanCellValue();
//        System.out.println(data);
//        return true;
////return data;
//    }

//}



