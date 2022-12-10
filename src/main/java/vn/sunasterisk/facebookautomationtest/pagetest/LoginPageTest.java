package vn.sunasterisk.facebookautomationtest.pagetest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class LoginPageTest {
    public void loginFacebook(String email, String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.facebook.com/");
        WebElement emailElement = webDriver.findElement(By.xpath("//*[@id= 'email']"));
        emailElement.sendKeys(email);
        WebElement passwordElement = webDriver.findElement(By.xpath("//*[@id='pass']"));
        passwordElement.sendKeys(password);
        WebElement buttonLogin = webDriver.findElement(By.xpath("//*[@type='submit']"));
        buttonLogin.click();
        String url = "https://www.facebook.com/?sk=welcome";
        String currentUrl = webDriver.getCurrentUrl();
        if (url.equals(currentUrl)){
            System.out.println("["+email+"] - ["+password+"]: Login thành công");
        } else {
            WebElement mgsElement = webDriver.findElement(By.xpath("//*[@class='_9ay7']"));
            System.out.println("["+email+"] - ["+password+"]: "+ mgsElement.getText());
        }
        Thread.sleep(2000);
        webDriver.close();
        webDriver.quit();
    }

}
