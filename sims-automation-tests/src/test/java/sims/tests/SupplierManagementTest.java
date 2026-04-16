package sims.tests;

import sims.base.BaseTest;
import sims.pages.DashboardPage;
import sims.pages.LoginPage;
import sims.pages.SupplierPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SupplierManagementTest extends BaseTest {

    private SupplierPage supplierPage;

    @BeforeEach
    public void loginAndNavigateToSuppliers() {
        driver.get("http://localhost:8080/sims/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("admin@sims.com");
        loginPage.enterPassword("Admin@123");
        DashboardPage dashboardPage = loginPage.clickLogin();
        supplierPage = dashboardPage.goToSuppliers();
    }

    @Test
    @DisplayName("TC-SUPPLIER-01: Add new supplier with valid data")
    public void testAddValidSupplier() {
        supplierPage.clickAddSupplier();
        supplierPage.enterSupplierDetails("Fresh Farms", "John Doe", "john@freshfarms.com", 
                                          "9876543210", "100");
        supplierPage.submitSupplier();
        
        String successMsg = supplierPage.getSuccessMessage();
        Assertions.assertTrue(successMsg.contains("Supplier added successfully"));
        Assertions.assertTrue(supplierPage.isSupplierInTable("Fresh Farms"));
    }
}