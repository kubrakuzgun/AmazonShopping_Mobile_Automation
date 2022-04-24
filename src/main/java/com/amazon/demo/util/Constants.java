package com.amazon.demo.util;

public interface Constants {
    public static final String LOCALHOST = "127.0.0.1";

    public static final int DEFAULT_APPIUM_PORT = 4723;
    public static final String DEFAULT_APPIUM_URL = "http://" + LOCALHOST + ":" + DEFAULT_APPIUM_PORT;
    public static final String DEFAULT_APPIUM_PLATFORM_NAME = "android";
    public static final String DEFAULT_APPIUM_AUTOMATION_TYPE = "uiautomator2";
    public static final String APPIUM_VERSION1_API_ENDPOINT = "/wd/hub/";
}
