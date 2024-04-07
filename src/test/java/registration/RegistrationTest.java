package registration;

import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import service.APIServices;
import com.github.javafaker.Faker;
import model.UserAccount;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.RegistrationPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Тесты регистрации")
@Feature("Регистрация")
@Story("Регистрация нового пользователя")
@Link(name = "Связанная задача", type = "issue", url = "https://github.com/your-repo/your-project/issues/1")
@Owner("Tohtig")
@Tag("automated")
public class RegistrationTest extends WebDriverParams {
    private final Faker faker = new Faker(new Locale("en"));
    private final APIServices apiServices = new APIServices();
    private final List<UserAccount> testData = new ArrayList<>();
    private UserAccount account;

    @AfterEach
    public void cleanUp() {
        apiServices.deleteAccounts(testData);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Описание теста: Успешная регистрация нового пользователя")
    @DisplayName("Успешная регистрация")
    public void successRegistration() {
        account = new UserAccount().
                setEmail(faker.internet().emailAddress()).
                setPassword(faker.internet().password()).
                setName(faker.name().firstName());
        testData.add(account);
        open(RegistrationPage.URL);
        RegistrationPage regPage = new RegistrationPage();
        LoginPage loginPage = regPage.registrationAsNewClient(account.getName(), account.getEmail(), account.getPassword());
        assertTrue(loginPage.isDisplayed());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Описание теста: Проверка ошибки для некорректного пароля. Минимальный пароль шесть символов")
    @DisplayName("Проверка ошибки для некорректного пароля. Минимальный пароль шесть символов")
    public void registrationFiveCharPassReject() {
        account = new UserAccount().
                setEmail(faker.internet().emailAddress()).
                setPassword(RandomStringUtils.randomAlphanumeric(5)).
                setName(faker.name().firstName());
        testData.add(account);
        open(RegistrationPage.URL);
        RegistrationPage regPage = new RegistrationPage();
        regPage.registrationUnsuccess(account.getName(), account.getEmail(), account.getPassword());
        assertTrue(regPage.errorPasswordIsDisplayed());
        assertTrue(regPage.isDisplayed());
    }
}
