package com.amazon.demo.pages;

import com.amazon.demo.hook.Hook;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import org.junit.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;

import java.util.List;


public class AmazonShoppingHomePage extends AmazonShoppingBase {
    private Logger logger = getLogger();
    private static AppiumDriver driver =  Hook.getDriver();

    public AmazonShoppingHomePage() {
        super();
    }


    //elements


    @FindBy(xpath= "//android.widget.ImageView[@resource-id='com.amazon.mShop.android.shopping:id/action_bar_home_logo']")
    public WebElement amazonLogo;

    @FindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
    public WebElement searchBar;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/iss_search_dropdown_item_text'][1]")
    public WebElement searchSuggestedWordFirst;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title'][1]")
    public WebElement firstSearchResult;

    private AmazonShoppingProductDetailPage productDetailPage = new AmazonShoppingProductDetailPage();

    public void checkHomepageLogo(){
        waitUntilElementVisible(amazonLogo);
        Assert.assertTrue("Logo is displayed", amazonLogo.isDisplayed());
    }

    public void searchForProduct(String keyword) throws InterruptedException {
        waitUntilElementVisible(searchBar);
        searchBar.click();
        Thread.sleep(1000);
        searchBar.sendKeys(keyword);
        waitUntilElementVisible(searchSuggestedWordFirst);
        searchSuggestedWordFirst.click();
    }

    public void checkSearchResults(){
        waitUntilElementVisible(firstSearchResult);
        String firstResultProductName = firstSearchResult.getText();
        firstResultProductName.replace("...","");
        firstResultProductName.trim();
        firstSearchResult.click();
        waitUntilElementVisible(productDetailPage.productDetailsCustomerReview);
        MobileElement expectedProduct = null;
        List<MobileElement> detailPageElements = driver.findElementsByClassName("android.view.View");
        for(MobileElement element : detailPageElements){
            if(element.getText().contains(firstResultProductName)){
                logger.info("Product titles match");
                expectedProduct = element;
            }
        }
        Assert.assertTrue("Product titles do not match!", expectedProduct!=null);
    }



}
