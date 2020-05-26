package com.infy.practice.listeners;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestListener;
import org.testng.SkipException;

public class CustomListeners extends TestBase implements ITestListener {

    public void onTestStart(org.testng.ITestResult result) {
        String sheetName = result.getTestClass().toString();
        sheetName = sheetName.replace("[TestClass name=class com.infy.practice.testcases.", "").replace("]", "");
        String testCaseName = result.getName();

        //Extent Reports Start
        extentTest = extentReport.startTest(testCaseName.toUpperCase());

        getDataFromExcel(sheetName, testCaseName);
        if (data.get("RunMode").equalsIgnoreCase("N")) {
            throw new SkipException(testCaseName + " is set as 'N' for Run Mode");
        }

        // Adding in as Log4j
        log.debug("-----------------Test Case: " + result.getName().toUpperCase() + " EXECUTION STARTED-----------------");
    }

    public void onTestSuccess(org.testng.ITestResult result) {

        String sheetName = result.getTestClass().toString();
        sheetName = sheetName.replace("[TestClass name=class com.infy.practice.testcases.", "").replace("]", "");
        String testCaseName = result.getName();
        updateDataIntoExcel(sheetName, testCaseName, "Status", "Pass");

        // Adding in as Log4j
        log.debug("----------------Test Case: " + testCaseName.toUpperCase() + " EXECUTION COMPLETED----------------");

        //Extent Reports
        extentTest.log(LogStatus.PASS, testCaseName.toUpperCase() + " PASSED");
        extentReport.endTest(extentTest);
        extentReport.flush();
    }

    public void onTestFailure(org.testng.ITestResult result) {

        String sheetName = result.getTestClass().toString();
        sheetName = sheetName.replace("[TestClass name=class com.infy.practice.testcases.", "").replace("]", "");
        String testCaseName = result.getName();
        updateDataIntoExcel(sheetName, testCaseName, "Status", "Fail");
        String screenShotPath = takeScreenShot(testCaseName);
        // Adding in as Log4j
        log.debug("-----------------Test Case: " + testCaseName.toUpperCase() + " EXECUTION FAILED-----------------");

        //Extent Reports

        extentTest.log(LogStatus.FAIL, testCaseName.toUpperCase() + " FAILED with Exception: " + result.getThrowable());
        extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenShotPath));
        extentReport.endTest(extentTest);
        extentReport.flush();

    }

    public void onTestSkipped(org.testng.ITestResult result) {
        String sheetName = result.getTestClass().toString();
        sheetName = sheetName.replace("[TestClass name=class com.infy.practice.testcases.", "").replace("]", "");
        String testCaseName = result.getName();
        updateDataIntoExcel(sheetName, testCaseName, "Status", "Skipp");

        // Adding in as Log4j
        log.debug("-----------------Test Case: " + testCaseName.toUpperCase() + " EXECUTION SKIPPED-----------------");

        //Extent Reports
        extentTest.log(LogStatus.SKIP, testCaseName.toUpperCase() + " Skipped with Exception: " + result.getThrowable());
        extentReport.endTest(extentTest);
        extentReport.flush();
    }

    public void onTestFailedButWithinSuccessPercentage(org.testng.ITestResult result) { /* compiled code */ }

    public void onTestFailedWithTimeout(org.testng.ITestResult result) { /* compiled code */ }

    public void onStart(org.testng.ITestContext context) { /* compiled code */ }

    public void onFinish(org.testng.ITestContext context) { /* compiled code */ }

}
