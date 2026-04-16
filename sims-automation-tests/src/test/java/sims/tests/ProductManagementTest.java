package sims.tests;

import sims.base.BaseTest;
import sims.pages.DashboardPage;
import sims.pages.LoginPage;
import sims.pages.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ProductManagementTest extends BaseTest {

    private ProductPage productPage;

    @BeforeEach
    public void loginAndNavigateToProducts() {
        driver.get("http://localhost:8080/sims/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("manager@sims.com");
        loginPage.enterPassword("Manager@123");
        DashboardPage dashboardPage = loginPage.clickLogin();
        productPage = dashboardPage.goToProducts();
    }

    @ParameterizedTest
    @CsvSource({
        "0, invalid",
        "1, valid",
        "2, valid",
        "99998, valid",
        "99999, valid",
        "100000, invalid",
        "'abc', invalid"
    })
    @DisplayName("TC-PRICE-BVA: Test price boundary values")
    public void testPriceBoundaryValues(String price, String expectedValidity) {
        productPage.clickAddProduct();
        productPage.enterProductDetails("PRD10001", "Test Product", price, "Test Category", "50");
        productPage.submitProduct();

        if (expectedValidity.equals("valid")) {
            String successMsg = productPage.getSuccessMessage();
            Assertions.assertTrue(successMsg.contains("Product added successfully"));
            Assertions.assertTrue(productPage.isProductInTable("Test Product"));
        } else {
            String errorMsg = productPage.getErrorMessage();
            Assertions.assertTrue(errorMsg.contains("error") || errorMsg.contains("invalid"));
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("TC-PRODUCT-01: Add product with valid data")
    public void testAddValidProduct() {
        productPage.clickAddProduct();
        productPage.enterProductDetails("PRD20001", "Fresh Milk 1L", "60", "Dairy", "50");
        productPage.submitProduct();
        
        String successMsg = productPage.getSuccessMessage();
        Assertions.assertTrue(successMsg.contains("Product added successfully"));
        Assertions.assertTrue(productPage.isProductInTable("Fresh Milk 1L"));
    }
}