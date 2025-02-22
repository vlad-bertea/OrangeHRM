package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;
import utils.TestData;

import java.util.UUID;

import static utils.DisplayField.*;
import static utils.DisplayFieldGroup.PERSONAL;
import static utils.ReportsSelectionCriteria.GENDER;

@TestMethodOrder(OrderAnnotation.class)
public class PIMTests extends BaseTest {

    private static EmployeeListPage employeeListPage;
    private static ViewPersonalDetailsPage viewPersonalDetailsPage;
    private static ReportsPage reportsPage;

    private static String employeeId;
    private static String employeeFirstName;
    private static String employeeMiddleName;
    private static String employeeLastName;
    private static final String REPORT_NAME = UUID.randomUUID().toString();

    @Test
    @Order(1)
    public void testAddEmployee() {
        generateTestData();

        pimPage = homePage.accessPIMModule(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(pimPage.getNavigationBar()));

        AddEmployeePage addEmployeePage = (AddEmployeePage) pimPage.menuNavigation(driver, PIMPage.ADD_EMPLOYEE);
        wait.until(ExpectedConditions.visibilityOfAllElements(addEmployeePage.getAddEmployeeForm()));

        addEmployeePage.typeFirstName(employeeFirstName);
        addEmployeePage.typeMiddleName(employeeMiddleName);
        addEmployeePage.typeLastName(employeeLastName);
        addEmployeePage.typeEmployeeId(driver, employeeId);

        viewPersonalDetailsPage = addEmployeePage.saveEmployee(driver);
        wait.until(ExpectedConditions.visibilityOf(
                viewPersonalDetailsPage.getPersonalDetailsSubmitButton()));
        viewPersonalDetailsPage.setDateOfBirth(driver, TestData.BIRTH_DATE);
        viewPersonalDetailsPage.setGender(FEMALE_GENDER);
        viewPersonalDetailsPage.personalDetailsSubmit();

        employeeListPage = (EmployeeListPage) viewPersonalDetailsPage.menuNavigation(driver, PIMPage.EMPLOYEE_LIST);
        wait.until(ExpectedConditions.visibilityOf(employeeListPage.getTableFilter()));

        employeeListPage.searchByName(driver, employeeFirstName);
        wait.until(ExpectedConditions.visibilityOf(employeeListPage.getEmployeeListTable()));
        wait.until(ExpectedConditions.visibilityOf(employeeListPage.getRecords()));

        Assertions.assertTrue(employeeListPage.validateSearchResults
                (employeeFirstName, employeeListPage.getTableInfo(driver, TestData.EMPLOYEE_FIRST_NAME)));
        Assertions.assertTrue(employeeListPage.validateSearchResults
                (employeeMiddleName, employeeListPage.getTableInfo(driver, TestData.EMPLOYEE_MIDDLE_NAME)));
        Assertions.assertTrue(employeeListPage.validateSearchResults
                (employeeLastName, employeeListPage.getTableInfo(driver, TestData.EMPLOYEE_LAST_NAME)));
        Assertions.assertTrue(employeeListPage.validateSearchResults
                (employeeId, employeeListPage.getTableInfo(driver, TestData.EMPLOYEE_ID)));
    }

    @Test
    @Order(2)
    public void testEditEmployee() {
        viewPersonalDetailsPage = employeeListPage.clickEditButton(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(viewPersonalDetailsPage.getMiddleNameInput()));
        generateFirstName();

        viewPersonalDetailsPage.setFirstName(driver, employeeFirstName);
        viewPersonalDetailsPage.personalDetailsSubmit();
        wait.until(ExpectedConditions.visibilityOf(viewPersonalDetailsPage.getFullNameWebElement()));

        Assertions.assertTrue(viewPersonalDetailsPage.getFullNameValue().contains(employeeFirstName));
    }

    @Test
    @Order(3)
    public void testCreateReport() {
        pimPage = homePage.accessPIMModule(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(pimPage.getNavigationBar()));
        reportsPage =  (ReportsPage) pimPage.menuNavigation(driver, PIMPage.REPORTS);
        AddReportPage addReportPage = reportsPage.openAddReportPage(driver);

        addReportPage.setReportName(driver, REPORT_NAME);
        addReportPage.selectSelectionCriteria(driver, GENDER.getLabel());
        addReportPage.selectGender(driver, FEMALE_GENDER);
        addReportPage.selectDisplayFieldGroup(driver, PERSONAL.getLabel());
        addReportPage.selectDisplayField(driver, EMPLOYEE_LAST_NAME.getLabel());
        addReportPage.selectDisplayField(driver, EMPLOYEE_FIRST_NAME.getLabel());
        addReportPage.selectDisplayField(driver, EMPLOYEE_ID.getLabel());
        addReportPage.selectDisplayField(driver, DATE_OF_BIRTH.getLabel());
        addReportPage.selectDisplayField(driver, GENDER.getLabel());

        DisplayReportPage displayReportPage = addReportPage.clickSaveButton(driver);

        Assertions.assertEquals(REPORT_NAME, displayReportPage.getReportName());
        Assertions.assertTrue(displayReportPage.checkEmployeeIdInReport(driver, employeeId));
    }

    @Test
    @Order(4)
    public void testDeleteEmployee() {
        pimPage = new PIMPage(driver);
        employeeListPage = (EmployeeListPage) pimPage.menuNavigation(driver, PIMPage.EMPLOYEE_LIST);
        wait.until(ExpectedConditions.visibilityOf(employeeListPage.getEmployeeIdFilter()));
        employeeListPage.searchById(driver, employeeId);
        employeeListPage.deleteEmployee();
        employeeListPage.searchById(driver, employeeId);

        Assertions.assertEquals(0, employeeListPage.getRecordsCount());
    }

    @Test
    @Order(5)
    public void testSearchReport() {
        homePage = new HomePage(driver);
        pimPage = homePage.accessPIMModule(driver);
        reportsPage = (ReportsPage) pimPage.menuNavigation(driver, PIMPage.REPORTS);
        reportsPage.searchReport(driver, REPORT_NAME);

        Assertions.assertEquals(REPORT_NAME, reportsPage.getSearchResultReportName());
    }

    private void generateTestData() {
        generateFirstName();
        employeeMiddleName = TestData.generateTestData(TestData.EMPLOYEE_MIDDLE_NAME);
        employeeLastName = TestData.generateTestData(TestData.EMPLOYEE_LAST_NAME);
        employeeId = TestData.generateTestData(TestData.EMPLOYEE_ID);
    }

    private void generateFirstName() {
        employeeFirstName = TestData.generateTestData(TestData.EMPLOYEE_FIRST_NAME);
    }
}
