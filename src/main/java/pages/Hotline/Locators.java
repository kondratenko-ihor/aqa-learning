package pages.Hotline;

import org.openqa.selenium.By;

public class Locators {
    // JS
    public static final String JS_CLOSE_SUBSCRIPTION_DIALOG = "document.querySelector('.grv-dialog-host').shadowRoot.querySelector('.buttons-wrapper > :nth-child(1)').click();";

    public static class SearchField {
        public static final By SEARCH_FIELD = By.id("searchbox");
        public static final By SUGGESTIONS_ELEMENTS = By.cssSelector("#suggestion-results > a");
        public static final By SUGGESTION_ELEMENT_TITLE = By.cssSelector(".result__title");
        public static final By FIRST_ELEMENT_IN_SEARCH = By.cssSelector("#suggestion-results > :nth-child(1)");
    }
    public static class CategoryPage {
        public static final By ADVERT_PRODUCT_MIN_PRICE = By.cssSelector("[class=\"promo-list-item__value-price\"] :nth-child(1)");
        public static final By ADVERT_PRODUCT_MAX_PRICE = By.cssSelector("[class=\"promo-list-item__value-price\"] :nth-child(2)");
        public static final By ADVERT_ITEM = By.id("catalogAdvertProduct");
        public static final By ADVERT_MODEL_NAME_SECTION = By.cssSelector("#catalogAdvertProduct :nth-child(2) a");
        public static final By PRODUCT_PRICES_ELEMENTS = By.cssSelector("[class=\"list-body__content content flex-wrap\"] [class=\"list-item list-item--row\"] [class=\"price m_b-5\"] :nth-child(1)");
        public static final By LOADER = By.cssSelector("[class=\"catalog-list__preloader\"]");
        public static final By THIRD_PRODUCT_IN_LIST = By.cssSelector(":nth-child(3) [class=\"list-item__title-container m_b-5\"] a");
        public static final By PRODUCT_NAME_ELEMENTS = By.cssSelector("[class=\"list-item__title-container m_b-5\"] a");
        public static final By PAGE_PIKER_1 = By.cssSelector("[class=\"pagination__pages flex\"] a:nth-child(1)");
        public static final By PAGE_PIKER_2 = By.cssSelector("[class=\"pagination__pages flex\"] a:nth-child(2)");
    }
    public static final class Filter {
        public static final By PRICE_FILTER = By.cssSelector("[class=\"filter-price__range flex middle-xs evenly-xs\"] :nth-child(4)");
        public static final String PRICE_VALUE = "15000";
        public static final By BRAND_FILTER = By.cssSelector("[class=\"sidebar-filters__container\"] [class=\"sidebar-filters__item sidebar-filters__item--top\"]:nth-child(3)");
        public static final By BRAND_CHECKBOX = By.cssSelector("[class=\"sidebar-filters__container\"] [class=\"sidebar-filters__item sidebar-filters__item--top\"]:nth-child(3) [class=\"filter-checklist__item checkbox flex-inline middle-xs\"]:nth-child(7)");
        public static final By BRAND_NAME = By.cssSelector("[class=\"sidebar-filters__container\"] [class=\"sidebar-filters__item sidebar-filters__item--top\"]:nth-child(3) [class=\"filter-checklist__item checkbox flex-inline middle-xs\"]:nth-child(7) span:nth-child(1)");
    }

    public static class ProductDetailPage {
        public static final By FILTER_ON_DETAIL_PAGE = By.xpath("//span[contains(text(),\"Оцінка магазину\")]");
        public static final By PRICE_ON_DETAIL_PAGE = By.cssSelector(" [class=\"price__value\"]");
        public static final By RATING = By.cssSelector(" [class=\"shop__rating-value text-lg\"]");
        public static final By SHOP_NAME = By.cssSelector(" [class=\"shop__title mb-row\"]");
        public static final By STORE_LIST = By.cssSelector("[class=\"price content\"] .list [class=\"list__item row flex\"]");
    }
}
