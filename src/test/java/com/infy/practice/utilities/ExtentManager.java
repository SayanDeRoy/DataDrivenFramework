package com.infy.practice.utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReportInstance()
    {
        if(extent == null)
        {
            extent = new ExtentReports("target/surefire-reports/htmlReports/extentReports.html",true, DisplayOrder.OLDEST_FIRST);
            extent.loadConfig(new File("src/test/resources/extentconfig/ReportsConfig.xml"));
        }
        return extent;
    }
}
