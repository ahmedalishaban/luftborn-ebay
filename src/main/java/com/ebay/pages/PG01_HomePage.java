package com.ebay.pages;

import com.ebay.utils.UiUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PG01_HomePage {

    //elements
    private final String homePageUrl = "https://www.ebay.com/";
    private final By navBarElement = By.id("vl-flyout-nav");
    private final By bannerElement = By.className("carousel__container");
    private final By searchInput = By.cssSelector("[title='Search']");
    private final By searchBtn = By.id("gh-search-btn");

    //driver
    WebDriver driver;
    //constructor
    public PG01_HomePage(WebDriver driver){
        this.driver = driver;
    }

    //methods
    public void navigateToHomePage(){
        UiUtilities.navigateTo(driver,homePageUrl);
    }

    public boolean verifyUserShowsToHimHomePageBanners(){
        return UiUtilities.isDisplayed(driver,bannerElement);
    }

    public boolean verifyUserShowsToHimNavBar(){
        return UiUtilities.isDisplayed(driver,navBarElement);
    }

    public String verifyHomePageUrl(){
        return UiUtilities.getCurrentUrl(driver);
    }

    public void searchForProduct(String productName){
        UiUtilities.setText(driver,searchInput,productName);
    }

    public void clickSearchBtn(){
        UiUtilities.click(driver,searchBtn);
    }


}
