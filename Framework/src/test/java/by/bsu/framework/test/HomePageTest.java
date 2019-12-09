package by.bsu.framework.test;

import by.bsu.framework.model.BookingCar;
import by.bsu.framework.page.HomePage;
import by.bsu.framework.service.BookingCarCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class HomePageTest extends CommonConditions {
    private HomePage homePage = new HomePage(driver);

    @Test
    public void bookingPickUpReturnLocationFarAwayAndRentalTimeShort() {
        BookingCar bookingCar = BookingCarCreator.withPickUpReturnLocationAndTime();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputReturnLocation(bookingCar.getReturnLocation())
                .inputPickUpDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow().plusDays(1))
                .selectPickUpTime(bookingCar.getPickUpTime())
                .selectReturnTime(bookingCar.getReturnTime())
                .selectCar();
        Assert.assertEquals("We are unable to process your request at this time. " +
                        "Please return to the Homepage and start your process again " +
                        "or use the Worldwide Phone Number List to find your Budget Customer Service telephone number.",
                homePage.getErrorMessage());
    }

    @Test
    public void bookingPeriodGreaterThanOneMonth() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputPickUpDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow().plusMonths(3))
                .selectCar();
        Assert.assertEquals("Sorry, rentals of 31 days or more aren't available to be booked online. Please contact us to book this rental.",
                homePage.getErrorMessage());
    }

    @Test
    public void bookingPickUpReturnLocationFarAway() {
        BookingCar bookingCar = BookingCarCreator.withPickUpReturnLocation();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputReturnLocation(bookingCar.getReturnLocation())
                .selectCar();
        Assert.assertEquals("The Location you have selected is Sold Out during the dates requested. Please try another Budget location for renting.",
                homePage.getErrorMessage());
    }

    @Test
    public void bookingReturnTimeGreaterThanPickUpTime() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputPickUpDate(bookingCar.getDateNow())
                .inputReturnDate(bookingCar.getDateNow())
                .selectPickUpTime(bookingCar.getPickUpTime().plusHours(2).minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectReturnTime(bookingCar.getPickUpTime().minusHours(1).minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectCar();
        Assert.assertEquals("Whoops! Your return time has already passed. Please select a new time.",
                homePage.getErrorMessage());
    }

    @Test
    public void cannotBeReturnedOnTheSameDayAtTheSameTime() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocationAndSameTime();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputPickUpDate(bookingCar.getDateNow().plusDays(2))
                .inputReturnDate(bookingCar.getDateNow().plusDays(2))
                .selectPickUpTime(bookingCar.getPickUpTime())
                .selectReturnTime(bookingCar.getReturnTime())
                .selectCar();
        Assert.assertEquals("Pick-up Date cannot be after Return Date.",
                homePage.getErrorMessage());
    }

    @Test
    public void bookingPlaceClosedAtTheRequestedHours() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocationAndCloserTime();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .selectPickUpTime(bookingCar.getPickUpTime())
                .selectReturnTime(bookingCar.getReturnTime())
                .selectCar();
        Assert.assertEquals("The Rental Location you have selected is closed during the hours requested." +
                        "\n\n" +
                        "Hours of Operation for this location are:" +
                        "\n" +
                        "Sun - Sat 10:00 AM - 7:00 PM Rental service may be available after hours.Call: +375447122445 for further details." +
                        "\n" +
                        "Please try another Budget location for renting.",
                homePage.getInfoErrorMessage());
    }

    @Test
    public void impossiblePlacesForBooking() {
        BookingCar bookingCar = BookingCarCreator.withPickUpReturnImpossibleLocation();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .inputReturnLocation(bookingCar.getReturnLocation())
                .selectCar();
        Assert.assertEquals("Sorry! No Budget locations are available in address provided.!",
                homePage.getErrorMessage());
    }

    @Test
    public void bookingCar() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        String bookingInfoPickUpLocation =
                homePage.bookingCar(bookingCar).pickUpLocationInfo();
        assertThat(bookingInfoPickUpLocation, is(equalTo(bookingCar.getPickUpLocation())));
    }

    @Test
    public void bookingPickUpTimeGreaterThanCurrentTime() {
        BookingCar bookingCar = BookingCarCreator.withPickUpLocation();
        homePage.openPage()
                .inputPickUpLocation(bookingCar.getPickUpLocation())
                .selectPickUpTime(bookingCar.getPickUpTime().minusHours(3).minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectReturnTime(bookingCar.getPickUpTime().minusMinutes(bookingCar.getPickUpTime().getMinute()))
                .selectCar();
        Assert.assertEquals("Whoops! Your pick-up time has already passed. Please select a new time.",
                homePage.getErrorMessage());
    }
}