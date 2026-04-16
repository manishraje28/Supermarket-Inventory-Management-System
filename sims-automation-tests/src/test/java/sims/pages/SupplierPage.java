package sims.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SupplierPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By addSupplierButton = By.id("addSupplierBtn");
    private By supplierNameField = By.id("supplierName");
    private By contactField = By.id("contact");
    private By emailField = By.id("email");
    private By phoneField = By.id("phone");
    private By moqField = By.id("moq");
    private By submitButton = By.id("submitSupplierBtn");
    private By successMessage = By.className("success-message");
    private By supplierTable = By.id("supplierTable");

    public SupplierPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickAddSupplier() {
        driver.findElement(addSupplierButton).click();
    }

    public void enterSupplierDetails(String name, String contact, String email, 
                                     String phone, String moq) {
        driver.findElement(supplierNameField).sendKeys(name);
        driver.findElement(contactField).sendKeys(contact);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(moqField).sendKeys(moq);
    }

    public void submitSupplier() {
        driver.findElement(submitButton).click();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
        return driver.findElement(successMessage).getText();
    }

    public boolean isSupplierInTable(String supplierName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(supplierTable));
        return driver.findElement(supplierTable).getText().contains(supplierName);
    }
}