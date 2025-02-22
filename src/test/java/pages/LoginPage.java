package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userInputField;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInputField;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public HomePage login(WebDriver driver, String userName, String password) {
        userInputField.sendKeys(userName);
        passwordInputField.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public ArrayList<WebElement> getLoginElements() {
        ArrayList<WebElement> elements = new ArrayList<>();
        elements.add(this.userInputField);
        elements.add(this.userInputField);
        elements.add(this.loginButton);
        return elements;
    }
}
