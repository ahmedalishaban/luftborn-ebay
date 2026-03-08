package com.ebay.tests;

import com.ebay.listeners.TestListener;
import com.ebay.utils.JsonReader;
import com.ebay.utils.UiUtilities;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class TC01_HomeTest extends BaseTest{


    @Test
    public void validateHomePageUrl() throws Exception {
        homePage.navigateToHomePage();
        String actualUrl = homePage.verifyHomePageUrl();
        String expectedUrl = JsonReader.getKeyword("HomePageURL");;
        Assert.assertTrue(actualUrl.contains(expectedUrl));
    }



}
