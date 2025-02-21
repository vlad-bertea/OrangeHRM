package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.*;
import utils.TestData;

@TestMethodOrder(OrderAnnotation.class)
public class PIMTests extends BaseTest {

    private static EmployeeListPage employeeListPage;
    private AddEmployeePage addEmployeePage;
    private static ViewPersonalDetailsPage viewPersonalDetailsPage;
    private static ReportsPage reportsPage;
    private static AddReportPage addReportPage;

    private static String employeeId;
    private static String employeeFirstName;
    private static String employeeMiddleName;
    private static String employeeLastName;
    private static String birthDate;

    @Test
    @Order(1)
    public void testAddEmployee() {
        generateTestData();

        pimPage = homePage.accessPIMModule(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(pimPage.getNavigationBar()));

        addEmployeePage = (AddEmployeePage) pimPage.menuNavigation(driver, PIMPage.ADD_EMPLOYEE);
        wait.until(ExpectedConditions.visibilityOfAllElements(addEmployeePage.getAddEmployeeForm()));

        addEmployeePage.typeFirstName(employeeFirstName);
        addEmployeePage.typeMiddleName(employeeMiddleName);
        addEmployeePage.typeLastName(employeeLastName);
        addEmployeePage.typeEmployeeId(employeeId);

        viewPersonalDetailsPage = addEmployeePage.saveEmployee(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(viewPersonalDetailsPage.getPersonalDetailsSubmitButton()));
        viewPersonalDetailsPage.personalDetailsSubmit();

        employeeListPage = (EmployeeListPage) viewPersonalDetailsPage.menuNavigation(driver, PIMPage.EMPLOYEE_LIST);
        wait.until(ExpectedConditions.visibilityOf(employeeListPage.getTableFilter()));

        employeeListPage.searchByName(driver, employeeFirstName);
        wait.until(ExpectedConditions.visibilityOfAllElements(employeeListPage.getRecords()));

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

        viewPersonalDetailsPage.setFirstName(employeeFirstName);
        viewPersonalDetailsPage.personalDetailsSubmit();
        wait.until(ExpectedConditions.visibilityOf(viewPersonalDetailsPage.getFullNameWebElement()));

        Assertions.assertTrue(viewPersonalDetailsPage.getFullNameValue().contains(employeeFirstName));
    }

    @Test
    @Order(3)
    public void testDeleteEmployee() {
        pimPage = new PIMPage(driver);
        employeeListPage = (EmployeeListPage) pimPage.menuNavigation(driver, PIMPage.EMPLOYEE_LIST);
        wait.until(ExpectedConditions.visibilityOf(employeeListPage.getEmployeeIdFilter()));

        employeeListPage.searchById(driver, employeeId);
        employeeListPage.deleteEmployee(driver);
        employeeListPage.searchById(driver, employeeId);

        Assertions.assertEquals(0, employeeListPage.getRecordsCount());
    }

//    @Test
//    @Order(4)
//    public void testCreateReport() {
//        pimPage = homePage.accessPIMModule(driver);
//        wait.until(ExpectedConditions.visibilityOfAllElements(pimPage.getNavigationBar()));
//
//        reportsPage =  (ReportsPage) pimPage.menuNavigation(driver, PIMPage.REPORTS);
//
//        addReportPage = reportsPage.openAddReportPage(driver);
//        addReportPage.selectSelectionCriteria(driver, "Gender");
//    }

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
