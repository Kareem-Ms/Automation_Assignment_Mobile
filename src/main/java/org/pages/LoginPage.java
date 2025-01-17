package org.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.utils.ElementsAction;


public class LoginPage {

    //********** Variables **********\\
    AndroidDriver driver;
    ElementsAction elementsAction;

    public LoginPage(AndroidDriver driver){
        this.driver = driver;
        elementsAction = new ElementsAction(driver);
    }

    //********** Locators **********\\
    AppiumBy userNameInputLocator = (AppiumBy) AppiumBy.accessibilityId("test-Username");
    AppiumBy passwordInputLocator = (AppiumBy) AppiumBy.accessibilityId("test-Password");
    AppiumBy loginBtnLocator = (AppiumBy) AppiumBy.accessibilityId("test-LOGIN");
    By loginErrorMsgLocator = By.xpath("//android.view.ViewGroup[@content-desc = 'test-Error message']//android.widget.TextView");
    By leftMenuLocator = By.xpath("//android.view.ViewGroup[@content-desc = 'test-Menu']");
    By logoutLinkLocator = By.xpath("//android.view.ViewGroup[@content-desc = 'test-LOGOUT']");

    //*********** Actions **********\\

    @Step("Login with user name as: {userName} and password: {password}")
    public void login(String userName, String password){
        elementsAction.type(userNameInputLocator,userName);
        elementsAction.type(passwordInputLocator,password);
        elementsAction.click(loginBtnLocator);
    }

    @Step("Open left side menu")
    public void openLeftMenu(){
        elementsAction.click(leftMenuLocator);
    }

    @Step("Check if logout link appears")
    public boolean isLogoutLinkAppear(){
        return elementsAction.isElementExist(logoutLinkLocator);
    }

    @Step("Get login error message")
    public String getLoginErrorMsg(){
        return elementsAction.getText(loginErrorMsgLocator);
    }

    @Step("Logout")
    public void logout(){
        elementsAction.click(logoutLinkLocator);
    }


}
