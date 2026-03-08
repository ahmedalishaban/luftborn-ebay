package com.ebay.tests;

import com.ebay.pages.PG01_HomePage;
import com.ebay.pages.PG02_SearchResultsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public WebDriver driver;
    public PG01_HomePage homePage;
    public PG02_SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        homePage = new PG01_HomePage(driver);
        searchResultsPage = new PG02_SearchResultsPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
