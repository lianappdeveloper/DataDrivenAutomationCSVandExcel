import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class LoginDataProviderExcelTest {
    @DataProvider (name = "user-ids-passwords-excel-data-provider")
    public Object[][] userIdsAndPasswordDataProvider(){
        return ExcelReadUtil.readExcelInto2DArray(".\\src\\test\\Login-Data.xlsx", "Sheet1", 2);
    }

    @Test (dataProvider = "user-ids-passwords-excel-data-provider")
    public void testLogin(String userId, String password){
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
    @Test
    public void readFromExcel(){
        String[][] data = ExcelReadUtil.readExcelInto2DArray(".\\src\\test\\Login-Data.xlsx", "Sheet1", 2);
        System.out.println(Arrays.deepToString(data));

    }
}
