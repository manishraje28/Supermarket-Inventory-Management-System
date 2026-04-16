package sims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By welcomeMessage = By.cssSelector(".welcome-message");
    private By productsLink = By.linkText("Products");
    private By stockLink = By.linkText("Stock");
    private By suppliersLink = By.linkText("Suppliers");
    private By reportsLink = By.linkText("Reports");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getWelcomeText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
        return driver.findElement(welcomeMessage).getText();
    }

    public ProductPage goToProducts() {
        driver.findElement(productsLink).click();
        return new ProductPage(driver);
    }

    public StockPage goToStock() {
        driver.findElement(stockLink).click();
        return new StockPage(driver);
    }

    public SupplierPage goToSuppliers() {
        driver.findElement(suppliersLink).click();
        return new SupplierPage(driver);
    }

    public boolean isReportsLinkVisible() {
        return driver.findElement(reportsLink).isDisplayed();
    }
}