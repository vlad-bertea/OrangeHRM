package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DisplayReportPage extends PIMPage {

    @FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 orangehrm-main-title']")
    private WebElement reportName;
    @FindBy(xpath = "//div[@class='oxd-report-table --frame']")
    private WebElement report;

    public DisplayReportPage(WebDriver driver) {
        super(driver);
    }

    public String getReportName() {
        wait.until(ExpectedConditions.visibilityOf(this.report));
        return this.reportName.getText();
    }

    public boolean checkEmployeeIdInReport(WebDriver driver, String employeeId) {
        String baseXpath = "//div[@class='rgCell' and contains(text(), %s)]";
        String xpath = String.format(baseXpath, employeeId);
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
