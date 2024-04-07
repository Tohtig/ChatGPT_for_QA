package registration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class WebDriverParams {

    private void setupWebDriver() {
        /*  В Я.браузере должны отрабатывать 11 из 11 тестов. Включить BuilderSectionsTest */
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        /*  В хроме отрабатывают 10 из 11 тестов */
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15_000L;
    }

    @BeforeEach
    void init() {
        setupWebDriver();
    }

    @AfterEach
    void closeWebDriver() {
        Selenide.closeWebDriver();
    }
}
