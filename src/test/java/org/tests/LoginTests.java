package org.tests;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.pages.LoginPage;
import org.testng.Assert;
import org.utils.AppiumDriverBuilder;
import org.utils.AppiumServerConfigs;
import org.utils.JsonFileManager;
import org.testng.annotations.*;

@Epic("Regression tests")
@Feature("Login Tests")
public class LoginTests {

    AndroidDriver driver;
    AppiumServerConfigs appiumServerConfigs;
    AppiumDriverBuilder appiumDriverBuilder;
    LoginPage loginPage;

    @Test(description = "verify login with correct user name and password")
    public void verifyLoginCorrectCredintials(){
        JsonFileManager jsonFileManager = new JsonFileManager("src/test/java/org/testData/ValidLoginTestData.json");

        loginPage.login(jsonFileManager.getTestData("userName"),jsonFileManager.getTestData("password"));
        loginPage.openLeftMenu();

        Assert.assertTrue(loginPage.isLogoutLinkAppear());
        loginPage.logout();
    }

    @Test(description = "verify login with incorrect user name and password")
    public void verifyLoginIncorrectCredintials(){
        JsonFileManager jsonFileManager = new JsonFileManager("src/test/java/org/testData/InvalidLoginTestData.json");

        loginPage.login(jsonFileManager.getTestData("userName"),jsonFileManager.getTestData("password"));
        Assert.assertEquals(loginPage.getLoginErrorMsg(),jsonFileManager.getTestData("invalidLoginErrorMsg"));
    }

    @BeforeClass
    public void setup()  {
        appiumServerConfigs = new AppiumServerConfigs();
        appiumServerConfigs.startAppiumServer();
        appiumDriverBuilder = new AppiumDriverBuilder();
        driver = appiumDriverBuilder.getAppiumDriver(appiumServerConfigs.getAppiumServiceBuilder());
        loginPage = new LoginPage(driver);

    }

    @AfterClass
    public void tearDown(){
        appiumDriverBuilder.closeAppiumDriver();
        appiumServerConfigs.stopAppiumServer();
    }
}
