package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AddReportPage extends PIMPage {

    @FindBy(xpath = "//div[@class='orangehrm-card-container']//input[@class='oxd-input oxd-input--active']")
    private WebElement reportName;
    @FindBy(xpath = "//div[@class='orangehrm-card-container']//input[@class='oxd-input oxd-input--focus']")
    private WebElement reportNameFocused;
    @FindBy(xpath = "//div[@class='oxd-grid-4 orangehrm-full-width-grid']" +
            "//div[label[contains(text(), 'Selection Criteria')]]" +
            "/..//div[@class='oxd-select-wrapper']")
    private WebElement selectionCriteriaDropdown;
    @FindBy(xpath = "//div[@role='listbox']")
    private WebElement dropdownList;
    @FindBy(xpath = "//div[@class='oxd-grid-4 orangehrm-full-width-grid']" +
            "//div[label[contains(text(), 'Selection Criteria')]]/../..//button")
    private WebElement selectionCriteriaPlusButton;

    public AddReportPage(WebDriver driver) {
        super(driver);
    }

    public void clickSelectionCriteriaDropdown() {
        wait.until(ExpectedConditions.visibilityOf(selectionCriteriaDropdown));
        this.selectionCriteriaDropdown.click();
    }

    public WebElement getDropdownList() {
        return this.dropdownList;
    }

    public void selectSelectionCriteria(WebDriver driver, String selectionCriteria) {
        clickSelectionCriteriaDropdown();
        String selectionCriteriaValueBaseXpath = "//div[@role='listbox']//div[span[contains(text(), '%s')]]";
        String xpath = String.format(selectionCriteriaValueBaseXpath, selectionCriteria);
        driver.findElement(By.xpath(xpath)).click();
        this.selectionCriteriaPlusButton.click();
    }
}
