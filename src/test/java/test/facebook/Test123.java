package test.facebook;

import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Test123 {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.w3schools.com/spaces/");

    }

    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(9000);
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void test12() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 100000);
        WebElement button1 = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[9]/div/div[3]/div[1]/a")));
        System.out.println(button1.getText());
        System.out.println(button1);
        // tryClickNTime(button1,2,2);
        WebElement loginBtn = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='w3loginbtn']")));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();",loginBtn);
    }

    private void tryClickNTime(WebElement button, int time, int seconds) {
        for (int i = 0; i < time; i++) {
            try {
                button.click();
                Thread.sleep(seconds * 1000);
            } catch (Exception e) {
            }
        }
    }
}
