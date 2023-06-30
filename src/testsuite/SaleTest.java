package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SaleTest extends BaseTest {
    String baseUrl = "https://magento.softwaretestingboard.com/";
    public static String emailId;

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyTheTotalItemsDisplayedOnTheWomenJacketsPage() throws InterruptedException {
        RegisterTest registerTest = new RegisterTest();
        registerTest.userShouldRegisterAccountSuccessfully();
        emailId = registerTest.email;

        // Click on ‘Sign In’ link
        driver.findElement(By.xpath("//a[contains(text(),'Sign ')]")).click();
        // Enter valid Email
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(emailId);
        // Enter valid Password
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Peeter123");
        // Click on ‘Sign In’ button
        driver.findElement(By.xpath("//button[@class='action login primary']")).click();
        // Click on ‘Sale’ Menu tab
        driver.findElement(By.xpath("//a[contains(@id,'ui-id-8')]")).click();
        //  Click on ‘Jackets’ link on left side under WOMEN’S DEAL Category
        driver.findElement(By.xpath("(//a[text()='Jackets'])[1]")).click();
        String actualMsg = "Jackets";
        String expectedMsg = driver.findElement(By.xpath("(//span[text()='Jackets'])[3]")).getText();
        Assert.assertEquals("Error Message", expectedMsg, actualMsg);
        // Count the Total Item Displayed on Page and print the name of all items into console.
        List<WebElement> product = driver.findElements(By.xpath("//*[@class='product name product-item-name']"));
        System.out.println("Total item on page is :- " + (product.size()));
        Thread.sleep(1000);
        for (WebElement webElement : product) {
            String name = webElement.getText();
            System.out.println(name);
        }
        Assert.assertEquals(12, product.size());
    }

    @After
    public void closeBrowser() {
        driver.close();
    }
}
