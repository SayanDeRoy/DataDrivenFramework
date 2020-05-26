package com.infy.practice.pageobjects;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BankManager {

    WebDriver driver;

    public BankManager(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Add Customer')]")
    private WebElement addCustomer;

    @FindBy(xpath = "//button[contains(text(),'Open Account')]")
    private WebElement openAccount;

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Customers')]")
    private WebElement customers;

    public void clickOnAddCustomer() {
        addCustomer.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Add Customer Tab");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Add Customer Tab");
    }

    public void clickOnOpenAccount() {
        openAccount.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Open Account Tab");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Open Account Tab");
    }

    public void clickOnCustomers() {
        customers.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Customers Tab");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Customers Tab");
    }
}
