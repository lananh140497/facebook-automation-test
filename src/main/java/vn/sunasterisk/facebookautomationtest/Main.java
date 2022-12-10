package vn.sunasterisk.facebookautomationtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import vn.sunasterisk.facebookautomationtest.pagetest.LoginPageTest;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LoginPageTest loginPageTest = new LoginPageTest();
        loginPageTest.loginFacebook("100088268452149","Aa@123456");
        loginPageTest.loginFacebook("100088268452149","ABC123@@");
        loginPageTest.loginFacebook("1000999999999999","ABC123@@");
    }
}
