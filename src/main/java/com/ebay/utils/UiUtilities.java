package com.ebay.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UiUtilities {

    private static final int TIMEOUT = 10;

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public static void navigateTo(WebDriver driver, String targetUrl){
        driver.navigate().to(targetUrl);
    }

    public static void click(WebDriver driver, By locator){
        WebElement element = getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public static void setText(WebDriver driver, By locator, String text){
        WebElement element = getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    public static void clear(WebDriver driver){
        // Optional if you want a clear with wait
    }

    public static boolean isDisplayed(WebDriver driver, By locator){
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
    }

    public static String getText(WebDriver driver, By locator){
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    public static String getCurrentUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public static List<WebElement> listElements(WebDriver driver, By locator){
        return getWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public static void waitForVisibility(WebDriver driver, By locator){
        getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}