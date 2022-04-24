package com.amazon.demo.steps;

import com.amazon.demo.pages.AmazonShoppingBase;
import com.amazon.demo.pages.AmazonShoppingHomePage;
import com.amazon.demo.pages.AmazonShoppingSettingsPage;
import com.thoughtworks.gauge.Step;

public class AmazonShoppingTestSteps {
    private AmazonShoppingBase amazonShoppingBase;
    private AmazonShoppingHomePage homePage;
    private AmazonShoppingSettingsPage settingsPage;

    //constructor for LinkNowSteps
    public AmazonShoppingTestSteps() {
        //initiallize objects of required pages
        amazonShoppingBase = new AmazonShoppingBase();
        homePage = new AmazonShoppingHomePage();
        settingsPage = new AmazonShoppingSettingsPage();
    }

    @Step("Launch Amazon Shopping app and check homepage visibility")
    public void launchAmazonShoppingAndCheckHomepage() {
        amazonShoppingBase.launchAmazonShoppingApp();
        homePage.checkHomepageLogo();
    }

    @Step("Search for a product and check results")
    public void searchProductAndCheckResults() throws InterruptedException {
        homePage.searchForProduct("keyboard");
        homePage.checkSearchResults();
    }

    @Step("Add product to cart and check total price")
    public void addProductToCartAndCheckPrice() {

    }


}
