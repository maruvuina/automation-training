package com.epam.ta.page;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehiclePage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    private final String VEHICLE_PAGE_URL = "https://www.budget.com/en/reservation#/vehicles";

    public VehiclePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//div[@id='vehicleTeaser']//div[@class='col-sm-6 col-md-4 source']//div[@class='location-info']")
    private WebElement pickUpLocationInfo;

    @FindBy(xpath = "(//a[@class='modify-link'])[1]")
    private WebElement modifyLink;

    @FindBy(xpath = "(//input[@id='DropLoc_value'])[1]")
    private WebElement dropLocValue;

    @FindBy(xpath = "(//div[@class='step2-renter-summary-detail-btn hidden-xs']//button[@class='btn btn-primary '])[1]")
    private WebElement updateButton;

    @FindBy(xpath = "//div[@class='col-sm-6 col-md-4 destination hidden-xs']//div[@class='location-info']")
    private WebElement locationInfo;

    @Override
    public AbstractPage openPage() {
        driver.navigate().to(VEHICLE_PAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        logger.log(Level.INFO, "Vehicle page opened");
        return this;
    }

    public String pickUpLocationInfo() {
        return pickUpLocationInfo.getText();
    }

    public VehiclePage clickModifyLink() {
        modifyLink.click();
        return this;
    }

    public VehiclePage inputDropLocValue(String dropLoc) {
        dropLocValue.clear();
        dropLocValue.sendKeys(dropLoc);
        new WebDriverWait(driver, 40)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='angucomplete-row' and starts-with(@ng-class, '')]"))).click();
        return this;
    }

    public VehiclePage clickUpdateButton() {
        updateButton.click();
        return this;
    }

    public String getLocationInfo() {
        return locationInfo.getText();
    }
}
