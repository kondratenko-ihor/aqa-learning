package WebDriverConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;


public class WebDriverConfig {
    public ChromeDriver driver;

    @BeforeTest
    public void setDriver() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Integer> cameraSetting = new HashMap<>();
        HashMap<String, Object> profile = new HashMap<>();
        HashMap<String, Object> prefs = new HashMap<>();

        cameraSetting.put("media_stream", 1);
        cameraSetting.put("geolocation", 2);
        profile.put("managed_default_content_settings", cameraSetting);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("disable-notifications");
//      options.addArguments("headless");
//      options.addArguments("use-fake-ui-for-media-stream");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
