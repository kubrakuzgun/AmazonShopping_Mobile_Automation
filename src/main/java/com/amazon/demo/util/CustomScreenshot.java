package com.amazon.demo.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazon.demo.hook.Hook;
import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class CustomScreenshot implements CustomScreenshotWriter {

    @Override
    public String takeScreenshot() {
        TakesScreenshot driver = (TakesScreenshot) Hook.getDriver();

        Date dateNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd_hhmmss_SSS");

        String fileName = "screenshot_" + ft.format(dateNow) + ".png";
        File screenshotFile = new File(Paths.get(System.getenv("gauge_screenshots_dir"), fileName).toString());
        File tmpFile = driver.getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(tmpFile, screenshotFile);
        } catch (IOException e) {
        }

        return screenshotFile.getName();
    }
}
