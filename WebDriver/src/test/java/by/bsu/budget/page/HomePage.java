package by.bsu.budget.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HomePage {
    private final String HOMEPAGE_URL = "https://www.budget.com/";
    private final int WAIT_TIMEOUT_SECONDS = 40;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(HOMEPAGE_URL);
        PageFactory.initElements(this.driver, this);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    @FindBy(xpath = "//*[@id=\"PicLoc_value\"]")
    private WebElement pickUpLocation;

    @FindBy(xpath = "//*[@id=\"DropLoc_value\"]")
    private WebElement returnLocation;

    @FindBy(xpath = "//*[@id=\"from\"]")
    private WebElement pickUpDate;

    @FindBy(xpath = "//*[@id=\"to\"]")
    private WebElement returnDate;

    @FindBy(xpath = "//*[@id=\"res-home-select-car\"]")
    private WebElement selectCar;

    @FindBy(xpath = "//*[@id=\"selectCar\"]/div/div[2]/div[6]/div[1]/div[1]/div[3]/select")
    private WebElement pickUpTime;

    @FindBy(xpath = "//*[@id=\"selectCar\"]/div/div[2]/div[6]/div[1]/div[4]/div[1]/div[3]/select")
    private WebElement returnTime;

    public void inputPickUpLocation(String picLoc) {
        pickUpLocation.clear();
        pickUpLocation.sendKeys(picLoc);

    }

    public void inputPickUpDate(LocalDate picDate) {
        pickUpDate.clear();
        pickUpDate.sendKeys(picDate.format(dateFormatter));
    }

    public void inputReturnDate(LocalDate dropDate) {
        returnDate.clear();
        returnDate.sendKeys(dropDate.format(dateFormatter));
    }

    public void selectCar() {
        selectCar.click();
    }

    public boolean isErrorMessgaeVisiable() {
        WebElement errorMessgase =
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//*[@id=\"selectCar\"]/div/div[2]/div[2]/div/div/div/span[@class=\"mainErrorText info-error-msg-text\"]")));
        return errorMessgase.isDisplayed();
    }

    public void selectPickUpTime(LocalTime picTime) {
        new Select(pickUpTime).selectByVisibleText(picTime.format(timeFormatter));
    }

    public void selectReturnTime(LocalTime dropTime) {
        new Select(returnTime).selectByVisibleText(dropTime.format(timeFormatter));
    }
}
