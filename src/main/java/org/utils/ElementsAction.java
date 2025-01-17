package org.utils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.fail;

public class ElementsAction {

    AndroidDriver driver;
    public ElementsAction(AndroidDriver driver){
        this.driver = driver;
    }

    public WebDriverWait getExplicitWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By elementLocator) {
        try {
            getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        }

        try {
            driver.findElement(elementLocator).click();
        } catch (Exception exception) {
                fail("Couldn't click on the element:" + elementLocator, exception);
        }
    }

    public void type(AppiumBy elementLocator, String text) {
        locatingElement(elementLocator);
        try {
            driver.findElement(elementLocator).clear();
            driver.findElement(elementLocator).sendKeys(text);

        } catch (Exception e) {
            fail(e.getMessage());
        }

    }

    public String getText(By elementLocator) {
        locatingElement(elementLocator);
        try {
            return driver.findElement(elementLocator).getText();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        return null;
    }


    public boolean isElementExist(By elementLocator) {
        try {
            getExplicitWait(driver).until(d -> driver.findElement(elementLocator).isDisplayed());
            return driver.findElement(elementLocator).isDisplayed();
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
            return false;
        }
    }

    private void locatingElement(By elementLocator) {
        try {
            getExplicitWait(driver).until(d -> driver.findElement(elementLocator).isDisplayed());
            if (!driver.findElement(elementLocator).isDisplayed()) {
                fail("The element [" + elementLocator + "] is not Displayed");
            }
        } catch (TimeoutException toe) {
            fail(toe.getMessage());
        }
    }
}
