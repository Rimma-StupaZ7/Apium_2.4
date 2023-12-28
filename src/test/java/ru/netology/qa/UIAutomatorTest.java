package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.netology.qa.screens.AutomatorPage;

public class UIAutomatorTest {

    private AndroidDriver driver;
    private String emptyString = " ";

    private String originalText = "";
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
    public void sampleTest() {
            automatorPage.userInput.isDisplayed();
            automatorPage.userInput.click();
            automatorPage.userInput.clear(); // Очищаем поле ввода
            automatorPage.userInput.sendKeys(emptyString);

            automatorPage.buttonChange.isDisplayed();
            automatorPage.buttonChange.click();

            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userInputId")));

            automatorPage.text.isDisplayed();
            Assertions.assertEquals(originalText, automatorPage.text.getText());
    }

    @Test
    public void buttonActivityTest() {
        automatorPage.userInput.isDisplayed();
        automatorPage.userInput.click();
        automatorPage.userInput.clear(); // Очищаем поле ввода
        automatorPage.userInput.sendKeys(text);

        automatorPage.buttonActivity.isDisplayed();
        automatorPage.buttonActivity.click();

        WebDriverWait wait = new WebDriverWait(driver, 20); // Увеличил время ожидания до 20 секунд
        wait.until(ExpectedConditions.visibilityOf(automatorPage.newActivityText));

        automatorPage.newActivityText.isDisplayed();
        Assertions.assertEquals(text, automatorPage.newActivityText.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
