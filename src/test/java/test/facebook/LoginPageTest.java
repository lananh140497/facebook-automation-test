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
        Thread.sleep(10000);
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
    public void no3_ClickButtonBack() throws InterruptedException {
        Thread.sleep(3000);
        webDriver.navigate().back();
        Assert.assertEquals("",webDriver.getTitle());
    }
    @Test
    public void no6_CheckDefaultValue(){
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        Assert.assertEquals(true,emailElement.isEnabled());
        emailElement.sendKeys("100088268452149");
        //System.out.println("Đây nhjashjasdjasdhj"+ emailElement);
    }
    @Test
    public void no7_CheckPlaceHolder(){
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        Assert.assertEquals("Email address or phone number",emailElement.getAttribute("placeholder"));
    }

}
