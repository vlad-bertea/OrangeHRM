package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddEmployeePage extends PIMPage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@name='middleName']")
    private WebElement middleName;
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastName;
    @FindBy(xpath = "//div[@class='orangehrm-card-container']//input[@class='oxd-input oxd-input--active']")
    private WebElement employeeId;
    @FindBy(xpath = "//div[@class='orangehrm-card-container']//input[@class='oxd-input oxd-input--focus']")
    private WebElement focusedEmployeeId;
    @FindBy(xpath = "//div[@class='orangehrm-card-container']")
    private WebElement addEmployeeForm;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void typeFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
    }

    public void typeMiddleName(String middleName) {
        this.middleName.sendKeys(middleName);
    }

    public void typeLastName(String lastName) {
        this.lastName.sendKeys(lastName);
    }

    public void typeEmployeeId(String employeeId) {
        this.employeeId.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        this.focusedEmployeeId.sendKeys(employeeId);
    }

    public ViewPersonalDetailsPage saveEmployee(WebDriver driver) {
        this.submitButton.click();
        return new ViewPersonalDetailsPage(driver);
    }

    public WebElement getAddEmployeeForm() {
        return this.addEmployeeForm;
    }
}
