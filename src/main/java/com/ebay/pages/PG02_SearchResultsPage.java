package com.ebay.pages;

import com.ebay.utils.UiUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PG02_SearchResultsPage {

    private final By resultsContainer = By.id("srp-river-results");
    private final By cards = By.cssSelector("li.s-card.s-card--horizontal");
    private final By manualFilter = By.xpath("//span[text()='Manual']");
    private final By countLabel = By.className("srp-controls__count-heading");

    WebDriver driver;

    public PG02_SearchResultsPage(WebDriver driver){
        this.driver = driver;
    }

    public void waitForResultsContainer(){
        UiUtilities.waitForVisibility(driver,resultsContainer);
    }

    public long getCardsCount() {
        List<WebElement> allItems = driver.findElements(By.cssSelector("li.s-card.s-card--horizontal"));
        long visibleCount = allItems.stream()
                .filter(item -> {
                    WebElement parent = item.findElement(By.xpath("..")); // direct parent
                    String ariaHidden = parent.getAttribute("aria-hidden");
                    return !"true".equals(ariaHidden);
                })
                .count();
        return visibleCount;
    }

    public void applyManualTransmissionFilter(){
        UiUtilities.click(driver,manualFilter);
    }

    public String getActualResultsCount(){
       UiUtilities.waitForVisibility(driver,countLabel);
       return UiUtilities.getText(driver,countLabel).trim().split(" ")[0];
    }



}
