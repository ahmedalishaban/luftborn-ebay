package com.ebay.listeners;

import com.ebay.tests.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {

        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).driver;

        takeScreenshot(driver);
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] takeScreenshot(WebDriver driver) {

        return ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
    }
}