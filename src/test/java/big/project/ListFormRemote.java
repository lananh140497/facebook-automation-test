package big.project;

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

import javax.xml.bind.Element;
import java.time.Year;
import java.util.*;

public class ListFormRemote {
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
        webDriver.get("https://edev.sun-asterisk.vn/en/dashboard/users/227/request_remotes");
        Thread.sleep(5000);
    }

    @After
    public void turnDown() throws InterruptedException {
        Thread.sleep(5000);
        webDriver.close();
        webDriver.quit();
    }

    //   //*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[1]//td[8]//*[@data-original-title = 'Edit']
    public int isElementExisted(String xpath) {
        try {
            WebElement element = webDriver.findElement(By.xpath(xpath));
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    public List<RemoteForm> createRemoteFormExpect() {
        List<RemoteForm> remoteFormListExpect = new ArrayList<>();
        RemoteForm remoteForm1 = new RemoteForm("B120402", "Nguyễn Mai Linh", "20:20 03-11-2023", "Date 03-21-2023, from 08:00 to 17:00", "At home: Ha Noi VN", "Pending", "Creative & Engineering Unit Vietnam 06(Vũ Trung Kiên, Trần Thị Ngọc Bích (SCFS), Nguyễn Văn Tân, thuy_test, thuy_test (khong sua_UM), hanh nt, hanh nguyễn 1, hanh 123, Trần Đức Minh)", 1, 1, 1);
        RemoteForm remoteForm2 = new RemoteForm("B120402", "Nguyễn Mai Linh", "20:13 03-11-2023", "Date 03-12-2023, from 08:00 to 17:00", "At home: Ha Noi VN", "Pending", "DuyenTest1(Lê Thị Mến, Hà Hữu Tín)", 1, 1, 1);
        RemoteForm remoteForm3 = new RemoteForm("B120402", "Nguyễn Mai Linh", "19:57 03-11-2023", "Date 03-10-2023, from 08:00 to 17:45", "Hà Đông", "Pending", "DuyenTest1(Lê Thị Mến, Hà Hữu Tín)", 1, 1, 1);
        RemoteForm remoteForm4 = new RemoteForm("B120402", "Nguyễn Mai Linh", "19:56 03-11-2023", "Date 03-09-2023, from 08:00 to 17:00", "At home: Ha Noi VN", "Pending", "DuyenTest1(Lê Thị Mến, Hà Hữu Tín)", 1, 1, 1);
        RemoteForm remoteForm5 = new RemoteForm("B120402", "Nguyễn Mai Linh", "19:36 03-11-2023", "Date 03-11-2023, from 08:00 to 17:00", "At home: Ha Noi VN", "Pending", "DuyenTest1(Lê Thị Mến, Hà Hữu Tín)", 1, 1, 1);
        RemoteForm remoteForm6 = new RemoteForm("B120402", "Nguyễn Mai Linh", "14:57 09-29-2022", "Date 10-01-2022, from 08:00 to 17:00\n" +
                "Date 10-02-2022, from 08:00 to 17:00\n" +
                "Date 10-03-2022, from 08:00 to 17:00\n" +
                "Date 10-04-2022, from 08:00 to 17:00\n" +
                "Date 10-05-2022, from 08:00 to 17:00\n" +
                "Date 10-06-2022, from 08:00 to 17:00\n" +
                "Date 10-07-2022, from 08:00 to 17:00\n" +
                "Date 10-08-2022, from 08:00 to 17:00\n" +
                "Date 10-09-2022, from 08:00 to 17:00\n" +
                "Date 10-10-2022, from 08:00 to 17:00\n" +
                "Date 10-11-2022, from 08:00 to 17:00\n" +
                "Date 10-12-2022, from 08:00 to 17:00", "At home: Ha Noi VN", "Approved", "DuyenTest1(Lê Thị Mến, Hà Hữu Tín)", 0, 1, 0);
        List<RemoteForm> remoteFormList = new ArrayList<>();
        remoteFormList.add(remoteForm1);
        remoteFormList.add(remoteForm2);
        remoteFormList.add(remoteForm3);
        remoteFormList.add(remoteForm4);
        remoteFormList.add(remoteForm5);
        remoteFormList.add(remoteForm6);
        return remoteFormList;
    }

    public void compareListRemoteForm(List<RemoteForm> remoteFormListExpect) {
        List<WebElement> webElementList = new ArrayList<>();
        webElementList = webDriver.findElements(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr"));
        System.out.println(webElementList.size());
        for (int i = 1; i <= webElementList.size(); i++) {
            for (int j = 0; j <= 8; j++) {
                if (j == 8) {
                    Assert.assertEquals(isElementExisted("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[8]//*[@data-original-title='Show']"), remoteFormListExpect.get(i - 1).getShowAction());
                    Assert.assertEquals(isElementExisted("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[8]//*[@data-original-title='Edit']"), remoteFormListExpect.get(i - 1).getEditAction());
                    Assert.assertEquals(isElementExisted("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[8]//*[@data-original-title='Delete']"), remoteFormListExpect.get(i - 1).getDeleteAction());

                } else {
                    switch (j) {
                        case 1:
                            WebElement staffCode = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[" + j + "]"));
                            Assert.assertEquals(remoteFormListExpect.get(i - 1).getStaffCode(), staffCode.getText());
                            break;
                        case 2:
                            WebElement staffName = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[" + j + "]"));
                            Assert.assertEquals(remoteFormListExpect.get(i - 1).getStaffName(), staffName.getText());
                            break;
                        case 3:
                            WebElement timeCreation = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[" + j + "]"));
                            Assert.assertEquals(remoteFormListExpect.get(i - 1).getTimeCreation(), timeCreation.getText());
                            break;
                        case 4:
                            WebElement timeRequest = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[" + j + "]"));
                            Assert.assertEquals(remoteFormListExpect.get(i - 1).getTimeRequest(), timeRequest.getText());
                            break;
                        case 5:
                            WebElement workplace = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[" + j + "]"));
                            Assert.assertEquals(remoteFormListExpect.get(i - 1).getWorkplace(), workplace.getText());
                            break;
                        case 6:
                            WebElement status = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[" + j + "]"));
                            Assert.assertEquals(remoteFormListExpect.get(i - 1).getStatus(), status.getText());
                            break;
                        case 7:
                            WebElement handledByStaff = webDriver.findElement(By.xpath("//*[@id='main-container']//div[2]//div[5]//div[1]//div[2]//div//table//tbody//tr[" + i + "]//td[" + j + "]"));
                            Assert.assertEquals(remoteFormListExpect.get(i - 1).getHandledByStaff(), handledByStaff.getText());
                            break;
                    }

                }
            }
        }

    }

    public List<RemoteForm> createRemoteFormSeachByMonthExpect(int month, int year) {
        List<RemoteForm> listRemoteFormALl = createRemoteFormExpect();
        List<RemoteForm> listRemoteFormResult = new ArrayList<>();
        String monthString = "";
        String yearString = "-" + year;
        if (month < 10) {
            monthString = 0 + month + "-";
        } else {
            monthString = month + "-";
        }
        for (RemoteForm form : listRemoteFormALl) {
            if (form.getTimeRequest().contains(monthString) && form.getTimeRequest().contains(yearString)) {
                listRemoteFormResult.add(form);
                System.out.println(form.getTimeRequest());
            }
        }
        return listRemoteFormResult;
    }

    public List<RemoteForm> createRemoteFormSeachByStatusExpect(String keyword) {
        List<RemoteForm> listRemoteFormALl = createRemoteFormExpect();
        List<RemoteForm> listRemoteFormResult = new ArrayList<>();
        for (RemoteForm form : listRemoteFormALl) {
            if (form.getStatus().equals(keyword)) {
                listRemoteFormResult.add(form);
            }
        }
        return listRemoteFormResult;
    }

    public void clickSearchBtn() {
        WebElement searchBtn = webDriver.findElement(By.xpath("//*[@id='main-container']/div[2]/div[4]/div/form/div/div/div[4]/button"));
        searchBtn.click();
    }

    public void selectStatus(String status) {
        WebElement statusStb = webDriver.findElement(By.id("q_status_eq"));
        Select select = new Select(statusStb);
        select.selectByVisibleText(status);
    }

    public void selectMonth(int month, int year) {
        WebElement monthPicker = webDriver.findElement(By.id("q_month"));
        monthPicker.click();
        int currentYear = Year.now().getValue();
        if (year == currentYear) {
            WebElement monthKeyWord = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[" + month + "]"));
            monthKeyWord.click();
        } else if (year < currentYear) {
            for (int i = 0; i < currentYear - year; i++) {
                WebElement previousYearIcon = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/table/thead/tr/th[1]"));
                previousYearIcon.click();
            }
            WebElement monthKeyWord = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[" + month + "]"));
            monthKeyWord.click();
        } else {
            for (int j = 0; j < year - currentYear; j++) {
                WebElement nextYearIcon = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/table/thead/tr/th[3]"));
                nextYearIcon.click();
            }
            WebElement monthKeyWord = webDriver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[" + month + "]"));
            monthKeyWord.click();

        }
    }

    public void searchByMonth(int month, int year) throws InterruptedException {
        selectMonth(month, year);
        clickSearchBtn();
        List<RemoteForm> formRemoteExpectResultList = createRemoteFormSeachByMonthExpect(month, year);
        compareListRemoteForm(formRemoteExpectResultList);
    }

    public void searchByStatus(String status) {
        selectStatus("Pending");
        clickSearchBtn();
        List<RemoteForm> formRemoteExpectResultList = createRemoteFormSeachByStatusExpect(status);
        compareListRemoteForm(formRemoteExpectResultList);
    }

    public List<RemoteForm> createRemoteFormByStatusAndMonthExpect(String status, int month, int year) {
        List<RemoteForm> listResultByMonth = createRemoteFormSeachByMonthExpect(month, year);
        if (listResultByMonth.size() == 0) {
            return listResultByMonth;
        } else {
            List<RemoteForm> listResultByStatusAndMonth = new ArrayList<>();
            for (RemoteForm form : listResultByMonth) {
                if (status.equals(form.getStatus())) {
                    listResultByStatusAndMonth.add(form);
                }
            }
            return listResultByStatusAndMonth;
        }
    }

    public void searchByStatusAndMonth(String status, int month, int year) {
        selectStatus(status);
        selectMonth(month, year);
        clickSearchBtn();
        List<RemoteForm> formRemoteExpectResultList = createRemoteFormByStatusAndMonthExpect(status, month, year);
        compareListRemoteForm(formRemoteExpectResultList);
    }

    @Test
    public void testListRemote() {
        List<RemoteForm> remoteFormListExpect = createRemoteFormExpect();
        compareListRemoteForm(remoteFormListExpect);
    }

    @Test
    public void searchByStatus() {
        searchByStatus("Pending");
    }

    @Test
    public void searchByMonth() throws InterruptedException {
        searchByMonth(10, 2022);
    }

    @Test
    public void searchByStatusAndMonth() {
        searchByStatusAndMonth("Pending", 3, 2023);
    }
}
