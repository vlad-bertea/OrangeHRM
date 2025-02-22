package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ViewPersonalDetailsPage extends PIMPage{

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']" +
            "//div[label[contains(text(), 'Birth')]]/..//div[@class='oxd-date-input']")
    private WebElement dateOfBirth;
    @FindBy(xpath = "//input[@class='oxd-input oxd-input--focus']")
    private WebElement focusedDateOfBirth;
    @FindBy(xpath = "//input[@type='radio' and @value = '1']" +
            "/..//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']")
    private WebElement maleGender;
    @FindBy(xpath = "//input[@type='radio' and @value = '2']" +
            "/..//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input']")
    private WebElement femaleGender;
    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']" +
            "//p[@class='oxd-text oxd-text--p orangehrm-form-hint']" +
            "/..//button[@type='submit']")
    private WebElement personalDetailsSubmitButton;
    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active orangehrm-firstname']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active orangehrm-middlename']")
    private WebElement middleNameInput;
    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active orangehrm-lastname']")
    private WebElement lastNameInput;
    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-name']//h6")
    private WebElement fullName;


    public ViewPersonalDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void personalDetailsSubmit() {
        this.personalDetailsSubmitButton.click();
    }

    public WebElement getPersonalDetailsSubmitButton() {
        return this.personalDetailsSubmitButton;
    }

    public void setGender(String genderType) {
        if (genderType.startsWith("F")) {
            this.femaleGender.click();
        } else this.maleGender.click();
    }

    public void setDateOfBirth(WebDriver driver, String dateOfBirth) {
        wait.until(ExpectedConditions.visibilityOf(this.dateOfBirth));
        Actions action = new Actions(driver);
        action.moveToElement(this.dateOfBirth);
        this.dateOfBirth.click();
        this.focusedDateOfBirth.sendKeys(dateOfBirth);
    }

    public void setFirstName(WebDriver driver, String firstName) {
        this.firstNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.switchTo().activeElement().sendKeys(firstName);    }

    public WebElement getMiddleNameInput() {
        return this.middleNameInput;
    }

    public WebElement getFullNameWebElement() {
        return this.fullName;
    }

    public String getFullNameValue() {
        return this.fullName.getText();
    }
}
