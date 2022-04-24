package com.amazon.demo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AmazonShoppingProductDetailPage extends AmazonShoppingBase{

    public AmazonShoppingProductDetailPage() {
        super();
    }

    @FindBy(xpath = "//android.view.View[@resource-id='acrCustomerReviewLink']")
    public WebElement productDetailsCustomerReview;

}
