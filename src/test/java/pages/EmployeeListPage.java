package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.TestData;

import java.util.ArrayList;

public class EmployeeListPage extends PIMPage {

    @FindBy(xpath = "//div[@class='oxd-table-filter']")
    private WebElement tableFilter;
    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']" +
            "//div[label[contains(text(), 'Employee Name')]]" +
            "/..//div[contains(@class, 'oxd-autocomplete-text-input oxd-autocomplete-text-input')]")
    private WebElement employeeNameFilter;
    @FindBy(xpath = "//div[@class='oxd-grid-item oxd-grid-item--gutters']" +
            "//div[label[contains(text(), 'Employee Id')]]" +
            "/..//input[@class='oxd-input oxd-input--active']")
    private WebElement employeeIdFilter;
    @FindBy(xpath = "//div[@class='oxd-table-filter']//button[@type='submit']")
    private WebElement searchButton;
    @FindBy(xpath = "//div[@class='oxd-table orangehrm-employee-list']")
    private WebElement employeeListTable;
    @FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']" +
            "//span[@class='oxd-text oxd-text--span']")
    private WebElement records;
    @FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable'][1]" +
            "//div[@class='oxd-table-cell oxd-padding-cell'][9]//div[@class='oxd-table-cell-actions']//button[1]")
    private WebElement editButton;
    @FindBy(xpath = "//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable'][1]" +
            "//div[@class='oxd-table-cell oxd-padding-cell'][9]//div[@class='oxd-table-cell-actions']//button[2]")
    private WebElement deleteButton;
    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']")
    private WebElement deletePopupConfirmationButton;

    public EmployeeListPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getRecords() {
        return this.records;
    }

    public int getRecordsCount() {
        String recordsTextValue = this.records.getText();
        String[] workingList = recordsTextValue.split("\\(");
        int recordIntValue = 0;

        try {
            workingList = workingList[1].split("\\)");
        } catch (ArrayIndexOutOfBoundsException e) {
            return recordIntValue;
        }

        try {
            recordIntValue = Integer.parseInt(workingList[0]);
        } catch (NumberFormatException e) {
            return recordIntValue;
        }
        return recordIntValue;
    }

    public WebElement getTableFilter() {
        return this.tableFilter;
    }

    public WebElement getEmployeeIdFilter() {
        return this.employeeIdFilter;
    }

    public WebElement getEmployeeListTable() {
        return this.employeeListTable;
    }

    public void searchByName(WebDriver driver, String searchNameValue) {
        this.employeeNameFilter.click();
        driver.switchTo().activeElement().sendKeys(searchNameValue);
        this.searchButton.click();
    }

    public void searchById(WebDriver driver, String searchIdValue) {
        this.employeeIdFilter.click();
        driver.switchTo().activeElement().sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        driver.switchTo().activeElement().sendKeys(searchIdValue);
        this.searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(this.records));
    }

    public ViewPersonalDetailsPage clickEditButton(WebDriver driver) {
        this.editButton.click();
        return new ViewPersonalDetailsPage(driver);
    }

    public void deleteEmployee() {
        this.deleteButton.click();
        this.deletePopupConfirmationButton.click();
    }

    public ArrayList<String> getTableInfo(WebDriver driver, String tableInfo) {
        int recordsCount = getRecordsCount();
        String tableCellBaseXpath =
                "((//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable'])[%s]" +
                        "//div[@class='oxd-table-cell oxd-padding-cell'])[%s]";
        ArrayList<String> result = new ArrayList<>();
        for (int row = 1; row < recordsCount + 1; row += 1) {
            switch (tableInfo) {
                case TestData.EMPLOYEE_FIRST_NAME, TestData.EMPLOYEE_MIDDLE_NAME -> {
                    String firstAndMiddleNamesXpath = String.format(tableCellBaseXpath, row, 3);
                    result.add(driver.findElement(By.xpath(firstAndMiddleNamesXpath)).getText());
                }
                case TestData.EMPLOYEE_LAST_NAME -> {
                    String lastNameXpath = String.format(tableCellBaseXpath, row, 4);
                    result.add(driver.findElement(By.xpath(lastNameXpath)).getText());
                }
                case TestData.EMPLOYEE_ID -> {
                    String employeeIdXpath = String.format(tableCellBaseXpath, row, 2);
                    result.add(driver.findElement(By.xpath(employeeIdXpath)).getText());
                }
            }
        }
        return result;
    }

    public boolean validateSearchResults(String searchValue, ArrayList<String> searchResults) {
        boolean matchFound = false;
        for(String searchResult: searchResults) {
            if (searchResult.contains(searchValue)) {
                matchFound = true;
                break;
            }
        }
        return  matchFound;
    }
}
