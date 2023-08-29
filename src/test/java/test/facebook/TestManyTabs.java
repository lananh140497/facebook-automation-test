package test.facebook;

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

public class TestManyTabs {
    private WebDriver webDriver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.w3schools.com/sql/default.asp");

    }

    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
        webDriver.quit();
    }

    @Test
    public void test() throws InterruptedException {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 100000);
        WebElement buttonGet = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='W3Schools Spaces']")));
        buttonGet.click();
        switchOtherWindows();
        WebElement button1 = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[9]/div/div[3]/div[1]/a")));
//        tryClickNTime(button1,2,2);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

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

    private void switchOtherWindows() {
        Set<String> windowshandles = webDriver.getWindowHandles();
        List<String> listwindows = new ArrayList<>(windowshandles);// convert từ set thành list
        System.out.println("Danh sach" + listwindows);
        String currentwd = webDriver.getWindowHandle();
        System.out.println(currentwd);
        String otherwd = null;
        for (String CDwindow : listwindows) {
            if (!CDwindow.equals(currentwd)) {
                otherwd = CDwindow;
            }
        }
        System.out.println("otherwd" + otherwd);
        webDriver.switchTo().window(otherwd);
    }
}
