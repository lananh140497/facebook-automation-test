package test.facebook;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class LoginPageTest {
    private WebDriver webDriver;
    @Before
    public void Setup(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        webDriver = new ChromeDriver();
       webDriver.get("https://www.facebook.com/");
    }
    @After
    public void Close() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.close();
        webDriver.quit();
    }
    @Test
    public void no1_AccessLoginByUrl(){
        String title = webDriver.getTitle();
        Assert.assertEquals("Facebook – log in or sign up",title);
    }
    @Test
    public void no2_AccessUrlAfterLogin() throws InterruptedException {
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
        ((JavascriptExecutor) webDriver).executeScript
                ("window.open('https://www.facebook.com/');");
    }
    @Test
    public void no6_CheckDefaultValue(){
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        Assert.assertEquals(true,emailElement.isEnabled());
        Assert.assertEquals("",emailElement.getAttribute("value"));
    }
    @Test
    public void no7_CheckPlaceHolder(){
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        Assert.assertEquals("Email address or phone number",emailElement.getAttribute("placeholder"));
    }
    @Test
    public void no8_Checkrequired() throws InterruptedException {
        WebElement buttonLogin = webDriver.findElement(By.xpath("//*[@type='submit']"));
        buttonLogin.click();
        Thread.sleep(2000);
        WebElement message = webDriver.findElement(By.xpath("//*[@class= '_9ay7']"));
        Assert.assertEquals("The email address or mobile number you entered isn't connected to an account. Find your account and log in.",message.getText());
    }

    @Test
    public void no9_CheckMaxlenght(){
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        Assert.assertEquals(null, emailElement.getAttribute("maxlenght") );
    }
    @Test
    public void no10_CheckTrimSpace() throws InterruptedException {
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        emailElement.sendKeys("  100088268452149  ");
        WebElement passwordElement = webDriver.findElement(By.xpath("//*[@id='pass']"));
        passwordElement.sendKeys("Aa@123456");
        WebElement buttonLogin = webDriver.findElement(By.xpath("//*[@type='submit']"));
        buttonLogin.click();
        Thread.sleep(2000);
        String currentTitle = webDriver.getTitle();
        Assert.assertEquals("Facebook",currentTitle);
    }
}
