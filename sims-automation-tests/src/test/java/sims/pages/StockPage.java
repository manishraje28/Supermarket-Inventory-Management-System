package sims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StockPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By stockTable = By.id("stockTable");
    private By lowStockRows = By.cssSelector(".low-stock-row");
    private By quantityCells = By.cssSelector(".stock-quantity");

    public StockPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isStockTableDisplayed() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(stockTable));
        return driver.findElement(stockTable).isDisplayed();
    }

    public List<WebElement> getLowStockRows() {
        return driver.findElements(lowStockRows);
    }

    public List<WebElement> getQuantityCells() {
        return driver.findElements(quantityCells);
    }
}