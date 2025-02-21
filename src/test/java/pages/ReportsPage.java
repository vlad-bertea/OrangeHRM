package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReportsPage extends PIMPage {

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addReportButton;

    public ReportsPage(WebDriver driver) {
        super(driver);
    }

    public AddReportPage openAddReportPage(WebDriver driver) {
        wait.until(ExpectedConditions.visibilityOf(addReportButton));
        this.addReportButton.click();
        return new AddReportPage(driver);
    }
}
