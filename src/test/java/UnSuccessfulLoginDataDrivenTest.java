import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

public class UnSuccessfulLoginDataDrivenTest {
    @DataProvider (name = "user-ids-data-provider")
    public Object[] userIdsDataProvider(){
        return new String[]{"tomsmith","Adam","Lian"};
    }

    @Test (dataProvider = "user-ids-data-provider")
    public void UnSuccessfulLoginTest(String userId){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys(userId);
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("SuperSecretPassword!");
        password.submit();

        String errorMessageText = driver.findElement(By.id("flash")).getText();
        System.out.println(errorMessageText);
        driver.quit();


    }
}
