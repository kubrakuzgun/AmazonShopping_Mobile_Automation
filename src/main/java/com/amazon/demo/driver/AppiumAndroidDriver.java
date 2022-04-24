package com.amazon.demo.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;

import java.net.URL;
import com.amazon.demo.util.Constants;
import org.slf4j.LoggerFactory;

public class AppiumAndroidDriver{

    private static AppiumDriver appiumDriver;
    public Logger logger = LoggerFactory.getLogger(getClass());

    public AppiumAndroidDriver() throws Exception {
        setCapabilities();
    }

    @NotNull
    public AppiumDriver setCapabilities() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        
        String deviceName = System.getenv("DEVICE_NAME");
        String deviceUDID = System.getenv("DEVICE_UDID");
        String appPackage = System.getenv("APP_PACKAGE");
        String appActivity = System.getenv("APP_ACTIVITY");
        String platformVersion = System.getenv("APPIUM_PLATFORM_VER");

        cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        cap.setCapability(MobileCapabilityType.UDID, deviceUDID);
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        cap.setCapability(MobileCapabilityType.NO_RESET, "true");
        cap.setCapability(AndroidMobileCapabilityType.AUTO_LAUNCH, false);
        cap.setCapability("appPackage", appPackage);
        cap.setCapability("appActivity", appActivity);


        String urlText = String.format("http://%s:%d%s", Constants.LOCALHOST, Constants.DEFAULT_APPIUM_PORT, Constants.APPIUM_VERSION1_API_ENDPOINT);
        logger.info(urlText);
        URL url = new URL(urlText);
        appiumDriver = new AndroidDriver<MobileElement>(url, cap);

        logger.info(String.valueOf(appiumDriver));
        return appiumDriver;
    }

    public void tearDown(WebDriver driver) {
        if (driver!= null){
            driver.quit();
        }
    }

    public AppiumDriver getAppiumDriver(){
        return appiumDriver;
    }
}
