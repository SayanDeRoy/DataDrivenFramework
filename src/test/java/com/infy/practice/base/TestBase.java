package com.infy.practice.base;

import com.infy.practice.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /*
     * WebDriver
     * Properties
     * Logs
     * ExtentReports
     * DB
     * Excel
     * Mail
     * Jenkins
     */

    public static WebDriver driver;
    public static List<String> columnValue = new ArrayList<>();
    Properties config = new Properties();
    FileInputStream fis;
    FileOutputStream fos;
    XSSFWorkbook wb;
    XSSFSheet sheet;
    public static HashMap<String, String> data = new HashMap<>();
    public static Logger log = Logger.getLogger("ApplicationLogger");
    public static ExtentReports extentReport = ExtentManager.getExtentReportInstance();
    public static ExtentTest extentTest;

    @BeforeSuite
    public void setUp() throws IOException {
        if (driver == null) {
            fis = new FileInputStream("src/test/resources/properties/config.properties");
            config.load(fis);
            log.debug("Config Properties file loaded");
            if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/executables/chromedriver.exe");
                ChromeOptions option = new ChromeOptions();
                //option.addArguments("headless");
                driver = new ChromeDriver(option);
                log.debug("Chrome Browser Opened");
            }
            driver.manage().window().maximize();
            driver.get(config.getProperty("applicationURL"));
            log.debug("Application Launched: " + config.getProperty("applicationURL"));
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitWait")), TimeUnit.SECONDS);
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        log.debug("All Browser Closed");
    }

    public void getDataFromExcel(String sheetName, String tcName) {
        try {
            fis = new FileInputStream("src/test/resources/excel/TestData.xlsx");
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheet(sheetName);
        int totalRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        for (int i = 1; i <= totalRowCount; i++) {
            String testCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
            if (testCaseName.equals(tcName)) {
                int totalColumnCount = sheet.getRow(i).getLastCellNum() - sheet.getRow(i).getFirstCellNum();
                for (int j = 1; j < totalColumnCount; j++) {
                    try {
                        String value = sheet.getRow(i).getCell(j).getStringCellValue();
                        if (!value.equals("")) {
                            String key = sheet.getRow(0).getCell(j).getStringCellValue();
                            data.put(key, value);
                        }
                    } catch (NullPointerException e) {
                    }
                }
                break;
            }
        }
        try {
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDataIntoExcel(String sheetName, String tcName, String colName, String value) {

        try {
            fis = new FileInputStream("src/test/resources/excel/TestData.xlsx");
            wb = new XSSFWorkbook(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheet(sheetName);
        int totalRowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 1; i <= totalRowCount; i++) {
            String testCaseName = sheet.getRow(i).getCell(0).getStringCellValue();
            if (testCaseName.equals(tcName)) {
                int totalColumnCount = sheet.getRow(0).getLastCellNum() - sheet.getRow(0).getFirstCellNum();
                for (int j = 1; j < totalColumnCount; j++) {
                    String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
                    if (columnName.equals(colName)) {
                        sheet.getRow(i).createCell(j, CellType.STRING).setCellValue(value);
                        break;
                    }
                }
                break;
            }
        }
        try {
            fos = new FileOutputStream("src/test/resources/excel/TestData.xlsx");
            wb.write(fos);
            wb.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValueUsingJavaScript(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return arguments[0].value;", ele).toString();
    }

    public static void scrollToElement(WebElement ele) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    public String takeScreenShot(String fileName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir")+"\\target\\surefire-reports\\htmlReports\\" + fileName + ".jpg";
        //String destFile = "./target/surefire-reports/htmlReports/"+fileName+".png";

        File dest = new File(destFile);

        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destFile;
    }

    public static void executeQueryAndGetStringValue(String query, String columnName) throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection
                ("jdbc:oracle:thin:@localhost:1522:sayan","SYSTEM","Punepdc3$");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next())
        {
            columnValue.add(rs.getString(columnName));
        }
    }
}
