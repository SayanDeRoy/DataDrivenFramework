package com.infy.practice.testcases;

import com.infy.practice.base.TestBase;
import com.infy.practice.pageobjects.AddCustomer;
import com.infy.practice.pageobjects.BankManager;
import com.infy.practice.pageobjects.CustomersTable;
import com.infy.practice.pageobjects.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateDeleteCustFunctionality extends TestBase {

    HomePage hp;
    AddCustomer ac;
    BankManager bm;
    CustomersTable ct;

    @BeforeClass
    public void PageObjectsInitialization() {
        hp = new HomePage(driver);
        ac = new AddCustomer(driver);
        bm = new BankManager(driver);
        ct = new CustomersTable(driver);
    }

    @Test(priority = 1)
    public void Create_New_Customer() {
        hp.clickOnBankManagerLoginButton();
        bm.clickOnAddCustomer();
        ac.createANewCustomer(data.get("FirstName"), data.get("LastName"), data.get("PostCode"));
        String custID = ac.verifyCustomerCreatedSuccessfullyAndGetCustomerID();
        updateDataIntoExcel("CreateDeleteCustFunctionality", "Create_New_Customer", "CustID", custID);
    }

    @Test(dependsOnMethods = "Create_New_Customer", priority = 2)
    public void Verify_Newly_Created_Customer_In_Customer_Table() {
        hp.clickOnHomeButton();
        hp.clickOnBankManagerLoginButton();
        bm.clickOnCustomers();
        ct.verifyNewCustomerPresentInsideCustomerTable(data.get("FirstName"), data.get("LastName"), data.get("PostCode"));
    }

    @Test(dependsOnMethods = {"Create_New_Customer"}, priority = 3)
    public void Delete_Newly_Created_Customer() {
        hp.clickOnHomeButton();
        hp.clickOnBankManagerLoginButton();
        bm.clickOnCustomers();
        ct.deleteCustomerAndVerify(data.get("FirstName"));
    }

}
