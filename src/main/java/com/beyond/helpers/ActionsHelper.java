package com.beyond.helpers;

import com.paulhammant.ngwebdriver.NgWebDriver;
import com.beyond.common.Base;
import com.beyond.pagesORCmds.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.*;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionsHelper extends Base {
    private static final Logger LOGGER = LogManager.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
    public static WebDriverWait wait;
    public static int waitTime = 60;
    public Data data;


    public static void waitForSeconds(Integer timeWait, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(timeWait, TimeUnit.SECONDS);
    }


    public static void sendKeyElementFromList(List<WebElement> element, String value, String neededValue,
                                              WebDriver driver) {
        element.parallelStream().forEach(element1 -> {
            if (element1.getText().equalsIgnoreCase(value)) {
                highlightElement(element1, driver);
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                jse.executeScript("arguments[0].click()", element1);

                ActionsHelper.actionsClick(element1, neededValue, driver);

            }
        });
    }


    public static boolean waitVisibility(WebElement element, int time, WebDriver driver) {
        boolean isElementPresent = false;
        try {
            wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.visibilityOf(element));
            isElementPresent = element.isDisplayed();
        } catch (Exception e) {
            throw e;
        }
        return isElementPresent;

    }

    public static boolean waitToBeClickable(WebElement element, int time, WebDriver driver) {
        boolean isElementClickable;
        try {
            wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isElementClickable = element.isEnabled();
        } catch (Exception e) {
            throw e;
        }
        return isElementClickable;

    }

    public static void selectByIndex(WebElement element, int index) {
        try {
            org.openqa.selenium.support.ui.Select make = new org.openqa.selenium.support.ui.Select(element);
            make.selectByIndex(index);
        } catch (Exception e) {
            throw new RuntimeException("Element is: " + element);

        }
    }

    public static void selectByValue(WebElement element, String value) {
        try {
            org.openqa.selenium.support.ui.Select make = new org.openqa.selenium.support.ui.Select(element);
            make.selectByValue(value);
        } catch (Exception e) {
            throw new RuntimeException("Element is: " + element);

        }
    }

    public static void scrollTo(WebElement element, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static void safeJavaScriptClick(WebElement element, WebDriver driver) throws Exception {
        try {
            if (element.isEnabled() && element.isDisplayed()) {

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            } else {
                LOGGER.info("Element not displayed Unable to click on element");
            }
        } catch (StaleElementReferenceException e) {
            LOGGER.info("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            LOGGER.info("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            LOGGER.info("Unable to click on element " + e.getStackTrace());
        }
    }

    public static String getImagePath(String imageName) {
        String path = System.getProperty("user.dir") + "/src/main/resources/images/" + imageName;
        return path;
    }

    public static String getDataProviderPath(String xmlFileName) {
        String path = System.getProperty("user.dir") + "/src/main/resources/DataProvider/" + xmlFileName;
        return path;
    }

    public static String getTodayDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }


    public static Calendar getTodayDateFromCalender() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal;
    }

    public static WebElement getElement(WebDriver driver, String attribute, String locator) {

        WebElement element = null;

        try {
            switch (attribute) {
                case "id":
                    element = driver.findElement(By.id(locator));
                case "name":
                    element = driver.findElement(By.id(locator));
                case "class":
                    element = driver.findElement(By.className(locator));
                case "xpath":
                    element = driver.findElement(By.xpath(locator));
            }
        } catch (Exception e) {
        }

        return element;
    }

    public static void actionsClick(WebElement element, String EnterText, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(EnterText, Keys.ENTER);
        actions.build().perform();

    }

    public static void click(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.build().perform();

    }

    public static String getFutureDate(int addedYears, int addedMonths, int addedDays) {
        DateFormat dateFormat;
        if (ReadWriteHelper.ReadData("browser").equalsIgnoreCase("chrome")) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        }


        Date currentDate = new Date();
        //System.out.println(dateFormat.format(currentDate));

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // manipulate date
        c.add(Calendar.YEAR, addedYears);
        c.add(Calendar.MONTH, addedMonths);
        c.add(Calendar.DATE, addedDays); //same with c.add(Calendar.DAY_OF_MONTH, 1);

        // convert calendar to date
        Date currentDatePlus = c.getTime();

        //System.out.println(dateFormat.format(currentDatePlus));

        return dateFormat.format(currentDatePlus);
    }


    public static void selectElementFromList(List<WebElement> element, String value, WebDriver driver) {
        element.parallelStream().forEach(element1 -> {

            if (element1.getText().equalsIgnoreCase(value)) {
                highlightElement(element1, driver);
                element1.click();
                LOGGER.info("inside try");
            }

        });
    }


    public static boolean waitForExistance(WebElement element, int seconds) {
        boolean isExist = false;

        int count = 1;
        while (count <= seconds) {
            try {
                Thread.sleep(1000);
                if (element.isDisplayed()) {
                    isExist = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception message: " + e.getMessage());
            }
            count++;
        }

        return isExist;
    }

    public static boolean waitForExistance(WebElement element) {
        boolean isExist = false;

        int count = 1;
        while (count <= waitTime) {
            try {
                Thread.sleep(1000);
                if (element.isDisplayed()) {
                    isExist = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception message: " + e.getMessage());
            }
            count++;
        }

        return isExist;
    }

    public static boolean waitForListExistance(List<WebElement> element, int seconds) {
        boolean isExist = false;
        int count = 1;
        while (count <= seconds) {
            try {
                Thread.sleep(1000);
                if (element.size() != 0 || element.get(count).isDisplayed() && element.get(count).isEnabled()) {
                    isExist = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception message: " + e.getMessage());
            }
            count++;
        }
        return isExist;
    }

    public static String reverseString(String value) {

        StringBuilder reverse = new StringBuilder();

        for (int i = value.length() - 1; i >= 0; i--) {
            reverse.append(value.charAt(i));
        }

        return reverse.toString();
    }

    public static WebElement getElementFromList(List<WebElement> element, String value) {
        WebElement elmnt = null;
        for (int i = 0; i < element.size(); i++) {
            if (element.get(i).getText().equalsIgnoreCase(value)) {
                elmnt = element.get(i);
                break;
            }
        }
        return elmnt;
    }

    public static void sendText(String locator, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + locator + "').value='" + value + "';");

    }

    public static void HandleKendoDateTimePicker(String day, String month, String year, WebDriver driver) throws InterruptedException {
        WebElement select = driver.findElement(By.cssSelector("table .picker-switch"));
        select.click();
        select = driver.findElement(By.cssSelector(".datepicker-months .picker-switch"));
        select.click();
        select = driver.findElement(By.xpath(String.format("//span[contains(.,'%s')]", year)));
        select.click();
        select = driver.findElement(By.xpath(String.format("//span[contains(.,'%s')]", month)));
        select.click();
        select = driver.findElement(By.xpath(String.format("//td[contains(.,'%s')]", day)));
        select.click();
        Thread.sleep(2000);
    }

    public static void navigateTo(String url, WebDriver driver) {
        driver.navigate().to(url);
    }

    public static String getFutureTime(int addedHours, int addedMins) {
        DateFormat dateFormat = new SimpleDateFormat("hh-mmaa");
        Date currentDate = new Date();
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.HOUR, addedHours);
        c.add(Calendar.MINUTE, addedMins);
        // convert calendar to date
        Date currentDatePlus = c.getTime();
        return dateFormat.format(currentDatePlus);
    }

    public static void retryClick(WebElement myelement, int maxSeconds) {
        int i = 0;
        boolean result = false;
        while (i <= maxSeconds) {
            try {
                myelement.click();
                result = true;
                break;
            } catch (Exception e) {
                result = false;
            }
            i++;
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }
        if (!result) {
            Assert.fail("Failed to click element: " + myelement.toString());
        }


    }

    public void getWebTableIndex(String tableId, WebDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        WebElement Webtable = driver.findElement(By.id(tableId)); // Replace TableID with Actual Table ID or Xpath

        List<WebElement> TotalRowCount = Webtable.findElements
                (By.xpath("//*[@id=" + tableId + "]/tbody/tr"));

        System.out.println("No. of Rows in the WebTable: " + TotalRowCount.size());
        // Now we will Iterate the Table and print the Values

        int RowIndex = 1;

        //int RowIndex=1;
        HashMap<String, Integer> webMap
                = new HashMap<>();

        for (WebElement rowElement : TotalRowCount) {
            List<WebElement> TotalColumnCount = rowElement.findElements(By.xpath("td"));
            int ColumnIndex = 1;

            for (WebElement colElement : TotalColumnCount) {
                System.out.println("Row " + RowIndex + " Column " + ColumnIndex + " Data " + colElement.getText());
                ColumnIndex = ColumnIndex + 1;
            }
            RowIndex = RowIndex + 1;
            webMap.put(TotalColumnCount.get(ColumnIndex).getText(), RowIndex);
        }
    }

    public static HashMap<Integer, String> getWebColumnIndex(String tableId, int columnIndex, WebDriver driver) throws InterruptedException {
        Thread.sleep(2000);
        WebElement Webtable = driver.findElement(By.id(tableId)); // Replace TableID with Actual Table ID or Xpath

        List<WebElement> TotalRowCount = Webtable.findElements
                (By.xpath("//*[@id='" + tableId + "']/tbody/tr"));

        System.out.println("No. of Rows in the WebTable: " + TotalRowCount.size());
        // Now we will Iterate the Table and print the Values

        int RowIndex = 1;
        HashMap<Integer, String> webMap
                = new HashMap<>();

        for (WebElement rowElement : TotalRowCount) {
            List<WebElement> TotalColumnCount = rowElement.findElements(By.xpath("td"));
            System.out.println("Row " + RowIndex + " Column " + columnIndex + " Data "
                    + TotalColumnCount.get(columnIndex).getText());

            RowIndex = RowIndex + 1;
            webMap.put(RowIndex, TotalColumnCount.get(columnIndex).getText());
            // System.out.println("test map  "+ webMap);
        }

        return webMap;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public static void highlightElement(WebElement element, WebDriver driver) {
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        }
    }


    public static void selectRadioBtnBefore(String selector, WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        //jsExecutor.executeScript("document.querySelector('"+ selector +"','::after').click();");
        jsExecutor.executeScript("document.getElementById(" + selector + ").checked = true;");
    }

    public static void clickAction(WebElement element) throws Exception {
        try {
            element.click();
        } catch (Exception ex) {
            throw ex;
        }
    }


    public static void sendKeys(WebElement wElement, String text, WebDriver driver) throws Exception {
        try {
            wElement.sendKeys(text);
        } catch (Exception ex) {
            //captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void sendKeysWithClear(WebElement wElement, String text, WebDriver driver) throws Exception {
        try {
            wElement.clear();
            wElement.sendKeys(text);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void clickWithJs(String locator, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('" + locator + "').click();");

    }


    public static void selectByVisibleText(WebElement welement, String text) throws Exception {
        try {
            Select select = new Select(welement);
            select.selectByVisibleText(text);
        } catch (Exception ex) {
            //captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }

    public static void scrollToBottom() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }


    public static void checkElement(WebElement checkBox) throws Exception {
        try {
            if (!checkBox.getAttribute("type").toLowerCase().equals("checkbox")) {
            }
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        } catch (Exception ex) {
            //captureScreenShot(driver, new Exception().getStackTrace()[0].getMethodName());
            throw ex;
        }
    }


    public static String generateRandomText() {
        String generatedString = RandomStringUtils.randomAlphabetic(5);

        return generatedString;
    }

    public static String generateRandomNumberAndText() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        int randomNumber = (int) (Math.random() * 900 + 1);
        return generatedString + randomNumber;
    }

    public static String getSaltString() {
        String SALTCHARS = " ا ب ت ث ج ح خ د ذ ر ز ف ق ك ل م ن ه و ي";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static void rowContent(int columnNumber, String divName, String expectedResults) {

        List<WebElement> columnContent = driver.findElements(By.xpath("//div[@class='" + divName + "']/table/tbody/tr/td[" + columnNumber + "]"));
        int columnSize = columnContent.size();
        System.out.println("columnSize = " + columnSize);


        for (int i = 0; i < columnSize; i++) {

            String System_Name_List = columnContent.get(i).getText();
            System.out.println("check Record " + System_Name_List);

            if (System_Name_List.equalsIgnoreCase(expectedResults)) {

                System.out.println(" inside if " + System_Name_List + " " + expectedResults);

                System.out.println("Action helper Expected = " + expectedResults);

                Assert.assertTrue(expectedResults.equals(System_Name_List));
                ActionsHelper.highlightElement(columnContent.get(i), driver);
                ActionsHelper.click(columnContent.get(i), driver);
                LOGGER.info(" record in table" + columnContent.get(i));
                break;


            }
            LOGGER.error("no match in expected results and table content ");
        }


    }


    public static boolean isFileDownloaded(String fileName, String needDelete) throws InterruptedException {

        File dir = new File("src/main/resources/DataProvider/");
        File[] dirContents = dir.listFiles();
        System.out.println("size " + dirContents.length);
        System.out.println(" before file is here");
        Thread.sleep(2000);
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().equals(fileName)) {
                System.out.println("First File " + dirContents[i].getName());
                System.out.println(" check file is here");
                LOGGER.info("Attachment exist on download file ");
                try {
                    Thread.sleep(1000);
                    if (needDelete.equalsIgnoreCase("true")) {
                        // File has been found, it can now be deleted:

                        dirContents[i].delete();
                        LOGGER.info("Attachment is deleted successfully  ");
                    }
                } catch (Exception e) {

                    LOGGER.error("can not delete the file ");
                }

                return true;
            }
        }
        return false;
    }

    public static int screenShotsNumber(String ExtentReportName) throws InterruptedException {
        int counter  = 0;
        File dir = new File("src/main/resources/Reports/");
        File[] dirContents = dir.listFiles();
        System.out.println("size in screen shot file " + dirContents.length);

        Thread.sleep(2000);
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().startsWith(ExtentReportName) && dirContents[i].getName().endsWith("PNG")) {
                System.out.println("First File in counter  " + dirContents[i].getName());
                System.out.println(" check file is here");
                LOGGER.info("Attachment exist on download file ");
                counter= i;
                System.out.println("Images counter  = "+counter);
                break;

            }
        }

        System.out.println("Images final counter = "+counter);
        return counter ;
    }

    public static String screenShots(String ExtentReportName) throws InterruptedException {
        String imageName = "";
        File dir = new File("src/main/resources/Reports/");
        File[] dirContents = dir.listFiles();
        System.out.println("size " + dirContents.length);
        System.out.println(" before file is here");
        Thread.sleep(2000);
        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().startsWith(ExtentReportName) && dirContents[i].getName().endsWith("PNG")) {
                System.out.println("First File " + dirContents[i].getName());
                System.out.println(" check file is here");
                LOGGER.info("Attachment exist on download file ");
                imageName = dirContents[i].getName();
                System.out.println("Image = "+imageName);

            }
        }
        return imageName;
    }

    public static void tableSize(int columnNumber, String divName, int rowSize) throws Exception {

        List<WebElement> columnContent = driver.findElements(By.xpath("//div[@class='" + divName + "']/table/tbody/tr/td[" + columnNumber + "]"));
        isPresented(columnContent.get(columnNumber));
        int columnSize = columnContent.size();
        Assert.assertTrue(columnSize == rowSize);

    }

    public static void loader() throws Exception {
        Boolean isPresent = driver.findElements(By.id("loader")).size() > 0;
        int size = driver.findElements(By.id("loader")).size();

        LOGGER.info("check if load element not presented " + isPresent.toString());
        LOGGER.info("loader size  " + size);

        while (size != 0) {
            Thread.sleep(10000);
            size -= 1;
        }
/*
        else {
            LOGGER.info("is present inside if " + isPresent.toString());
            waitForExistance(driver.findElement(By.id("\"" + tableName + "\" ")));
            WebElement columnContent = driver.findElement(By.xpath("//*[@id= \"" + tableName + "\"]/tbody/tr"));

            waitVisibility(columnContent,Data.waitTime,driver);
            waitVisibility(columnContent,Data.waitTime,driver);
           ActionsHelper.waitForExistance(columnContent, Data.waitTime);
        }*/
    }


    public static void isPresented(WebElement element) throws Exception {
        Boolean isPresent = driver.findElements(By.id("loader")).size() > 0;
        LOGGER.info("check if load element not presented " + isPresent.toString());
        if (isPresent) {//was is present = false

            LOGGER.info("is present inside if " + isPresent.toString());
            waitVisibility(element, Data.waitTime, driver);
            ActionsHelper.waitForExistance(element, Data.waitTime);
        }

    }

    public static String returnVal() {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ((JavascriptExecutor) driver).executeScript("$(\"#Emirateid\").val()");

        // i need to fix it
        String someValue = (String) js.executeScript("return $(\"#Emirateid\").val();");
        return someValue;
    }

    public static String returnValueGeneric(String value) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // ((JavascriptExecutor) driver).executeScript("$(\"#Emirateid\").val()");

        // i need to fix it
        String someValue = (String) js.executeScript("return $(\"#" + value + "\").val();");
        return someValue;
    }

    public static boolean retryingFindClick(WebElement element) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                element.click();
                result = true;
                break;
            } catch (Exception e) {
            }
            attempts++;
        }
        return result;
    }


    public static void tableClick(int columnNumber, String divName, String expectedResults) {

        List<WebElement> columnContent = driver.findElements(By.xpath("//div[@class='" + divName + "']/table/tbody/tr/td[" + columnNumber + "]"));
        int columnSize = columnContent.size();
        System.out.println("columnSize = " + columnSize);

        for (int i = 0; i < columnSize; i++) {

            String System_Name_List = columnContent.get(i).getText();
            System.out.println("check Record " + System_Name_List);

            if (System_Name_List.equalsIgnoreCase(expectedResults)) {

                System.out.println(" inside if " + System_Name_List + " " + expectedResults);

                System.out.println("Action helper Expected = " + expectedResults);

                ActionsHelper.highlightElement(columnContent.get(i), driver);
                //  ActionsHelper.click(columnContent.get(i), driver);
                ActionsHelper.click(columnContent.get(i), driver);

                break;


            }

        }
    }


    public static String rowContentSpan(int columnNumber, String divName, String expectedResults) throws InterruptedException {

        List<WebElement> columnContent = driver.findElements(By.xpath("//div[@class='" + divName + "']/table/tbody/tr/td[" + columnNumber + "]/span"));
        int columnSize = columnContent.size();
        WebElement loader = driver.findElement(By.id("loader"));
        System.out.println("columnSize = " + columnSize);

        for (int i = 0; i < columnSize; i++) {

            String System_Name_List = columnContent.get(i).getText();
            System.out.println("check Record " + System_Name_List);

            if (System_Name_List.equalsIgnoreCase(expectedResults)) {

                System.out.println(" inside if " + System_Name_List + " " + expectedResults);

                System.out.println("Action helper Expected = " + expectedResults);
                Assert.assertTrue(expectedResults.equals(System_Name_List));
                ActionsHelper.highlightElement(columnContent.get(i), driver);
                ActionsHelper.click(columnContent.get(i), driver);

                break;


            }

        }
        Thread.sleep(2000);
        System.out.println("columnNumber = " + columnNumber);
        ActionsHelper.waitInvisibility(loader, Data.waitTime);
        System.out.println(" content " + columnContent.get(0).getText());
        return columnContent.get(0).getText();
    }

    public static boolean waitInvisibility(WebElement element, int time) {
        boolean isElementPresent = false;
        try {
            wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.invisibilityOf(element));
            isElementPresent = element.isDisplayed();
        } catch (Exception e) {
            throw e;
        }
        return isElementPresent;

    }




    public static int tableSize1(int columnNumber, String divName) throws Exception {

        List<WebElement> columnContent = driver.findElements(By.xpath("//div[@class='" + divName + "']/table/tbody/tr/td[" + columnNumber + "]"));
        isPresented(columnContent.get(columnNumber));
        int columnSize = columnContent.size();

        return columnSize;
    }


    public static NgWebDriver getNGDriver() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (new NgWebDriver(js));

    }

    public static void waitForNGWeBDriverToFinish() {

        try {

            getNGDriver().waitForAngularRequestsToFinish();
            LOGGER.info("NG web driver wait ");
        } catch (Exception e) {

            Assert.fail("Failed to wait for NgWebDriver" + e.getMessage());
        }
    }


    public static void selectMenuElement(String menuName) throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> menuItem = driver.findElements(By.xpath
                ("//span[@class='hide-menu' and contains(text(), '" + menuName + "')]"));
        // Thread.sleep(1000);
        ActionsHelper.clickActionNew(menuItem.get(0), driver);
        LOGGER.info("select menu = " + menuName);
    }

    public static void clickActionNew(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click().perform();


    }

    public static String getRandNumberWithSize(int size) {
        String SALTCHARS = "01235467";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < size) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


    public static String generateRandomNumber() {
        String generatedString = RandomStringUtils.randomNumeric(8);

        return generatedString;
    }

    public static void tableIdAndClick(int columnNumber, String tableId, String expectedResults, String xpath) {
// use this method for table and to select the esis number
        List<WebElement> columnContent = driver.findElements(
                By.xpath("//*[@id=\"" + tableId + "\"]/tbody/tr/td[" + columnNumber + "]/" + xpath + ""));

        int columnSize = columnContent.size();
        System.out.println("columnSize = " + columnSize);

        for (int i = 0; i < columnSize; i++) {

            String System_Name_List = columnContent.get(i).getText();
            System.out.println("check Record " + System_Name_List);

            if (System_Name_List.equalsIgnoreCase(expectedResults)) {

                System.out.println(" inside if " + System_Name_List + " " + expectedResults);

                System.out.println("Action helper Expected = " + expectedResults);

                ActionsHelper.highlightElement(columnContent.get(i), driver);
                //  ActionsHelper.click(columnContent.get(i), driver);
                ActionsHelper.click(columnContent.get(i), driver);

                break;


            }

        }
    }

    public static void selectMenuElementSafe(String menuName) throws Exception {

        List<WebElement> menuItem = driver.findElements(By.xpath
                ("//span[@class='hide-menu' and contains(text(), '" + menuName + "')]"));
        ActionsHelper.clickActionNew(menuItem.get(1), driver);
        LOGGER.info("select menu = " + menuName);
    }

    public static void javaScriptClick(WebElement element, WebDriver driver) throws Exception {

        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

            LOGGER.info("click on element");

        } catch (StaleElementReferenceException e) {
            LOGGER.info("Element is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            LOGGER.info("Element was not found in DOM " + e.getStackTrace());
        } catch (Exception e) {
            LOGGER.info("Unable to click on element " + e.getStackTrace());
        }
    }


    public static String dateFormatter(String date) throws ParseException {


        System.out.println(ActionsHelper.getFutureDate(0, 0, 2));
        final String OLD_FORMAT = "dd/MM/yyyy";
        final String NEW_FORMAT = "dd-MMM-yyyy";

// August 12, 2010
        String oldDateString = date;
        String newDateString;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        System.out.println(newDateString);
        return newDateString;
    }

    public static void rowContent_A_U(int columnNumber, String tableid, String expectedResults) {

        List<WebElement> columnContent = driver.findElements(By.xpath(
                "//*[@id=\"" + tableid + "\"]/tbody/tr/td[" + columnNumber + "]/a/u"));
        int columnSize = columnContent.size();
        System.out.println("columnSize = " + columnSize);


        for (int i = 0; i <= columnSize; i++) {
            System.out.println("i = " + i);
            String System_Name_List = columnContent.get(i).getText();
            System.out.println("check Record " + System_Name_List);

            if (System_Name_List.equalsIgnoreCase(expectedResults)) {

                System.out.println(" inside if " + System_Name_List + " " + expectedResults);

                System.out.println("Action helper Expected = " + expectedResults);

                Assert.assertTrue(expectedResults.equals(System_Name_List));
                ActionsHelper.highlightElement(columnContent.get(i), driver);
                ActionsHelper.click(columnContent.get(i), driver);
                LOGGER.info(" record in table" + columnContent.get(i));
                break;


            }
            LOGGER.error("no match in expected results and table content ");
        }


    }

    public static void rowContent_A_U(int columnNumber, String tableID) {

        List<WebElement> columnContent = driver.findElements(By.xpath(
                "//*[@id=\"" + tableID + "\"]/tbody/tr/td[" + columnNumber + "]/a/u"));
        int columnSize = columnContent.size();
        System.out.println("columnSize = " + columnSize);


        ActionsHelper.highlightElement(columnContent.get(0), driver);
        ActionsHelper.highlightElement(columnContent.get(0), driver);
        columnContent.get(0).click();
        LOGGER.info(" record in table" + columnContent.get(0));

        LOGGER.error("no match in expected results and table content ");
        ReadWriteHelper.writeParentPage(columnContent.get(0).getText());

    }

    public static void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }


    public  static void ddlContent(String name){
        WebElement ddlContent = driver.findElement(By.xpath
                ("//span[starts-with (@class,'ms-Dropdown-optionText') and contains (text(), '" +
                        name + "')]"));
        ddlContent.click();

    }


    public static String projectName(){

        String proj=  System.getProperty("user.dir");

        //System.out.println(proj.lastIndexOf("/")+1).trim);

    return  proj.substring(proj.lastIndexOf("/") + 1).trim()
            ;}

     public static void main(String[] args) {
         System.out.println( projectName());
    }
}