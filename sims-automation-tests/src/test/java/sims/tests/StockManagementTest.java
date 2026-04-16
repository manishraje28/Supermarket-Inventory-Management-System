package sims.tests;

import sims.base.BaseTest;
import sims.pages.DashboardPage;
import sims.pages.LoginPage;
import sims.pages.StockPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class StockManagementTest extends BaseTest {

    private StockPage stockPage;

    @BeforeEach
    public void loginAndNavigateToStock() {
        driver.get("http://localhost:8080/sims/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("manager@sims.com");
        loginPage.enterPassword("Manager@123");
        DashboardPage dashboardPage = loginPage.clickLogin();
        stockPage = dashboardPage.goToStock();
    }

    @Test
    @DisplayName("TC-STOCK-01: Verify stock table is displayed")
    public void testStockTableDisplayed() {
        Assertions.assertTrue(stockPage.isStockTableDisplayed());
    }

    @Test
    @DisplayName("TC-STOCK-02: Verify stock quantities are numeric")
    public void testStockQuantitiesAreNumeric() {
        for (WebElement cell : stockPage.getQuantityCells()) {
            String qty = cell.getText();
            Assertions.assertTrue(qty.matches("\\d+"), "Stock quantity should be numeric: " + qty);
        }
    }
}