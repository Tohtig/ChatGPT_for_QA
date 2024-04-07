package registration;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тесты конструктора")
@Feature("Конструктор")
@Story("Переходы к разделам")
@Link(name = "HOMEWORK-1183 Интеграция Allure Reports", type = "issue", url = "https://jira.autotests.cloud/browse/HOMEWORK-1183")
@Owner("Tohtig")
public class BuilderSectionsTest extends WebDriverParams {
    @Test
    @Disabled
    @Severity(SeverityLevel.CRITICAL)
    @Description("Описание теста: Работают переходы к разделам - Булки, Соусы, Начинки")
    @DisplayName("Раздел 'Конструктор'. Работают переходы к разделам - Булки, Соусы, Начинки")
    public void jumpToIngredientsSection() {
        open(MainPage.URL);
        MainPage mainPage = new MainPage();
        mainPage.clickFillingHeader();
        assertTrue(mainPage.fillingSectionSelected());
        mainPage.clickSauceHeader();
        assertTrue(mainPage.sauceSectionSelected());
        mainPage.clickBunHeader();
        assertTrue(mainPage.bunSectionSelected());
    }
}
