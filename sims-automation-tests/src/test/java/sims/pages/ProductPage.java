package sims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addProductButton = By.id("addProductBtn");
    private By skuField = By.id("sku");
    private By productNameField = By.id("productName");
    private By priceField = By.id("price");
    private By categoryField = By.id("category");
    private By reorderLevelField = By.id("reorderLevel");
    private By submitButton = By.id("submitProductBtn");
    private By successMessage = By.className("success-message");
    private By errorMessage = By.className("error-message");
    private By productTable = By.id("productTable");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddProduct() {
        driver.findElement(addProductButton).click();
    }

    public void enterProductDetails(String sku, String name, String price, 
                                    String category, String reorderLevel) {
        driver.findElement(skuField).sendKeys(sku);
        driver.findElement(productNameField).sendKeys(name);
        driver.findElement(priceField).sendKeys(price);
        driver.findElement(categoryField).sendKeys(category);
        driver.findElement(reorderLevelField).sendKeys(reorderLevel);
    }

    public void submitProduct() {
        driver.findElement(submitButton).click();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).getText();
    }

    public boolean isProductInTable(String productName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productTable));
        return driver.findElement(productTable).getText().contains(productName);
    }
}