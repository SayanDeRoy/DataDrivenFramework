package com.infy.practice.pageobjects;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//button[text()='Home']")
    private WebElement home;

    @FindBy(xpath = "//button[text()='Customer Login']")
    private WebElement customerLogin;

    @FindBy(how = How.XPATH, using = "//button[text()='Bank Manager Login']")
    private WebElement bankMangerLogin;

    public void clickOnHomeButton() {
        home.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Home Tab");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Home Tab");
    }

    public void clickOnCustomerLoginButton() {
        customerLogin.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Customer Login Tab");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Customer Login Tab");
    }

    public void clickOnBankManagerLoginButton() {
        bankMangerLogin.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Bank Manager Login Tab");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Bank Manager Login Tab");
    }
}
