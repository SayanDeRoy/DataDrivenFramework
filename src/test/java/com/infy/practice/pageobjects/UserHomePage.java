package com.infy.practice.pageobjects;

import com.infy.practice.base.TestBase;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class UserHomePage {

    WebDriver driver;

    public UserHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "accountSelect")
    private WebElement accountDropDown;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Account Number')]//strong[1]")
    private WebElement accountNumber;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Account Number')]//strong[2]")
    private WebElement balance;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Account Number')]//strong[3]")
    private WebElement currency;

    @FindBy(how = How.XPATH, using = "//button[@ng-click='transactions()']")
    private WebElement transaction;

    @FindBy(how = How.XPATH, using = "//button[@ng-click='deposit()']")
    private WebElement deposit;

    @FindBy(how = How.XPATH, using = "//button[@ng-click='withdrawl()']")
    private WebElement withdrawn;

    @FindBy(how = How.XPATH, using = "//label[text()='Amount to be Deposited :']/following-sibling::input")
    private WebElement depositAmountField;

    @FindBy(how = How.XPATH, using = "//label[text()='Amount to be Withdrawn :']/following-sibling::input")
    private WebElement withdrawnAmountField;

    @FindBy(how = How.XPATH, using = "//button[text()='Deposit']")
    private WebElement depositButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Deposit Successful']")
    private WebElement depositSuccessMessage;

    @FindBy(how = How.XPATH, using = "//button[text()='Withdraw']")
    private WebElement withdrawButton;

    @FindBy(how = How.XPATH, using = "//span[text()='Transaction successful']")
    private WebElement withdrawSuccessMessage;

    @FindBy(how = How.XPATH, using = "//span[text()='Transaction Failed. You can " +
            "not withdraw amount more than the balance.']")
    private WebElement withdrawFailureMessage;

    public void verifyAccountNumberCurrencyAndBalance(String accountNumber, String currency, String balance) {
        Assert.assertEquals(this.accountNumber.getText().trim(), accountNumber);
        Assert.assertEquals(this.currency.getText(), currency);
        Assert.assertEquals(this.balance.getText(), balance);

        //Log4j entry
        TestBase.log.debug("Successfully verified Account Number, Currency and Balance");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Successfully verified Account Number, Currency and Balance");
    }

    public void enterDepositAmountAndClickOnDepositButton(String amount) {
        deposit.click();
        depositAmountField.sendKeys(amount);
        depositButton.click();
        Assert.assertEquals(depositSuccessMessage.isDisplayed(), true);

        //Log4j entry
        TestBase.log.debug("Successfully deposited amount: " + amount);

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Successfully deposited amount: " + amount);
    }

    public void enterWithdrawAmountAndClickOnWithdrawButton(String amount) {
        withdrawn.click();
        withdrawnAmountField.sendKeys(amount);
        withdrawButton.click();
        try {
            Assert.assertEquals(withdrawSuccessMessage.isDisplayed(), true);

            //Log4j entry
            TestBase.log.debug("Successfully withdrawn amount: " + amount);

            //Extent Report entry
            TestBase.extentTest.log(LogStatus.INFO, "Successfully withdrawn amount: " + amount);

        } catch (NoSuchElementException e) {
            Assert.assertEquals(withdrawFailureMessage.isDisplayed(), true);

            //Log4j entry
            TestBase.log.debug(amount+" is greater than balance hence withdraw failed");

            //Extent Report entry
            TestBase.extentTest.log(LogStatus.INFO, amount+" is greater than balance hence withdraw failed");
        }
    }

    public void verifyAmountAfterDeposit(String balanceAmount) {
        Assert.assertEquals(balance.getText(), balanceAmount);

        //Log4j entry
        TestBase.log.debug("Successfully verify the balance after deposit : " + balanceAmount);

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Successfully verify the balance after deposit : " + balanceAmount);
    }

    public void verifyAmountAfterWithdraw(String balanceAmount) {
        Assert.assertEquals(balance.getText(), balanceAmount);

        //Log4j entry
        TestBase.log.debug("Successfully verify the balance after withdraw : " + balanceAmount);

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Successfully verify the balance after withdraw : " + balanceAmount);
    }

    public void verifyAmountAfterWithdrawFailure(String balanceAmount) {
        Assert.assertEquals(balance.getText(), balanceAmount);

        //Log4j entry
        TestBase.log.debug("Withdraw Failed because Withdraw amount is greater than available balance");

        //Extent Report entry
        TestBase.extentTest.log(LogStatus.INFO, "Withdraw Failed because Withdraw amount is greater than available balance");
    }
}
