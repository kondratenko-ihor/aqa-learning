import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class testing2 {

    @Test(dataProvider = "loginData")
    public void TestLogin(String userName, String password) throws Exception {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.cssSelector("input[id='txtUsername']")).sendKeys(userName);
        driver.findElement(By.cssSelector("input[id='txtPassword']")).sendKeys(password);
        driver.findElement(By.cssSelector("input[id='btnLogin']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("span[id='spanMessage']")).isDisplayed());
        System.out.println("PASSED");
        //Assert.assertTrue(driver.findElement(By.id("welcome")).isDisplayed());
        Thread.sleep(5000);
        driver.quit();
    }

//        @DataProvider(parallel = true)
//    public Object[][] loginData() {
//        Object[][] data = new Object[3][2];
//        data[0][0] = "Admin12";
//        data[0][1] = "admin123";
//
//        data[1][0] = "Admin";
//        data[1][1] = "test123";
//
//        data[2][0] = "Test";
//        data[2][1] = "admin123";
//        return data;
//    }
    @DataProvider(parallel = true)
    public Object[][] loginData() {
        return new Object[][]{
            {"Admin", "admin1233"},
            {"Admin", "test123"},
            {"Test", "admin123"}
        };
    }

}
// data[3][0] = "Prod";
//         data[3][1] = "test123";
//
//         data[4][0] = "Stage";
//         data[4][1] = "admin123";
//
//         data[5][0] = "L6";
//         data[5][1] = "test123";