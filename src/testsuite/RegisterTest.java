package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.UUID;

public class RegisterTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";

    String email;


    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyThatSignInPageDisplay() {
        // click on the ‘Create an Account’ link
        driver.findElement(By.xpath("//a[contains(text(),'Create an Account')]")).click();
        // Verify the text ‘Create New Customer Account’
        String actualMessage = "Create New Customer Account";
        String expectedMessage = driver.findElement(By.xpath("//h1//span")).getText();
        Assert.assertEquals("Error Message is not Displayed", expectedMessage, actualMessage);
    }

    @Test
    public void userShouldRegisterAccountSuccessfully() throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
        email = "admin" + uuid + "@gmail.com";
        //click on the ‘Create an Account’ link
        driver.findElement(By.xpath("//a[contains(text(),'Create an Account')]")).click();
        // Enter First name
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Joy");
        // Enter Last name
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Petter");
        // Click on checkbox Sign Up for Newsletter
        driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
        // Enter Email
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        // Enter Password
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Peeter123");
        // Enter Confirm Password
        driver.findElement(By.xpath("//input[@id='password-confirmation']")).sendKeys("Peeter123");
        // Click on Create an Account button

        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();

        // Verify the text 'Thank you for registering with Main Website Store.’
        String actualMsg = "Thank you for registering with Main Website Store.";
        String expectedMsg = driver.findElement(By.xpath("//div[contains(text(),'registering ')]")).getText();
        Assert.assertEquals("Error Message", expectedMsg, actualMsg);



    }

    @After
    public void closeBrowser() {
        driver.close();
    }
}
