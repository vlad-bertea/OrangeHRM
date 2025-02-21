package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PIMPage extends HomePage {

    public  static final String CONFIGURATION = "CONFIGURATION";
    public  static final String EMPLOYEE_LIST = "EMPLOYEE_LIST";
    public  static final String ADD_EMPLOYEE = "ADD_EMPLOYEE";
    public  static final String REPORTS = "REPORTS";

    @FindBy(xpath = "//nav[@class='oxd-topbar-body-nav'][ul]")
    private WebElement navigationBar;
    String navigationBarXpath = "//nav[@class='oxd-topbar-body-nav']/ul/li[%s]";

    public PIMPage(WebDriver driver) {
        super(driver);
    }

    public PIMPage menuNavigation(WebDriver driver, String menuItem) {
        switch (menuItem) {
            case (CONFIGURATION) -> {
                driver.findElement(By.xpath(String.format(navigationBarXpath, 1))).click();
                return new ConfigurationPage(driver);
            }
            case (EMPLOYEE_LIST) -> {
                driver.findElement(By.xpath(String.format(navigationBarXpath, 2))).click();
                return new EmployeeListPage(driver);
            }
            case (ADD_EMPLOYEE) -> {
                driver.findElement(By.xpath(String.format(navigationBarXpath, 3))).click();
                return new AddEmployeePage(driver);
            }
            case (REPORTS) -> {
                driver.findElement(By.xpath(String.format(navigationBarXpath, 4))).click();
                return new ReportsPage(driver);
            }
            default -> {
                return new PIMPage(driver);
            }
        }
    }

    public WebElement getNavigationBar() {
        return this.navigationBar;
    }
}
