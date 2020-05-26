package com.infy.practice.pageobjects;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CustomerLogin {

    WebDriver driver;
    Select sel;

    public CustomerLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//select[@id='userSelect']")
    private WebElement loginDropDown;

    @FindBy(how = How.XPATH, using = "//button[text()='Login']")
    private WebElement login;

    public void verifyLoginButtonIsNotVisible() {
        Assert.assertEquals(login.isDisplayed(), false);

        //Log4j entry
        TestBase.log.debug("Verified Login button is not visible before selecting the customer");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Verified Login button is not visible before selecting the customer");
    }

    public void selectUserForLogin(String userName) {
        sel = new Select(loginDropDown);
        sel.selectByVisibleText(userName);

        //Log4j entry
        TestBase.log.debug("Customer Selected for Login: " + userName);

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Customer Selected for Login: " + userName);
    }

    public void clickOnLoginButton() {
        login.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Customer Login button");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Customer Login button");
    }
}
