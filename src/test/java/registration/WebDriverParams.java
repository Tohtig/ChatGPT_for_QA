package registration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.qameta.allure.selenide.AllureSelenide;
import helpers.Attach;

import java.util.Map;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebDriverParams {

    private void setupWebDriver() {
        /*  В Я.браузере должны отрабатывать 11 из 11 тестов. Включить BuilderSectionsTest */
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
        /*  В хроме отрабатывают 10 из 11 тестов */
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15_000L;

//        Подключение селенойда
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeEach
    void init() {
        setupWebDriver();
    }

    @AfterEach
    void addAttachments() {
        if (WebDriverRunner.getWebDriver() != null) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
            Attach.addVideo();
            closeWebDriver();
        }
    }
}
