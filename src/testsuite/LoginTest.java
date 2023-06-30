package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";
    public static String emailId;

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userShouldLoginSuccessfullyWithValidCredentials() throws InterruptedException {
        RegisterTest registerTest = new RegisterTest();
        registerTest.userShouldRegisterAccountSuccessfully();
        emailId = registerTest.email;
        Thread.sleep(1000);

        // Click on ‘Sign In’ link
        driver.findElement(By.xpath("//a[contains(text(),'Sign ')]")).click();
        // Enter valid Email
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailId);
        // Enter valid Password
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Peeter123");
        // Click on ‘Sign In’ button
        driver.findElement(By.xpath("//button[@class='action login primary']")).click();
        // Verify the ‘Welcome’ text is display
        String actualMsg = "Welcome, Joy Petter!";
        Thread.sleep(1000);
        String expectedMsg = driver.findElement(By.xpath("(//span[@class='logged-in'])[1]")).getText();
        Assert.assertEquals("Error Message", expectedMsg, actualMsg);
    }

    @Test
    public void verifyTheErrorMessageWithInvalidCredentials() throws InterruptedException {
        // Click on ‘Sign In’ link
        driver.findElement(By.xpath("//a[contains(text(),'Sign ')]")).click();
        // Enter invalid Email
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("joy123a@gmail.com");
        // Enter invalid Password
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Peeter123");
        // Click on ‘Sign In’ button
        driver.findElement(By.xpath("//button[@class='action login primary']")).click();
        Thread.sleep(2000);
        // Verify the error message ‘The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.’
        String actualMsg = "The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.";
        String expectedMsg = driver.findElement(By.xpath("//*[@class='message-error error message']")).getText();
        Assert.assertEquals("Error Message", expectedMsg, actualMsg);


    }

    @Test
    public void userShouldLogOutSuccessfully() throws InterruptedException {
//        RegisterTest registerTest = new RegisterTest();
//        String emailId = registerTest.email;
        // Click on ‘Sign In’ link
        driver.findElement(By.xpath("//a[contains(text(),'Sign ')]")).click();
        // Enter valid Email
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailId);
        // Enter valid Password
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Peeter123");
        // Click on ‘Sign In’ button
        driver.findElement(By.xpath("//button[@class='action login primary']")).click();
        // Verify the ‘Welcome’ text is display
        String actualMsg = "Welcome, Joy Petter!";
        Thread.sleep(1000);
        String expectedMsg = driver.findElement(By.xpath("(//span[@class='logged-in'])[1]")).getText();
        Assert.assertEquals("Error Message", expectedMsg, actualMsg);
        // Click on down aero near Welcome
        driver.findElement(By.xpath("//button[@class='action switch']")).click();
        // Click on Sign Out link
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[contains(text(),'Out ')]")).click();

        // Verify the text ‘You are signed out’
        String actualMsg1 = "You are signed out";
        String expectedMsg1 = driver.findElement(By.xpath("//h1//span")).getText();
        Assert.assertEquals("", expectedMsg1, actualMsg1);

    }

    @After
    public void closeBrowser() {
        driver.close();
    }

}
