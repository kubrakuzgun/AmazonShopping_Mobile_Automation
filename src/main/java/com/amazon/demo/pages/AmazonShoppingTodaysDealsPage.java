package com.amazon.demo.pages;

import com.amazon.demo.hook.Hook;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;

public class AmazonShoppingTodaysDealsPage extends AmazonShoppingBase {

    private Logger logger = getLogger();
    private static AppiumDriver driver = Hook.getDriver();

    public AmazonShoppingTodaysDealsPage() {
        super();
    }


}
