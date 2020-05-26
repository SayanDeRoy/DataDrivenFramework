package com.infy.practice.pageobjects;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class OpenAccount {

    WebDriver driver;
    Select sel;

    public OpenAccount(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "userSelect")
    private WebElement customerDropDown;

    @FindBy(name = "currency")
    private WebElement currencyDropDown;

    @FindBy(how = How.XPATH, using = "//button[text()='Process']")
    private WebElement process;

    private String expectedAlertMessage = "Account created successfully with account Number";

    public void selectCustomerByTypingAndVerify(String fName, String lName) {
        String expectedDisplayedFullName = fName + " " + lName;
        customerDropDown.sendKeys(expectedDisplayedFullName);
        int value = Integer.parseInt(TestBase.getValueUsingJavaScript(customerDropDown));
        sel = new Select(customerDropDown);
        List<WebElement> allOptions = sel.getOptions();
        String actualDisplayedFullName;
        try {
            actualDisplayedFullName = allOptions.get(value).getText();
        } catch (IndexOutOfBoundsException e) {
            actualDisplayedFullName = allOptions.get(value - 1).getText();
        }
        Assert.assertEquals(actualDisplayedFullName, expectedDisplayedFullName);

        //Log4j entry
        TestBase.log.debug(expectedDisplayedFullName + " selected as a Customer for Account Opening");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, expectedDisplayedFullName + " selected as a Customer for Account Opening");
    }

    public void selectCurrency(String currencyType) {
        sel = new Select(currencyDropDown);
        sel.selectByVisibleText(currencyType);

        //Log4j entry
        TestBase.log.debug(currencyType + " selected as a Currency for Account Opening");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, currencyType + " selected as a Currency for Account Opening");
    }

    public void clickOnProcessButton() {
        process.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Process button");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Process button");
    }

    public String verifyAccountOpenedSuccessfullyAndGetAccountNumber() {
        Alert alert = driver.switchTo().alert();
        String[] messagePart = alert.getText().split(":");
        Assert.assertEquals(messagePart[0].trim(), expectedAlertMessage);
        alert.accept();

        //Log4j entry
        TestBase.log.debug("Clicked on 'OK' button on alert window");
        TestBase.log.debug("Account Opened successfully with AccID is: " + messagePart[1]);

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on 'OK' button on alert window");
        TestBase.extentTest.log(LogStatus.INFO, "Account Opened successfully with AccID is: " + messagePart[1]);

        return messagePart[1];
    }
}
