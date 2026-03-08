package com.ebay.tests;

import com.ebay.listeners.TestListener;
import com.ebay.utils.JsonReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_SearchResultsTest extends BaseTest {

    @Test
    public void searchResultsTest() throws Exception {

        homePage.navigateToHomePage();

        String productSKU = JsonReader.getKeyword("productSKU");
        homePage.searchForProduct(productSKU);
        homePage.clickSearchBtn();

        searchResultsPage.waitForResultsContainer();


        long resultsCountList = searchResultsPage.getCardsCount();
        String actualCountLabel = searchResultsPage.getActualResultsCount();

        Assert.assertEquals(resultsCountList, 5);
        Assert.assertEquals(actualCountLabel, "5");

    }

    @Test
    public void verifySearchResultsWithManualTransmission() throws Exception {

        homePage.navigateToHomePage();

        String productSKU = JsonReader.getKeyword("productSKU");
        homePage.searchForProduct(productSKU);
        homePage.clickSearchBtn();

        searchResultsPage.waitForResultsContainer();
        searchResultsPage.applyManualTransmissionFilter();
        long resultsCountList = searchResultsPage.getCardsCount();
        String actualCountLabel = searchResultsPage.getActualResultsCount();

        Assert.assertEquals(resultsCountList, 3);
        Assert.assertEquals(actualCountLabel, "3");

    }




}
