package ru.netology.qa.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


public class AutomatorPage {
    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    public WebElement userInput;
    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public WebElement buttonChange;
    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    public WebElement buttonActivity;
    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    public WebElement text;



    private AppiumDriver driver;

    public AutomatorPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}

