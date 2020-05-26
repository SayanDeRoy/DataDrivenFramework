package com.infy.practice.pageobjects;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddCustomer {

    WebDriver driver;

    public AddCustomer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//label[contains(text(),'First Name')]/following-sibling::input")
    private WebElement firstName;

    @FindBy(xpath = "//label[contains(text(),'Last Name')]/following-sibling::input")
    private WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    private WebElement postCode;

    @FindBy(how = How.XPATH, using = "//button[text()='Add Customer']")
    private WebElement addTheCustomer;

    private String expectedAlertMessage = "Customer added successfully with customer id";

    private void enterFirstNameAndVerify(String firstNameValue) {
        firstName.sendKeys(firstNameValue);
        String enteredFirstName = TestBase.getValueUsingJavaScript(firstName);
        Assert.assertEquals(enteredFirstName, firstNameValue);

        //Log4j entry
        TestBase.log.debug(firstNameValue + " as First Name Entered and Verified");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, firstNameValue + " as First Name Entered and Verified");
    }

    private void enterLastNameAndVerify(String lastNameValue) {
        lastName.sendKeys(lastNameValue);
        String enteredLastName = TestBase.getValueUsingJavaScript(lastName);
        Assert.assertEquals(enteredLastName, lastNameValue);

        //Log4j entry
        TestBase.log.debug(lastNameValue + " as Last Name Entered and Verified");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, lastNameValue + " as Last Name Entered and Verified");
    }

    private void enterPostCodeAndVerify(String PostCodeValue) {
        postCode.sendKeys(PostCodeValue);
        String enteredPostCode = TestBase.getValueUsingJavaScript(postCode);
        Assert.assertEquals(enteredPostCode, PostCodeValue);

        //Log4j entry
        TestBase.log.debug(PostCodeValue + " as Post Code Entered and Verified");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, PostCodeValue + " as Post Code Entered and Verified");
    }

    private void clickOnAddCustomerAfterEnteringAllValues() {
        addTheCustomer.click();

        //Log4j entry
        TestBase.log.debug("Clicked on Add Customer button");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on Add Customer button");
    }

    public void createANewCustomer(String fname, String lName, String pCode) {
        enterFirstNameAndVerify(fname);
        enterLastNameAndVerify(lName);
        enterPostCodeAndVerify(pCode);
        clickOnAddCustomerAfterEnteringAllValues();
    }

    public String verifyCustomerCreatedSuccessfullyAndGetCustomerID() {
        Alert alert = driver.switchTo().alert();
        String[] messagePart = alert.getText().split(":");
        Assert.assertEquals(messagePart[0].trim(), expectedAlertMessage);
        alert.accept();

        //Log4j entry
        TestBase.log.debug("Clicked on 'OK' button on alert window");
        TestBase.log.debug("Customer Created successfully with CustID is: " + messagePart[1]);

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on 'OK' button on alert window");
        TestBase.extentTest.log(LogStatus.INFO, "Customer Created successfully with CustID is: " + messagePart[1]);

        return messagePart[1];
    }

    public void clickOkOnAlertWindow() {
        driver.switchTo().alert().accept();

        //Log4j entry
        TestBase.log.debug("Clicked on 'OK' button on alert window");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Clicked on 'OK' button on alert window");
    }
}
