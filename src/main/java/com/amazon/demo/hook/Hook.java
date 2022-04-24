package com.amazon.demo.hook;


import com.amazon.demo.driver.AppiumAndroidDriver;
import com.thoughtworks.gauge.*;
import io.appium.java_client.AppiumDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Hook {

    public Logger logger = LoggerFactory.getLogger(getClass());
    private static AppiumAndroidDriver driver;

    public Hook() {
    }

    @BeforeStep
    public void beforeStep (ExecutionContext e) throws InterruptedException {
        logger.debug(e.getCurrentStep().getText());
    }

    @BeforeSpec
    public void beforeSpec() throws Exception {
        driver = new AppiumAndroidDriver();
    }

    @AfterSpec
    public void afterSpec() throws InterruptedException {
        driver.getAppiumDriver().quit();
    }

    public static AppiumDriver getDriver(){
        return driver.getAppiumDriver();
    }

}

