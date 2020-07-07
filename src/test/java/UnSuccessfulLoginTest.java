import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static org.junit.Assert.*;

public class UnSuccessfulLoginTest {

    @Test
    public void UnSuccessfulLoginTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("ReallyBadPassword");
        password.submit();

        String errorMessageText = driver.findElement(By.id("flash")).getText();
        System.out.println(errorMessageText);
        assertEquals(errorMessageText, "Your password is invalid!\n" +
                "×");
        driver.quit();


    }
}
