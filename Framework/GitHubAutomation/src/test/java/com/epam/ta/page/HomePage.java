package com.epam.ta.page;

import com.epam.ta.model.BookingCar;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.epam.ta.util.FormatterPattern.DATA_FORMATTER;
import static com.epam.ta.util.FormatterPattern.TIME_FORMATTER;

public class HomePage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    private final String HOMEPAGE_URL = "https://www.budget.com/";

    @FindBy(xpath = "//input[@id='PicLoc_value']")
    private WebElement pickUpLocation;

    @FindBy(xpath = "//input[@id='DropLoc_value']")
    private WebElement returnLocation;

    @FindBy(xpath = "//input[@id='from']")
    private WebElement pickUpDate;

    @FindBy(xpath = "//input[@id='to']")
    private WebElement returnDate;

    @FindBy(xpath = "//button[@id='res-home-select-car']")
    private WebElement selectCar;

    @FindBy(xpath = "//select[@name='reservationModel.pickUpTime' and @class='form-control res-inputTime ng-pristine ng-valid ng-not-empty ng-touched']")
    private WebElement pickUpTime;

    @FindBy(xpath = "//select[@name='reservationModel.dropTime' and @class='form-control res-inputTime ng-pristine ng-valid ng-not-empty ng-touched']")
    private WebElement returnTime;
    
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public HomePage openPage() {
        driver.navigate().to(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        logger.log(Level.INFO, "Home page opened");
        return this;
    }

    public HomePage inputPickUpLocation(String picLoc) {
        pickUpLocation.sendKeys(picLoc);
        pickUpLocation.sendKeys(picLoc);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='angucomplete-row' and starts-with(@ng-class, '')]"))).click();
        return this;
    }

    public HomePage inputReturnLocation(String returnLoc) {
        returnLocation.sendKeys(returnLoc);
        new WebDriverWait(driver, 40)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='angucomplete-row' and starts-with(@ng-class, '')]"))).click();
        return this;
    }

    public HomePage inputPickUpDate(LocalDate picDate) {
        pickUpDate.clear();
        pickUpDate.sendKeys(picDate.format(DATA_FORMATTER));
        return this;
    }

    public HomePage inputReturnDate(LocalDate dropDate) {
        returnDate.clear();
        returnDate.sendKeys(dropDate.format(DATA_FORMATTER));
        return this;
    }

    public HomePage selectCar() {
        selectCar.click();
        return this;
    }

    public HomePage selectPickUpTime(LocalTime picTime) {
        new Select(pickUpTime).selectByVisibleText(picTime.format(TIME_FORMATTER));
        return this;
    }

    public HomePage selectReturnTime(LocalTime dropTime) {
        new Select(returnTime).selectByVisibleText(dropTime.format(TIME_FORMATTER));
        return this;
    }

    public String getErrorMessage() {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//div[@class='col-lg-12 res-PageError']//span[@class='mainErrorText info-error-msg-text']")))
                            .getText();
    }

    public String getInfoErrorMessage() {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//span[@id='warning-msg-err']")))
                            .getText();
    }

    public VehiclePage bookingCar(BookingCar bookingCar) {
        inputPickUpLocation(bookingCar.getPickUpLocation())
            .inputPickUpDate(bookingCar.getDateNow().plusDays(1))
            .inputReturnDate(bookingCar.getDateNow().plusDays(2))
            .selectCar();
        logger.log(Level.INFO, "Vehicle page performed.");
        return new VehiclePage(driver);
    }
}
