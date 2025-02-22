package org.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumDriverBuilder {

    AndroidDriver driver;

    public AndroidDriver getAppiumDriver(AppiumDriverLocalService appiumServiceBuilder)  {
        PropertyManager propertyManager = new PropertyManager();

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setDeviceName(propertyManager.readFromProperty("DeviceName"));
        uiAutomator2Options.setApp(propertyManager.readFromProperty("apkPath"));
        uiAutomator2Options.setAppPackage("com.swaglabsmobileapp");
        uiAutomator2Options.setAppActivity("com.swaglabsmobileapp.MainActivity");
        uiAutomator2Options.noReset();

        driver = new AndroidDriver(appiumServiceBuilder.getUrl(),uiAutomator2Options);

        return driver;
    }

    public void closeAppiumDriver(){
        driver.quit();
    }
}
