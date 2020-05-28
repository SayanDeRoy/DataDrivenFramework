package com.infy.practice.testcases;

import com.infy.practice.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class DifferentActions extends TestBase {

    @Test
    public void Window_Authorization() {
        String userName = data.get("UserName");
        String password = data.get("Password");

        String expectedLoginSuccessMessage = "Congratulations! You must have the proper credentials.";
        String url = "https://" + userName + ":" + password + "@the-internet.herokuapp.com/";

        driver.get(url);
        driver.findElement(By.linkText("Basic Auth")).click();

        String actualLoginSuccessMessage = driver.findElement(By.xpath("//h3[text()='Basic Auth']/following-sibling::p")).getText();
        Assert.assertEquals(actualLoginSuccessMessage, expectedLoginSuccessMessage);
    }

    @Test
    public void Upload_File_Using_Send_Keys() throws IOException, InterruptedException {
        driver.get("https://altoconvertpdftojpg.com/");
        driver.findElement(By.xpath("//button[@id='dropzoneInput-label']")).click();
        Thread.sleep(3000);

        Runtime.getRuntime().exec("C:\\Selenium Drivers\\AutoIT Script\\uploadFile.exe");


    }


}
