package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewPersonalDetailsPage extends PIMPage{

    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']" +
            "//div[label[contains(text(), 'Birth')]]/..//div[@class='oxd-date-input']")
    private WebElement dateOfBirth;
    @FindBy(xpath = "//input[@type='radio' and @value = '1']")
    private WebElement maleGender;
    @FindBy(xpath = "//input[@type='radio' and @value = '2']")
    private WebElement femaleGender;
    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-content']" +
            "//p[@class='oxd-text oxd-text--p orangehrm-form-hint']" +
            "/..//button[@type='submit']")
    private WebElement personalDetailsSubmitButton;
    @FindBy(xpath = "//input[@class='oxd-input oxd-input--active orangehrm-firstname']")
    private WebElement firstNameInput;
    @FindBy(xpath = "//input[@class='oxd-input oxd-input--focus orangehrm-firstname']")
    private WebElement firstNameInputFocused;
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
        if (genderType.equalsIgnoreCase("M")) {
            this.maleGender.click();
        } else this.femaleGender.click();
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.sendKeys(dateOfBirth);
    }

    public void setFirstName(String firstName) {
        this.firstNameInput.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        this.firstNameInputFocused.sendKeys(firstName);
    }

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
