package test.facebook;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class TableElementTest {
    private WebDriver webDriver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.w3schools.com/html/html_tables.asp");

    }
    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(10000);
        webDriver.close();
        webDriver.quit();
    }
    @Test
    public void tableElementTest(){
        List <WebElement> listWebElement;
        listWebElement = webDriver.findElements(By.xpath("//*[@id='customers']//tr"));
        System.out.println("Số bản ghi Customer : "+ listWebElement.size());
        List <Customer> listCustomer = new ArrayList<>();

        for(int i =1; i<listWebElement.size();i++){
            List <WebElement> listMini;
            listMini = listWebElement.get(i).findElements(By.xpath(".//td"));
            Customer customer = new Customer(listMini.get(0).getText(),listMini.get(1).getText(),listMini.get(2).getText());
            listCustomer.add(customer);
        }
        Assert.assertEquals(6,listCustomer.size());
        Assert.assertEquals("Maria An6ders",listCustomer.get(0).getContact());
    }
}

