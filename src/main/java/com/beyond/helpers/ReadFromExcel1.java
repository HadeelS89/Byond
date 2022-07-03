package com.beyond.helpers;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;


public class ReadFromExcel1 {

    static XSSFWorkbook wb;
    static XSSFSheet sheet1;

    public ReadFromExcel1(String Excelpath) throws IOException {
        // TODO Auto-generated method stub

        try {
            File file = new File(Excelpath);

            //Create an object of FileInputStream class to read excel file

            FileInputStream fis = new FileInputStream(file);
            wb = new XSSFWorkbook(fis);

        } catch (Exception e) {

        }
    }


    public static String getData2(String sheetName, String headerName) {
        sheet1 = wb.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        Random random = new Random();

        Iterator<Row> rows = sheet1.rowIterator();
        Row row1 = rows.next();

        int row = 0;

        if (row1.getRowNum() == 0) {
            row = 1;
        } else {
            row = random.nextInt(row1.getRowNum() + sheet1.getLastRowNum());
            System.out.println("Row num = " + row);
        }


        //int row = random.nextInt((sheet1.getLastRowNum())+(sheet1.getFirstRowNum()+1));


        Map<String, XSSFCell> map = new HashMap<>();
        map.put("AgeMin", sheet1.getRow(row).getCell(ExcelSheetHeaders.AgeRangeMin.getValue()));
        //cell.setCellType(Cell.CELL_TYPE_STRING);
        map.put("AcademicCareers", sheet1.getRow(row).getCell(ExcelSheetHeaders.AcademicCareers.getValue()));
        map.put("Nationality", sheet1.getRow(row).getCell(ExcelSheetHeaders.Nationality.getValue()));
        map.put("PreferredUniversity", sheet1.getRow(row).getCell(ExcelSheetHeaders.PreferredUniversity.getValue()));
        map.put("Count", sheet1.getRow(row).getCell(ExcelSheetHeaders.Count.getValue()));
        map.put("PreferredMajor", sheet1.getRow(row).getCell(ExcelSheetHeaders.PreferredMajor.getValue()));
        map.put("ProvideVenue", sheet1.getRow(row).getCell(ExcelSheetHeaders.ProvideVenue.getValue()));
        map.put("DescriptionEnglish", sheet1.getRow(row).getCell(ExcelSheetHeaders.DescriptionEnglish.getValue()));
        map.put("DescriptionArabic", sheet1.getRow(row).getCell(ExcelSheetHeaders.DescriptionArabic.getValue()));
        map.put("Capacity", sheet1.getRow(row).getCell(ExcelSheetHeaders.Capacity.getValue()));
        map.put("Country", sheet1.getRow(row).getCell(ExcelSheetHeaders.Country.getValue()));
        map.put("BankName", sheet1.getRow(row).getCell(ExcelSheetHeaders.BankName.getValue()));
        map.put("BranchName", sheet1.getRow(row).getCell(ExcelSheetHeaders.BranchName.getValue()));
        map.put("AddressLine1", sheet1.getRow(row).getCell(ExcelSheetHeaders.AddressLine1.getValue()));
        map.put("AddressLine2", sheet1.getRow(row).getCell(ExcelSheetHeaders.AddressLine2.getValue()));
        map.put("IABN", sheet1.getRow(row).getCell(ExcelSheetHeaders.IABN.getValue()));
        map.put("PayElementNameInput", sheet1.getRow(row).getCell(ExcelSheetHeaders.PayElementNameInput.getValue()));
        map.put("Amount", sheet1.getRow(row).getCell(ExcelSheetHeaders.Amount.getValue()));

        String data = formatter.formatCellValue(map.get(headerName));
        return data;
    }

    public static String getData1(String sheetName, String column) {
        sheet1 = wb.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        Random random = new Random();
        Iterator<Row> rows = sheet1.rowIterator();
        Row row1 = rows.next();
        //get columns size
        Iterator<Cell> cols = row1.cellIterator();

        int row = 0;
        XSSFCell cell = null;
        int col = 0;
        while (cols.hasNext()) {
            cell = sheet1.getRow(row).getCell(col);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (cell.getStringCellValue().equalsIgnoreCase(column)) {
                break;
            }
            col++;
        }

        int newRow = sheet1.getLastRowNum();
        int randomRow = ActionsHelper.getRandomNumber(1, newRow+1);

        cell = sheet1.getRow(randomRow).getCell(col);

        String data = formatter.formatCellValue(cell);
        return data;
    }
    public static String getDataWithoutRand(String sheetName, String column,int Row) {

        sheet1 = wb.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        //Random random = new Random();
        Iterator<Row> rows = sheet1.rowIterator();
        Row row1 = rows.next();
        //get columns size
        Iterator<Cell> cols = row1.cellIterator();

        int row = 0;
        XSSFCell cell = null;
        int col = 0;
        while (cols.hasNext()) {
            cell = sheet1.getRow(row).getCell(col);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (cell.getStringCellValue().equalsIgnoreCase(column)) {
                break;
            }
            col++;
        }

        int newRow = sheet1.getLastRowNum()+1;
        //int randomRow = ActionsHelper.getRandomNumber(1, newRow+1);

        cell = sheet1.getRow(Row).getCell(col);

        String data = formatter.formatCellValue(cell);
        return data;
    }

    public static HashMap<Integer, String> getDataWithoutRand1(String sheetName, String column,int Row) {
        HashMap<Integer, String> sheetMap = new HashMap<>();

        sheet1 = wb.getSheet(sheetName);
        DataFormatter formatter = new DataFormatter();
        //Random random = new Random();
        Iterator<Row> rows = sheet1.rowIterator();
        Row row1 = rows.next();
        //get columns size
        Iterator<Cell> cols = row1.cellIterator();

        int row = 0;
        XSSFCell cell = null;
        int col = 0;
        while (cols.hasNext()) {
            cell = sheet1.getRow(row).getCell(col);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (cell.getStringCellValue().equalsIgnoreCase(column)) {
                break;
            }
            col++;
        }

        int newRow = sheet1.getLastRowNum()+1;
        //int randomRow = ActionsHelper.getRandomNumber(1, newRow+1);

        cell = sheet1.getRow(Row).getCell(col);

        String data = formatter.formatCellValue(cell);
        sheetMap.put(Row, data);
        return sheetMap;
    }

    public static void main(String[] args) throws IOException {

        //System.out.println(ActionsHelper.getRandomNumber(0, 10));
        String value = ReadWriteHelper.readFromExcel("programData", "Configuration", "AgeRangeMax");
        System.out.println(value);
    }


    public int GetRowCount(String SheetName) {

        int row = wb.getSheet(SheetName).getLastRowNum();

        row = row + 1;

        return row;
    }
}