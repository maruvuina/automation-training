package by.bsu.framework.test;

import by.bsu.framework.model.BookingCar;
import by.bsu.framework.page.HomePage;
import by.bsu.framework.service.BookingCarCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class VehiclePageTest extends CommonConditions {
    private HomePage homePage = new HomePage(driver);

    @Test
    public void updateBookingCarParameters() {
        BookingCar bookingCar = BookingCarCreator.withPickUpReturnDifferentLocation();
        String changingReturnInfoLocation =
                homePage.bookingCar(bookingCar)
                        .clickModifyLink()
                        .inputDropLocValue(bookingCar.getReturnLocation())
                        .clickUpdateButton()
                        .getLocationInfo();
        assertThat(changingReturnInfoLocation, is(equalTo(bookingCar.getReturnLocation())));
    }
}
