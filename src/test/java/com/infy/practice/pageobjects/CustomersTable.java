package com.infy.practice.pageobjects;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CustomersTable {

    WebDriver driver;

    public CustomersTable(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//input[@placeholder='Search Customer']")
    private WebElement searchCustomer;

    @FindBy(how = How.XPATH, using = "//tr[@class='ng-scope']/td[1]")
    private List<WebElement> firstNameValues;

    @FindBy(how = How.XPATH, using = "//tr[@class='ng-scope']/td[2]")
    private List<WebElement> lastNameValues;

    @FindBy(how = How.XPATH, using = "//tr[@class='ng-scope']/td[3]")
    private List<WebElement> postCodeValues;

    @FindBy(how = How.XPATH, using = "//tr[@class='ng-scope']/td[4]")
    private List<WebElement> accountNumbers;

    @FindBy(how = How.XPATH, using = "//tr[@class='ng-scope']/td[5]/button")
    private List<WebElement> deleteButtons;

    public void verifyNewCustomerPresentInsideCustomerTable(String fName, String lName, String pCode) {
        for (int i = 0; i < firstNameValues.size(); i++) {
            if (firstNameValues.get(i).getText().equals(fName)) {
                TestBase.scrollToElement(firstNameValues.get(i));
                Assert.assertEquals(firstNameValues.get(i).getText(), fName);
                Assert.assertEquals(lastNameValues.get(i).getText(), lName);
                Assert.assertEquals(postCodeValues.get(i).getText(), pCode);

                //Log4j entry
                TestBase.log.debug("Successfully verified Newly created customer in Customers table");

                //Extent Report entry
                TestBase.extentTest.log(LogStatus.INFO, "Successfully verified Newly created customer in Customers table");

                break;
            }
        }
    }

    public void verifyNewAccountNumberPresentInsideCustomerTable(String fName, String expectedAccNumber) {
        for (int i = 0; i < firstNameValues.size(); i++) {
            if (firstNameValues.get(i).getText().equals(fName)) {
                TestBase.scrollToElement(firstNameValues.get(i));
                String actualAccNumber = accountNumbers.get(i).findElement(By.tagName("span")).getText().trim();
                Assert.assertEquals(actualAccNumber, expectedAccNumber);

                //Log4j entry
                TestBase.log.debug("Successfully verified Newly created Account in Customers table");

                //Extent Report entry
                TestBase.extentTest.log(LogStatus.INFO, "Successfully verified Newly created Account in Customers table");

                break;
            }
        }
    }

    public void deleteCustomerAndVerify(String fName) {
        int count = 0;
        for (int i = 0; i < firstNameValues.size(); i++) {
            if (firstNameValues.get(i).getText().equals(fName)) {
                deleteButtons.get(i).click();
                i = -1;
                count++;
                if (count > 1) {
                    Assert.fail(fName + " is still Present After Delete");
                }
            }
        }
        if (count == 0) {
            Assert.fail(fName + " is not available in Customer Table to perform Delete");
        }

        //Log4j entry
        TestBase.log.debug("Successfully Deleted Newly created customer in Customers table");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Successfully Deleted Newly created customer in Customers table");
    }
}
