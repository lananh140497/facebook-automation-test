package org.example.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class LoginPageTest {
    private WebDriver webDriver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.facebook.com/");

    }
    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(10000);
        webDriver.close();
        webDriver.quit();
    }
    @Test
    public void TestLoginSuccess() throws InterruptedException {
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        emailElement.sendKeys("100088268452149");
        WebElement passwordElement = webDriver.findElement(By.xpath("//*[@id='pass']"));
        passwordElement.sendKeys("Aa@123456");
        WebElement buttonLogin = webDriver.findElement(By.xpath("//*[@type='submit']"));
        buttonLogin.click();
        Thread.sleep(2000);
        String currentTitle = webDriver.getTitle();
        Assert.assertEquals("Facebook",currentTitle);
    }
  @Test
    public void accessUrlAfterLogin() throws InterruptedException {
      WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
      emailElement.sendKeys("100088268452149");
      WebElement passwordElement = webDriver.findElement(By.xpath("//*[@id='pass']"));
      passwordElement.sendKeys("Aa@123456");
      WebElement buttonLogin = webDriver.findElement(By.xpath("//*[@type='submit']"));
      buttonLogin.click();
      Thread.sleep(2000);
      webDriver.get("https://www.facebook.com/");
      String currentTitle = webDriver.getTitle();
      Assert.assertEquals("Facebook",currentTitle);
  }
    @Test
    public void no2() throws InterruptedException {
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        emailElement.sendKeys("100088268452149");
        System.out.println("in ra "+ emailElement.getAttribute("value"));
    }

    @Test
    public void testWebDriverWaitLoging(){

    }

}
