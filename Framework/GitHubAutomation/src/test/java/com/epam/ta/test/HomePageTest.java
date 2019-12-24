package com.epam.ta.test;

import com.epam.ta.model.BookingCar;
import com.epam.ta.page.HomePage;
import com.epam.ta.service.BookingCarCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class HomePageTest extends CommonConditions {
	private static final Logger logger = LogManager.getLogger(HomePageTest.class);
    private HomePage homePage = new HomePage(driver);

    @Test
    public void bookingPickUpReturnLocationFarAwayAndRentalTimeShort() {
        BookingCar bookingCar = BookingCarCreator.withPickUpReturnLocationAndTime();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputReturnLocation(bookingCar.getReturnLocation())
                .inputPickUpDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow().plusDays(1))
                .selectPickUpTime(bookingCar.getPickUpTime())
                .selectReturnTime(bookingCar.getReturnTime())
                .selectCar()
                .getErrorMessage();
        Assert.assertEquals("We are unable to process your request at this time. " +
                        "Please return to the Homepage and start your process again " +
                        "or use the Worldwide Phone Number List to find your Budget Customer Service telephone number.",
                errorMessage);
        logger.log(Level.INFO, "Test booking pick up return location far away and rental time short was performed");
    }

    @Test
    public void bookingPeriodGreaterThanOneMonth() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputPickUpDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow().plusMonths(3))
                .selectCar()
                .getErrorMessage();
        Assert.assertEquals("Sorry, rentals of 31 days or more aren't available to be booked online. Please contact us to book this rental.",
                errorMessage);
        logger.log(Level.INFO, "Test booking period greater than one month was performed");
    }

    @Test
    public void bookingPickUpReturnLocationFarAway() {
        BookingCar bookingCar = BookingCarCreator.withPickUpReturnLocation();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputReturnLocation(bookingCar.getReturnLocation())
                .selectCar()
                .getErrorMessage();
        Assert.assertEquals("The Location you have selected is Sold Out during the dates requested. Please try another Budget location for renting.",
                errorMessage);
        logger.log(Level.INFO, "Test booking pick up return location far away was performed");
    }

    @Test
    public void bookingReturnTimeGreaterThanPickUpTime() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputPickUpDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow())
                .selectPickUpTime(bookingCar.getPickUpTime().plusHours(2).minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectReturnTime(bookingCar.getPickUpTime().minusHours(1).minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectCar()
                .getErrorMessage();
        Assert.assertEquals("Whoops! Your return time has already passed. Please select a new time.",
                errorMessage);
        logger.log(Level.INFO, "Test booking return time greater than pick up time was performed");
    }

    @Test
    public void cannotBeReturnedOnTheSameDayAtTheSameTime() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocationAndSameTime();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputPickUpDate(bookingCar.getDateNow().plusDays(2))
                .inputReturnDate(bookingCar.getDateNow().plusDays(2))
                .selectPickUpTime(bookingCar.getPickUpTime())
                .selectReturnTime(bookingCar.getReturnTime())
                .selectCar()
                .getErrorMessage();
        Assert.assertEquals("Pick-up Date cannot be after Return Date.",
                errorMessage);
        logger.log(Level.INFO, "Test cannot be returned on the same day at the same time was performed");
    }

    @Test
    public void bookingPlaceClosedAtTheRequestedHours() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocationAndCloserTime();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .selectPickUpTime(bookingCar.getPickUpTime())
                .selectReturnTime(bookingCar.getReturnTime())
                .selectCar()
                .getInfoErrorMessage();
        Assert.assertEquals("The Rental Location you have selected is closed during the hours requested." +
                        "\n\n" +
                        "Hours of Operation for this location are:" +
                        "\n" +
                        "Sun - Sat 10:00 AM - 7:00 PM Rental service may be available after hours.Call: +375447122445 for further details." +
                        "\n" +
                        "Please try another Budget location for renting.",
                errorMessage);
        logger.log(Level.INFO, "Test booking place closed at the requested hours was performed");
    }

    @Test
    public void impossiblePlacesForBooking() {
        BookingCar bookingCar = BookingCarCreator.withPickUpReturnImpossibleLocation();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputReturnLocation(bookingCar.getReturnLocation())
                .selectCar()
                .getErrorMessage();
        Assert.assertEquals("",
                errorMessage);
        logger.log(Level.INFO, "Test impossible places for booking was performed");
    }

    @Test
    public void bookingCar() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        String bookingInfoPickUpLocation =
                new HomePage(driver).bookingCar(bookingCar).pickUpLocationInfo();
        assertThat(bookingInfoPickUpLocation, is(equalTo(bookingCar.getPickUpLocation())));
        logger.log(Level.INFO, "Test booking car was performed");
    }

    @Test
    public void bookingPickUpTimeGreaterThanCurrentTime() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        String errorMessage = new HomePage(driver).openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .selectPickUpTime(bookingCar.getPickUpTime().minusHours(3).minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectReturnTime(bookingCar.getPickUpTime().minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectCar()
                .getErrorMessage();
        Assert.assertEquals("Whoops! Your pick-up time has already passed. Please select a new time.",
                errorMessage);
        logger.log(Level.INFO, "Test booking pick up time greater than current time was performed");
    }
}