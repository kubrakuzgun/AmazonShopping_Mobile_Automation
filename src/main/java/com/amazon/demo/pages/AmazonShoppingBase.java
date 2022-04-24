package com.amazon.demo.pages;

import com.amazon.demo.hook.Hook;
import com.thoughtworks.gauge.Gauge;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

public class AmazonShoppingBase {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static AppiumDriver driver = Hook.getDriver();
    protected static WebDriverWait webDriverWait;


    public static AppiumDriver getDriver() {
        return driver;
    }

    public Logger getLogger() {
        return logger;
    }

    public AmazonShoppingBase() {
        webDriverWait = new WebDriverWait(driver,15);
        PageFactory.initElements(driver,this);
    }

    public void waitUntilElementVisible(WebElement element){
        long start = System.currentTimeMillis();
        try {
            logger.info(driver.toString());
            logger.info("-----Waiting for element to be visible-----");
            webDriverWait.withTimeout(Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
            logger.info("Element is visible");
            long finish = System.currentTimeMillis();
            long timeElapsed = (finish - start);
            logger.info("Element waiting time: " + timeElapsed + " milliseconds");
        } catch (Exception e) {
            long finish = System.currentTimeMillis();
            long timeElapsed = (finish - start);
            logger.info("Element waiting time: " + timeElapsed + " milliseconds");
            Assertions.fail(element.toString()+" not visible");
            Gauge.captureScreenshot();
            e.printStackTrace();
        }
    }


    public void swipeUpUntilSeeElement(WebElement element) {
        int maxRetryCount = 6;
        while (maxRetryCount > 0) {
            try {
                if (element.isEnabled()) {
                    logger.info(element + " element found");
                    break;
                }
            }catch (Exception e){
                maxRetryCount--;
                new TouchAction(driver)
                        .press(PointOption.point(506, 1829))
                        .waitAction(waitOptions(ofMillis(1000)))
                        .moveTo(PointOption.point(506, 1085))
                        .release()
                        .perform();
                logger.info("--------------------------");
                logger.info("SWIPED UP");
                logger.info("--------------------------");
                if (maxRetryCount == 0) {
                    Assertions.fail(element + "element does not exist!");
                    break;
                }
            }
        }
    }

    public void pressAndHoldElement(WebElement element) {
        Point point = element.getLocation();
        TouchAction a2 = new TouchAction(driver);
        a2.press(PointOption.point(point.x, point.y)).waitAction(WaitOptions.waitOptions(ofMillis(3000))).release().perform();

    }

    public void goBackNTimes(int count){
        for(int i=0; i<count; i++){
            driver.navigate().back();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void launchAmazonShoppingApp(){
        driver.launchApp();
        logger.info("Amazon Shopping is launched");
    }


}
