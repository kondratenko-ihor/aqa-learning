package pages.hotline;

import org.openqa.selenium.*;
import org.testng.Assert;
import pages.basePage.BasePage;

import java.util.List;
import java.util.Locale;

public class MainPage extends BasePage {
    public static final By SEARCH_FIELD = By.id("searchbox");
    public static final By SUGGESTIONS_ELEMENTS = By.cssSelector("#suggestion-results > a");
    public static final By SUGGESTION_ELEMENT_TITLE = By.cssSelector(".result__title");
    public static final By FIRST_ELEMENT_IN_SEARCH = By.cssSelector("#suggestion-results > :nth-child(1)");




    public MainPage(WebDriver driver) {
        super(driver);
        driver.get(TestData.MAIN_PAGE_URL);
    }


    public void closeSubscriptionRequestIfAppears() {
        try {
            ((JavascriptExecutor) driver).executeScript(TestData.JS_CLOSE_SUBSCRIPTION_DIALOG);
        } catch (JavascriptException ignored) {
        }
    }

    public void checkPlaceholder() {
        waitClickableAndClick(SEARCH_FIELD);
        WebElement searchField = driver.findElement(SEARCH_FIELD);
        Assert.assertEquals(searchField.getAttribute(TestData.ATTRIBUTE_NAME), TestData.ATTRIBUTE_VALUE_EXPECTED, AssertErrorMessage.AUTOCOMPLETE_ON);
    }

    public void searchForItem() {
        driver.findElement(SEARCH_FIELD).sendKeys(TestData.SEARCH_WORD);
        waitForElementToBeClickable(SUGGESTIONS_ELEMENTS);
        List<WebElement> suggestionsList = driver.findElements(SUGGESTIONS_ELEMENTS);
        for (WebElement suggestion : suggestionsList) {
            Assert.assertTrue(suggestion.findElement(SUGGESTION_ELEMENT_TITLE).getText().toLowerCase(Locale.ROOT).contains(TestData.SEARCH_WORD_CYRILLIC) ||
                            suggestion.findElement(SUGGESTION_ELEMENT_TITLE).getText().toLowerCase(Locale.ROOT).contains(TestData.SEARCH_WORD_LATIN),
                    AssertErrorMessage.SEARCH_SUGGEST_ERROR);
        }
        waitForElementToBeClickable(FIRST_ELEMENT_IN_SEARCH);
        WebElement suggestionFirstElement = driver.findElement(FIRST_ELEMENT_IN_SEARCH);
        suggestionFirstElement.click();
    }

}


