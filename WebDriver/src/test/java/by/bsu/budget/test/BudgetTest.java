package by.bsu.budget.test;

import by.bsu.budget.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class BudgetTest {
    private WebDriver driver;
    private HomePage page;
    private final String pickUpLocation = "Minsk National Airport";
    private final LocalDate pickUpDate = LocalDate.now();
    private final LocalTime timeNow = LocalTime.NOON;

    @BeforeClass
    public void browserSetUp() {
       driver = new ChromeDriver();
       page = new HomePage(driver);
       driver.manage().window().maximize();
    }

    @Test
    public void bookingPeriodGreaterThanOneMonth() {
        page.inputPickUpLocation(pickUpLocation);
        page.inputPickUpDate(pickUpDate);
        page.inputReturnDate(pickUpDate.plusMonths(3));
        page.selectCar();
        Assert.assertTrue(page.isErrorMessgaeVisiable());
    }

    @Test
    public void bookingReturnTimeGreaterThanPickUpTime() {
        page.inputPickUpLocation(pickUpLocation);
        page.inputPickUpDate(pickUpDate);
        page.inputReturnDate(pickUpDate);
        page.selectPickUpTime(timeNow.plusHours(2).minusMinutes(timeNow.getMinute()));
        page.selectReturnTime(timeNow.minusHours(1).minusMinutes(timeNow.getMinute()));
        page.selectCar();
        Assert.assertTrue(page.isErrorMessgaeVisiable());
    }

    @AfterClass
    public void browserTearDown() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}