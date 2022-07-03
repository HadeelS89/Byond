package com.beyond.helpers;


import java.util.HashMap;
import java.util.Map;

public enum ExcelSheetHeaders {

    AcademicCareers(0),
    AgeRangeMin(1),
    AgeRangeMax(2),
    Nationality(3),
    PreferredUniversity(4),
Count(5),
    PreferredMajor(6),
            ProvideVenue(0),
    DescriptionEnglish(1),
    DescriptionArabic(2),
    Capacity(3),
    Country(0),
    BankName(1),
    BranchName(2),
    AddressLine1(3),
    AddressLine2(4),
    IABN(5),
    PayElementNameInput(0),
    Amount(1);

    private int value;
    private static Map map = new HashMap<>();

    private ExcelSheetHeaders(int value) {
        this.value = value;
    }

    static {
        for (ExcelSheetHeaders excelSheetHeaders : ExcelSheetHeaders.values()) {
            map.put(excelSheetHeaders.value, excelSheetHeaders);
        }
    }

    public static ExcelSheetHeaders valueOf(int pageType) {
        return (ExcelSheetHeaders) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}