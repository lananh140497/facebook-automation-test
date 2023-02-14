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

public class testWebDriverWaitTest {
    private WebDriver webDriver;
    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.facebook.com/");

    }
    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(10000);
        webDriver.close();
        webDriver.quit();
    }
    @Test
    public void testWebDriverWaitLoging(){
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//*[@id= 'email']"))); // chờ cho đến khi element đó xuất hiên
//        emailElement.sendKeys("100088268452149");
        WebElement passwordElement = webDriver.findElement(By.xpath("//*[@id='pass']"));
//        passwordElement.sendKeys("Aa@123456");
//        WebElement buttonLogin = webDriver.findElement(By.xpath("//*[@type='submit']"));
//        buttonLogin.click();


        Actions actions = new Actions(webDriver);
        actions.moveToElement(passwordElement)
                .click()
//                .sendKeys("100088268452149")
                .pause(3000)
                .keyDown(Keys.SHIFT) // nhấn giữ phím
                .sendKeys(Keys.TAB) // ấn phím tab
                .keyUp(Keys.SHIFT) // nhả phím
                .build()//
                .perform();
//        .sendKeys(Keys.TAB).pause(3000)
//                        .sendKeys(press).pause(3000).build().perform();
//        Assert.assertEquals(passwordElement,webDriver.switchTo().activeElement());// kiem tra con tro chuot
    }
    @Test
    public void testCheckpoint(){
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//*[@id= 'email']"))); // chờ cho đến khi element đó xuất hiên
        emailElement.sendKeys("100089530206487");
        WebElement passwordElement = webDriver.findElement(By.xpath("//*[@id='pass']"));
        passwordElement.sendKeys("A0RErtMd");
        WebElement buttonLogin = webDriver.findElement(By.xpath("//*[@type='submit']"));
        buttonLogin.click();
//        WebElement buttonContinue = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//*[@role= 'main']//*[@role='button']")));
//        buttonContinue.click();
        WebElement iframeCha = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id ='captcha-recaptcha']"))); // tìm iframe
        webDriver.switchTo().frame(iframeCha); // chuyển từ html vào iframe cha
        WebElement iframeCon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title ='reCAPTCHA']")));
        webDriver.switchTo().frame(iframeCon);// chuyển từ iframe cha đến iframe con
        WebElement checkboxCapcha = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//*[@role = 'presentation' and @class ='recaptcha-checkbox-border']")));
        checkboxCapcha.click();
        webDriver.switchTo().parentFrame(); // quay lại iframe cha
        WebElement iframeCon2 = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//*[contains(@src,'https://www.google.com/recaptcha/api2/bframe')]")));
        webDriver.switchTo().frame(iframeCon2); // chuyển từ iframe cha đến một iframe con khác
        WebElement buttonXacMinh = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//*[@id='recaptcha-verify-button']")));
        buttonXacMinh.click();
        // "//button[@type='submit']"- xpath để tìm kiếm element có tagname là button
        // Giả sử có 2 element có xpath giống hệt nhau nhưng có 2 cha khác nhau có thể dùng thằng cha để tìm kiếm
        //*[@idCha='idcha']//*[@class='elenmentcon']
    }

}
