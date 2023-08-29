package test.facebook;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.ArrayList;
import java.util.List;

public class TestDividersTable {
    private WebDriver webDriver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.w3schools.com/html/html_table_styling.asp");

    }
    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
        webDriver.quit();
    }
    @Test
    public void testDividersTable(){
        Dividers dividers1 = new Dividers("Peter","Griffin","$100");
        Dividers dividers2 = new Dividers("Lois","Griffin","$150");
        Dividers dividers3 = new Dividers("Joe","Swanson","$300");
        List <Dividers> listExpectedDividers = new ArrayList<>();
        listExpectedDividers.add(dividers1);
        listExpectedDividers.add(dividers2);
        listExpectedDividers.add(dividers3);
        List<WebElement> listWebElement = webDriver.findElements(By.xpath("//*[@id='main']//table[4]//tbody//tr"));
        for( int i = 1; i<= listWebElement.size(); i++){
            List<WebElement> listMini = webDriver.findElements(By.xpath("//*[@id='main']//table[4]//tbody//tr["+ i+"]//td"));
            Assert.assertEquals(listExpectedDividers.get(i-1).getFirstName(),listMini.get(0).getText());
            Assert.assertEquals(listExpectedDividers.get(i-1).getLastName(),listMini.get(1).getText());
            Assert.assertEquals(listExpectedDividers.get(i-1).getSavings(),listMini.get(2).getText());
        }
    }

}
