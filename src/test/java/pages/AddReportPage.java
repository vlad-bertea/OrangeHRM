package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddReportPage extends PIMPage {

    @FindBy(xpath = "//div[@class='orangehrm-card-container']//input[@class='oxd-input oxd-input--active']")
    private WebElement reportName;
    @FindBy(xpath = "//div[@class='oxd-grid-4 orangehrm-full-width-grid']" +
            "//div[label[contains(text(), 'Selection Criteria')]]" +
            "/..//div[@class='oxd-select-wrapper']")
    private WebElement selectionCriteriaDropdown;
    @FindBy(xpath = "//div[@role='listbox']")
    private WebElement dropdownList;
    @FindBy(xpath = "//div[@class='oxd-grid-4 orangehrm-full-width-grid']" +
            "//div[label[contains(text(), 'Selection Criteria')]]/../..//button")
    private WebElement selectionCriteriaPlusButton;
    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters'][2]//div[@class='oxd-select-text-input']")
    private WebElement selectGenderDropdown;
    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']" +
            "//div[label[contains(text(), 'Select Display Field Group')]]" +
            "/..//div[@class='oxd-select-wrapper']")
    private WebElement selectDisplayFieldGroupDropdown;
    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters orangehrm-report-criteria --span-column-2']" +
            "//div[label[contains(text(), 'Select Display Field')]]" +
            "/..//div[@class='oxd-select-wrapper']")
    private WebElement selectDisplayFieldDropdown;
    @FindBy(xpath = "//div[@class='oxd-grid-4 orangehrm-full-width-grid']" +
            "//div[label[contains(text(), 'Select Display Field')]]/../..//button")
    private WebElement selectDisplayFieldPlusButton;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    public AddReportPage(WebDriver driver) {
        super(driver);
    }

    public void selectSelectionCriteria(WebDriver driver, String selectionCriteria) {
        this.selectionCriteriaDropdown.click();
        selectDropdownOption(driver, selectionCriteria);
        this.selectionCriteriaPlusButton.click();
    }

    public void selectGender(WebDriver driver, String gender) {
        this.selectGenderDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(dropdownList));
        selectDropdownOption(driver, gender);
    }

    public void selectDisplayFieldGroup(WebDriver driver, String displayFieldGroup) {
        this.selectDisplayFieldGroupDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(dropdownList));
        selectDropdownOption(driver, displayFieldGroup);
    }

    public void selectDisplayField(WebDriver driver, String displayField) {
        this.selectDisplayFieldDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(dropdownList));
        selectDropdownOption(driver, displayField);
        this.selectDisplayFieldPlusButton.click();
    }

    private void selectDropdownOption(WebDriver driver, String dropDownItem) {
        String dropDownBasePath = "//div[@role='listbox']//div[span[contains(text(), '%s')]]";
        String xpath = String.format(dropDownBasePath, dropDownItem);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setReportName(WebDriver driver, String reportName) {
        wait.until(ExpectedConditions.visibilityOf(this.reportName));
        this.reportName.click();
        driver.switchTo().activeElement().sendKeys(reportName);
    }

    public DisplayReportPage clickSaveButton(WebDriver driver) {
        this.saveButton.click();
        return new DisplayReportPage(driver);
    }
}
