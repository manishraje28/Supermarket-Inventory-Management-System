package sims.tests;

import sims.base.BaseTest;
import sims.pages.DashboardPage;
import sims.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RBACTest extends BaseTest {

    @Test
    @DisplayName("TC-RBAC-01: Cashier cannot access admin pages")
    public void testCashierAccessRestriction() {
        // Login as cashier
        driver.get("http://localhost:8080/sims/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("cashier@sims.com");
        loginPage.enterPassword("Cashier@123");
        DashboardPage dashboardPage = loginPage.clickLogin();
        
        // Cashier should NOT see Reports link
        Assertions.assertFalse(dashboardPage.isReportsLinkVisible(),
                "Cashier should not see Reports link");
        
        // Try to access admin page directly
        driver.get("http://localhost:8080/sims/admin/suppliers");
        
        // Verify access denied message appears
        String pageSource = driver.getPageSource();
        Assertions.assertTrue(pageSource.contains("Access Denied") ||
                              pageSource.contains("403"));
    }

    @Test
    @DisplayName("TC-RBAC-02: Admin can access all modules")
    public void testAdminFullAccess() {
        // Login as admin
        driver.get("http://localhost:8080/sims/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("admin@sims.com");
        loginPage.enterPassword("Admin@123");
        DashboardPage dashboardPage = loginPage.clickLogin();
        
        // Admin should see Reports link
        Assertions.assertTrue(dashboardPage.isReportsLinkVisible(),
                "Admin should see Reports link");
    }
}