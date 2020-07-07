import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UnSuccessfulLoginDataDrivenWithPasswordTest {
    @DataProvider (name = "user-ids-passwords-data-provider")
    public Object[][] userIdsAndPasswordDataProvider(){
        return new String[][]{
        {"tomsmith","SuperSecretPassword!"},
        {"Adam","1234"},
        {"Lian","MostAmazingPassword"}};
    }

    @Test (dataProvider = "user-ids-passwords-data-provider")
    public void UnSuccessfulLoginTest(String userId, String password){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys(userId);
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(password);
        passwordElement.submit();

        String errorMessageText = driver.findElement(By.id("flash")).getText();
        System.out.println(errorMessageText);
        driver.quit();


    }
}
