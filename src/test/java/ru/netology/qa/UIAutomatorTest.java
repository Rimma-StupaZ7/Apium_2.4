package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.netology.qa.screens.AutomatorPage;
import org.junit.jupiter.api.Test;


public class UIAutomatorTest {
    private AndroidDriver driver;
    private final String emptyString = " ";
    private String text = "vervte";

    private AutomatorPage automatorPage;


    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);


        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        automatorPage = new AutomatorPage(driver);
    }

    @Test
    public void emptyStringTest() {
        automatorPage.userInput.isDisplayed();
        automatorPage.userInput.click();
        automatorPage.userInput.clear(); // Clear any existing text
        automatorPage.userInput.sendKeys("");

        automatorPage.buttonChange.isDisplayed();
        automatorPage.buttonChange.click();
        automatorPage.userInput.isDisplayed();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(driver -> automatorPage.text.getText().isEmpty());

        Assertions.assertTrue(automatorPage.text.getText().isEmpty());
    }


    @Test
    public void openNewActivityTest() {
        automatorPage.userInput.isDisplayed();
        automatorPage.userInput.click();
        automatorPage.userInput.clear(); // Clear any existing text
        automatorPage.userInput.sendKeys(text);

        automatorPage.buttonActivity.isDisplayed();
        automatorPage.buttonActivity.click();

        WebDriverWait wait = new WebDriverWait(driver, 5); // Wait for up to 5 seconds for the new Activity
        wait.until(ExpectedConditions.visibilityOf(automatorPage.text));

        automatorPage.text.isDisplayed();

        Assertions.assertEquals(text.trim(), automatorPage.text.getText().trim());
    }



    @Test
    public void sampleTest() {
        automatorPage.userInput.isDisplayed();
        automatorPage.userInput.click();
        automatorPage.userInput.sendKeys(emptyString);

        automatorPage.buttonChange.isDisplayed();
        automatorPage.buttonChange.click();

        automatorPage.userInput.isDisplayed();
        Assertions.assertEquals(emptyString,automatorPage.userInput.getText());
    }
    @Test
    public void buttonActivityTest() {
        automatorPage.userInput.isDisplayed();
        automatorPage.userInput.click();
        automatorPage.userInput.sendKeys(text);

        automatorPage.buttonActivity.isDisplayed();
        automatorPage.buttonActivity.click();

        WebDriverWait wait = new WebDriverWait(driver, 1); // wait for up to 1 second
        wait.until(ExpectedConditions.visibilityOf(automatorPage.text));

        automatorPage.text.isDisplayed();

        Assertions.assertEquals(text,automatorPage.text.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}