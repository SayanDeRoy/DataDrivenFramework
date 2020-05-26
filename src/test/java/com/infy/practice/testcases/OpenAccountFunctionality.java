package com.infy.practice.testcases;

import com.infy.practice.base.TestBase;
import com.infy.practice.pageobjects.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OpenAccountFunctionality extends TestBase {

    HomePage hp;
    AddCustomer ac;
    BankManager bm;
    CustomersTable ct;
    OpenAccount oa;

    @BeforeClass
    public void PageObjectsInitialization() {
        hp = new HomePage(driver);
        ac = new AddCustomer(driver);
        bm = new BankManager(driver);
        ct = new CustomersTable(driver);
        oa = new OpenAccount(driver);
    }

    @Test
    public void Create_New_Customer_Open_A_Dollar_Account_And_Verify() {
        hp.clickOnHomeButton();
        hp.clickOnBankManagerLoginButton();
        bm.clickOnAddCustomer();
        ac.createANewCustomer(data.get("FirstName"), data.get("LastName"), data.get("PostCode"));
        ac.clickOkOnAlertWindow();
        bm.clickOnOpenAccount();
        oa.selectCustomerByTypingAndVerify(data.get("FirstName"),data.get("LastName"));
        oa.selectCurrency(data.get("CurrencyType"));
        oa.clickOnProcessButton();
        String accNo = oa.verifyAccountOpenedSuccessfullyAndGetAccountNumber();
        updateDataIntoExcel("OpenAccountFunctionality", "Create_New_Customer_Open_A_Dollar_Account_And_Verify", "AccountNumber", accNo);
        updateDataIntoExcel("DepositWithdrawFunctionality", "Verify_Initial_Account_Balance", "AccountNumber", accNo);
        bm.clickOnCustomers();
        ct.verifyNewAccountNumberPresentInsideCustomerTable(data.get("FirstName"), accNo);
    }

    @Test
    public void Create_New_Customer_Open_A_Pound_Account_And_Verify() {
        hp.clickOnHomeButton();
        hp.clickOnBankManagerLoginButton();
        bm.clickOnAddCustomer();
        ac.createANewCustomer(data.get("FirstName"), data.get("LastName"), data.get("PostCode"));
        ac.clickOkOnAlertWindow();
        bm.clickOnOpenAccount();
        oa.selectCustomerByTypingAndVerify(data.get("FirstName"),data.get("LastName"));
        oa.selectCurrency(data.get("CurrencyType"));
        oa.clickOnProcessButton();
        String accNo = oa.verifyAccountOpenedSuccessfullyAndGetAccountNumber();
        updateDataIntoExcel("OpenAccountFunctionality", "Create_New_Customer_Open_A_Pound_Account_And_Verify", "AccountNumber", accNo);
        bm.clickOnCustomers();
        ct.verifyNewAccountNumberPresentInsideCustomerTable(data.get("FirstName"), accNo);
    }

    @Test
    public void Create_New_Customer_Open_A_Rupee_Account_And_Verify() {
        hp.clickOnHomeButton();
        hp.clickOnBankManagerLoginButton();
        bm.clickOnAddCustomer();
        ac.createANewCustomer(data.get("FirstName"), data.get("LastName"), data.get("PostCode"));
        ac.clickOkOnAlertWindow();
        bm.clickOnOpenAccount();
        oa.selectCustomerByTypingAndVerify(data.get("FirstName"),data.get("LastName"));
        oa.selectCurrency(data.get("CurrencyType"));
        oa.clickOnProcessButton();
        String accNo = oa.verifyAccountOpenedSuccessfullyAndGetAccountNumber();
        updateDataIntoExcel("OpenAccountFunctionality", "Create_New_Customer_Open_A_Rupee_Account_And_Verify", "AccountNumber", accNo);
        bm.clickOnCustomers();
        ct.verifyNewAccountNumberPresentInsideCustomerTable(data.get("FirstName"), accNo);
    }

}
