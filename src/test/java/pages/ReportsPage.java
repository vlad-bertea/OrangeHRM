package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReportsPage extends PIMPage {

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addReportButton;
    @FindBy(xpath = "//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']")
    private WebElement reportNameSearchInput;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']")
    private WebElement searchButton;
    @FindBy(xpath = "(//div[@class='oxd-table-row oxd-table-row--with-border'])" +
            "[2]//div[@class='oxd-table-cell oxd-padding-cell'][2]")
    private WebElement searchResultReportName;

    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    public void searchReport(WebDriver driver, String reportName) {
        wait.until(ExpectedConditions.visibilityOf(reportNameSearchInput));
        this.reportNameSearchInput.click();
        driver.switchTo().activeElement().sendKeys(reportName);
        this.searchButton.click();
    }

    public String getSearchResultReportName() {
        wait.until(ExpectedConditions.visibilityOf(this.searchResultReportName));
        return this.searchResultReportName.getText();
    }

    public AddReportPage openAddReportPage(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(addReportButton));
        this.addReportButton.click();
        return new AddReportPage(driver);
    }
}
