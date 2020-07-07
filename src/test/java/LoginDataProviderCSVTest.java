import com.opencsv.CSVReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LoginDataProviderCSVTest {
    @DataProvider (name = "user-ids-passwords-csv-data-provider")
    public Iterator<String[]> userIdsAndPasswordDataCSVProvider(){
    return readFromCSVFile(".\\src\\test\\login-data.csv").iterator();
    }

    @Test (dataProvider = "user-ids-passwords-csv-data-provider")
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
    public void TestReadingDataFromCSV() throws IOException {
        List<String[]> data = readFromCSVFile(".\\src\\test\\login-data.csv");
        for (String[] row:data){
            System.out.println(Arrays.toString(row));
        }
    }

    private List<String[]> readFromCSVFile(String csvFilePath){
        try{
        CSVReader reader = new CSVReader(new FileReader(csvFilePath));
        List<String[]> data = reader.readAll();
        return data;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
