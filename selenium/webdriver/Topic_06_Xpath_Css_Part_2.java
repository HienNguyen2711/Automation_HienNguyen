package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_06_Xpath_Css_Part_2 {
    WebDriver driver;
    Integer random = new Random().nextInt();
    String random_email = "HienNguyen" + random + "@gmail.com";
    String password = "123456";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @Test
    public void Login_01_Empty_Email_And_Password() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        //Empty email
        driver.findElement(By.id("email")).sendKeys();

        //Empty password
        driver.findElement(By.name("login[password]")).sendKeys();

        //Click Login button
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),"This is a required field.");
    }

    @Test
    public void Login_2_Invalid_Email() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("123@123");
        driver.findElement(By.name("login[password]")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void Login_3_Invalid_Password() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmai.com");
        driver.findElement(By.name("login[password]")).sendKeys("12345");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void Login_4_Invalid_Email_Password() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("automation@gmai.com");
        driver.findElement(By.name("login[password]")).sendKeys("123123123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
    }

    @Test
    public void Login_5_Create_Acc_Success() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.id("firstname")).sendKeys("Hien");
        driver.findElement(By.id("middlename")).sendKeys("Quang");
        driver.findElement(By.id("lastname")).sendKeys("Nguyen");
        driver.findElement(By.id("email_address")).sendKeys(random_email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register' and @type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText(),"MY DASHBOARD");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='welcome-msg']//p[@class='hello']")).getText().matches("Hello, Hien Quang Nguyen!"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Hien Quang Nguyen')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-account box-info']//p[contains(.,'"+random_email+"')]")).isDisplayed());
        driver.findElement((By.xpath("//a[contains(@class,'skip-account')]"))).click();
        driver.findElement(By.xpath("//div[@id='header-account']//li[contains(@class,'last')]/a[@title='Log Out']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='header-language-container']//p[contains(text(),'Default welcome msg!')]")).isDisplayed());
    }

    @Test
    public void Login_6_Login_with_valid_acc() {
        driver.get("http://live.demoguru99.com/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys(random_email);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.xpath("//button[@id='send2'and @title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText(),"MY DASHBOARD");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='welcome-msg']//p[@class='hello']")).getText().matches("Hello, Hien Quang Nguyen!"));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Hien Quang Nguyen')]")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-account box-info']//p[contains(.,'"+random_email+"')]")).isDisplayed());

    }

    @Test
    public void TC_07_CSS() {
        }

    @Test
    public void TC_08_XPATH() {
        }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}