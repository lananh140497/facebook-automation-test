package big.project;

import javafx.scene.input.DataFormat;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateFormRemote {
    private WebDriver webDriver;

    @Before
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://edev.sun-asterisk.vn");
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 5);
        WebElement logInBtn = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='wsm-btn btn-login']")));
        logInBtn.click();
        WebElement emailTxt = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='user_email']")));
        emailTxt.sendKeys("nguyen.mai.linh@framgia.com.edev.test");
        WebElement passwordTxt = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='user_password']")));
        passwordTxt.sendKeys("123456");
        WebElement oKBtn = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wsm-login-button']")));
        oKBtn.click();
        Thread.sleep(10000);
        webDriver.get("https://edev.sun-asterisk.vn/en/dashboard/users/227/request_remotes/new");
        Thread.sleep(5000);
    }

    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
        webDriver.quit();
    }

    public int isElementExisted(String xpath) {
        try {
            WebElement element = webDriver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public String selectWorKingMode(int selectAtHome, int selectOtherAddress, String homeAddress, int selectOtherPlace, String otherPlace) {
        String worKingMode = "";
        if (selectAtHome == 1) {
            WebElement atHomeBtn = webDriver.findElement(By.id("request_remote_is_at_home_true"));
            atHomeBtn.click();
            WebElement atHomeSlt = webDriver.findElement(By.id("select-address"));
            Select select = new Select(atHomeSlt);
            if (selectOtherAddress == 0) {
                select.selectByVisibleText(homeAddress);
            } else {
                select.selectByVisibleText("Other Address");
                WebElement otherAddressTxt = webDriver.findElement(By.id("workplace"));
                otherAddressTxt.sendKeys(homeAddress);
            }
            worKingMode = worKingMode + "At home: " + homeAddress;
            return worKingMode;
        }
        if (selectOtherAddress == 1) {
            WebElement atHomeBtn = webDriver.findElement(By.id("request_remote_is_at_home_false"));
            atHomeBtn.click();
            WebElement otherAddressTxt = webDriver.findElement(By.id("OtherAddress"));
            otherAddressTxt.sendKeys(homeAddress);
            worKingMode = worKingMode + homeAddress;
            return worKingMode;
        }

        return worKingMode;
    }

    public String changeMMDD(String date) {
        String[] dateArr = date.split("-");
        String str = dateArr[0];
        dateArr[0] = dateArr[1];
        dateArr[1] = str;
        return dateArr[0] + "-" + dateArr[1] + "-" + dateArr[2];
    }

    public String inputTimeRequest(List<TimeRequest> listTimeRequest) {
        String timeRequest = "";
        for (int i = 0; i < listTimeRequest.size(); i++) {
            WebElement date = webDriver.findElement(By.xpath("//*[@id='workingDate" + i + "']/div[1]/input"));
            date.clear();
            date.sendKeys(listTimeRequest.get(i).getDate());
            WebElement timeCheckIn = webDriver.findElement(By.xpath("//*[@id='workingDate" + i + "']/div[2]/input"));
            timeCheckIn.clear();
            timeCheckIn.sendKeys(listTimeRequest.get(i).getTimeCheckin());
            WebElement timeCheckOut = webDriver.findElement(By.xpath("//*[@id='workingDate" + i + "']/div[3]/input"));
            timeCheckOut.clear();
            timeCheckOut.sendKeys(listTimeRequest.get(i).getTimeCheckout());
            if (listTimeRequest.size() - 1 != i) {
                WebElement addWorkingDate = webDriver.findElement(By.xpath("//*[@class='add-user-workspace-btn user-workspace-link addWorkingDate']"));
                addWorkingDate.click();
                timeRequest = timeRequest + "Date " + changeMMDD(listTimeRequest.get(i).getDate()) + ", from " + listTimeRequest.get(i).getTimeCheckin() + " to " + listTimeRequest.get(i).getTimeCheckout() + "\n";
            } else {
                timeRequest = timeRequest + "Date " + changeMMDD(listTimeRequest.get(i).getDate()) + ", from " + listTimeRequest.get(i).getTimeCheckin() + " to " + listTimeRequest.get(i).getTimeCheckout();
            }
        }
        return timeRequest;
    }

    public void compareRemoteFormNew(RemoteForm remoteForm) {
        WebElement staffCode = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[1]"));
        Assert.assertEquals(remoteForm.getStaffCode(), staffCode.getText());
        WebElement staffName = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[2]"));
        Assert.assertEquals(remoteForm.getStaffName(), staffName.getText());
        WebElement timeCreation = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[3]"));
        // Assert.assertEquals(remoteForm.getTimeCreation(),timeCreation.getText());
        WebElement timeRequest = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[4]"));
        Assert.assertEquals(remoteForm.getTimeRequest(), timeRequest.getText());
        WebElement workplace = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[5]"));
        Assert.assertEquals(remoteForm.getWorkplace(), workplace.getText());
        WebElement status = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[6]"));
        Assert.assertEquals(remoteForm.getStatus(), status.getText());
        WebElement handledByStaff = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[7]"));
        Assert.assertEquals(remoteForm.getHandledByStaff(), handledByStaff.getText());
        Assert.assertEquals(isElementExisted("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[8]//*[@data-original-title='Show']"), remoteForm.getShowAction());
        Assert.assertEquals(isElementExisted("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[8]//*[@data-original-title='Edit']"), remoteForm.getEditAction());
        Assert.assertEquals(isElementExisted("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[8]//*[@data-original-title='Delete']"), remoteForm.getDeleteAction());

    }

    public void addRemoteFormSuccess(int selectAtHome, int selectOtherAddress, String homeAddress, int selectOtherPlace, String otherPlace, List<TimeRequest> listTimeRequest) throws InterruptedException {
        String workPlace = selectWorKingMode(selectAtHome, selectOtherAddress, homeAddress, selectOtherPlace, otherPlace);
        String timeRequestStr = inputTimeRequest(listTimeRequest);
        WebElement saveBtn = webDriver.findElement(By.xpath("//*[@id='new_request_remote']/input[3]"));
        saveBtn.click();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm MM-dd-yyyy");
        String currenDate = now.format(formatter);
        System.out.println(currenDate);
        Thread.sleep(10000);
        RemoteForm remoteForm = new RemoteForm("B120402", "Nguyễn Mai Linh", currenDate, timeRequestStr, workPlace, "Pending", "DuyenTest1(Lê Thị Mến, Hà Hữu Tín)", 1, 1, 1);
        compareRemoteFormNew(remoteForm);
    }

    @Test
    public void testAddRemoteFormSuccess() throws InterruptedException {
        TimeRequest timeRequest1 = new TimeRequest("01-03-2023", "08:00", "17:00");
        TimeRequest timeRequest2 = new TimeRequest("02-03-2023", "08:00", "17:00");
        TimeRequest timeRequest3 = new TimeRequest("03-03-2023", "08:00", "17:00");
        List<TimeRequest> timeRequestList = new ArrayList<>();
        timeRequestList.add(timeRequest1);
        timeRequestList.add(timeRequest2);
        timeRequestList.add(timeRequest3);
        addRemoteFormSuccess(1, 1, "Keangnam", 0, "", timeRequestList);
    }

}
