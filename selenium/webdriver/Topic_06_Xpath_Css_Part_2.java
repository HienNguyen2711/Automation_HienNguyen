package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_Xpath_Css_Part_2 {
    WebDriver driver;

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
    public void TC_05_LinkText() {
        }

    @Test
    public void TC_06_PartialLinkText() {
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