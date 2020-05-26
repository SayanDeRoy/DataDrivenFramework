package com.infy.practice.testcases;

import com.infy.practice.base.TestBase;
import com.infy.practice.pageobjects.CustomerLogin;
import com.infy.practice.pageobjects.HomePage;
import com.infy.practice.pageobjects.UserHomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class DepositWithdrawFunctionality extends TestBase {

    HomePage hp;
    CustomerLogin cl;
    UserHomePage uhp;

    @BeforeClass
    public void PageObjectsInitialization()
    {
        hp = new HomePage(driver);
        cl = new CustomerLogin(driver);
        uhp = new UserHomePage(driver);
    }

    @Test
    public void Verify_Initial_Account_Balance() {
        hp.clickOnHomeButton();
        hp.clickOnCustomerLoginButton();
        cl.verifyLoginButtonIsNotVisible();
        cl.selectUserForLogin(data.get("LogInName"));
        cl.clickOnLoginButton();
        uhp.verifyAccountNumberCurrencyAndBalance(data.get("AccountNumber"), data.get("CurrencyType"), data.get("Balance"));
    }

    @Test(dependsOnMethods = {"Verify_Initial_Account_Balance"})
    public void Deposit_Amount_And_Verify() throws IOException {
        uhp.enterDepositAmountAndClickOnDepositButton(data.get("Deposit"));
        uhp.verifyAmountAfterDeposit(data.get("Balance"));
    }

    @Test(dependsOnMethods = "Deposit_Amount_And_Verify")
    public void Withdraw_Amount_And_Verify() {
        uhp.enterWithdrawAmountAndClickOnWithdrawButton(data.get("Withdraw"));
        uhp.verifyAmountAfterWithdraw(data.get("Balance"));
    }

    @Test(dependsOnMethods = "Withdraw_Amount_And_Verify")
    public void Withdraw_Amount_More_Than_Balance() throws IOException {
        uhp.enterWithdrawAmountAndClickOnWithdrawButton(data.get("Withdraw"));
        uhp.verifyAmountAfterWithdrawFailure(data.get("Balance"));
    }
}
