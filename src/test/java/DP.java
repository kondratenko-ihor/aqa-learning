import film.miromax.WebDriverConfig;
import film.miromax.test.Locators;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import film.miromax.test.Locators.*;


public class DP  {

    @DataProvider(name = "bank")
    public Object[][] urlsMethod() {
        return new Object[][]{
                {"https://my.ukrsibbank.com/ua/personal", "Укрсиббанк", "locatorUkr", },
                {"https://www.universalbank.com.ua/", "ЮниверсалБанк","locatorUniver" },
                {"https://www.oschadbank.ua" ,"Ощадбанк", "locaOschad"}
        };
    }

    @Test(dataProvider = "bank")
    public void myTest(String url, String name, String locator) {
        System.out.println("urls is: " + url);
        System.out.println("name is: " + name);
        System.out.println("locator is: " + locator);
        System.out.println(locator);

    }
}

