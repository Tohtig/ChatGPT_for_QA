package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private final SelenideElement emailField = $x("//div[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private final SelenideElement passwordField = $x("//div[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private final SelenideElement loginButton = $x(".//button[text()='Войти']");

    public LoginPage() {
        if (!this.isDisplayed()) {
            throw new IllegalStateException("This is not LoginPage");
        }
    }

    public MainPage login(final String email, final String password) {
        emailField.setValue(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new MainPage();
    }

    public boolean isDisplayed() {
        loginButton.shouldBe(Condition.visible);
        return loginButton.isDisplayed();
    }
}
