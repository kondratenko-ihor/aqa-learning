package pages.airSlate;

import org.openqa.selenium.By;

public class Locators {
    public static final class DocumentManager {
        public static final String IFRAME = "#editor-iframe-web-form";
        public static final By I_CONSENT_BUTTON = By.cssSelector("[aria-label='Give your consent to do business electronically']");
        public static final String INPUT_VALID_DATA = "!@#$%^&AH";
        public static final String INPUT_INVALID_DATA = "!@#$%^&Adfkdlk307234022034H";
        public static final By SINGLE_LINE_FIRST = By.cssSelector("[placeholder='first_single_line']");
        public static final By NUMBER_FIELD = By.cssSelector("[data-type='number'] div:nth-child(2)");
        public static final By VALID_SINGLE_LINE = By.xpath("//input[@placeholder='Enter your answer here']");
        public static final By COMPLETE_BUTTON = By.cssSelector("[aria-label='Complete']");
        public static final By LINK_IN_DOC = By.cssSelector("[class*='HtmlContainer'] p a");
        public static final By VALIDATION_MESSAGE = By.cssSelector("div [class*='ValidationMessage']");
    }

    public static final class Login {
        public static final By LOGIN_EMAIL_INPUT = By.cssSelector("[data-qa-tag='user-email-input']");
        public static final By LOGIN_PASSWORD_INPUT = By.cssSelector("[data-qa-tag='user-password-input']");
        public static final String USER_EMAIL = "kondratenko.ihor@airslate.com";
        public static final String PASSWORD = "Qwerty123!";
        public static final By LOGIN_BUTTON = By.cssSelector("[type='submit']");
    }

    public static final class DomainSelect {
        public static final String DOMAIN_PATH = "/domain-select";
        public static final By SIDEBAR = By.cssSelector("[data-qa-tag='navBarprimary']");
        public static final By PROFILE_CIRCLE = By.cssSelector("[class='thumb thumb--circle']");
        public static final By MY_ACCOUNT_BUTTON = By.cssSelector("[data-qa-tag='btnUserAvatar']");
    }

    public static final class ProfilePage {
        public static final String IMAGE_PATH = "src/main/java/pages/airSlate/test_image.jpeg";
        public static final String PERSONAL_INFORMATION_URL = "https://my.airslate.com/account/personal-information";
        public static final By CHANGE_ICON_BUTTON = By.cssSelector("[class='photo-uploader__action-btn'] button");
        public static final By SELECT_IMAGE_BUTTON = By.cssSelector("[accept='image/jpeg,image/png,image/gif']");
        public static final By TAKE_PICTURE_BUTTON = By.cssSelector("[class='drag-and-drop__action-item']:nth-child(2)");
        public static final By NOTIFICATION = By.cssSelector("[class='notifications__item notifications__item--success is-show']");
        public static final By SAVE_AVATAR_BUTTON = By.cssSelector("[class='button button--sm button--primary']");
        public static final By SAVE_IMAGE = By.cssSelector("[class='button button--sm button--primary']");
    }

    public static final class ThankYouPage {
        public static final String THANK_YOU_PAGE_PATH = "/thank-you";
        public static final By TRY_AIR_SLATE_BUTTON = By.cssSelector("[aria-label='Try airSlate']");
        public static final By THANK_YOU_PAGE_TITLE = By.cssSelector("[class='headline-h3']");


    }

    static class SigningConfigSection {
//        public static final By SIGNATURE_ICON = By.cssSelector("[class*='EmptySignatureView']");
        public static final By SIGNATURE_ICON = By.cssSelector("[data-qa-tag='signature-wrap']");
        public static final By CAMERA_CAPTURE = By.cssSelector("[data-test='optionCapture']");
        public static final By SAVE = By.cssSelector("[data-test='saveAndUse']");
    }

}
