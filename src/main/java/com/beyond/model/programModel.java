package com.beyond.model;

import com.beyond.helpers.ExcelSheetHeaders;
import com.beyond.helpers.ReadFromExcel;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class programModel {
    int row;
    public static ReadFromExcel readFromExcel;

    public programModel() {
        try {
            readFromExcel = new ReadFromExcel("src/main/resources/DataProvider/programData.xlsx", "Configuration");
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        this.row = readFromExcel.row;
    }

    private String userFieldSearch;
    public String ageRangeMin;
    public String ageRangeMax;



    public programModel populateData() {
        programModel testmode = new programModel();

        testmode.setUserFieldSearch(readFromExcel.getCellData(readFromExcel.sheet.getRow(row).getCell(ExcelSheetHeaders.AcademicCareers.getValue())));
        testmode.setAgeRangeMin(readFromExcel.getCellData(readFromExcel.sheet.getRow(row).getCell(ExcelSheetHeaders.AgeRangeMin.getValue())));
        testmode.setAgeRangeMax(readFromExcel.getCellData(readFromExcel.sheet.getRow(row).getCell(ExcelSheetHeaders.AgeRangeMax.getValue())));
        testmode.setUserFieldSearch(readFromExcel.getCellData(readFromExcel.sheet.getRow(row).getCell(ExcelSheetHeaders.AcademicCareers.getValue())));
        testmode.setUserFieldSearch(readFromExcel.getCellData(readFromExcel.sheet.getRow(row).getCell(ExcelSheetHeaders.AcademicCareers.getValue())));
        testmode.setUserFieldSearch(readFromExcel.getCellData(readFromExcel.sheet.getRow(row).getCell(ExcelSheetHeaders.AcademicCareers.getValue())));
        testmode.setUserFieldSearch(readFromExcel.getCellData(readFromExcel.sheet.getRow(row).getCell(ExcelSheetHeaders.AcademicCareers.getValue())));
        return testmode;
    }

}
