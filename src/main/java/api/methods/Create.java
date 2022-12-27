package api.methods;

import api.entities.common.BookingRequest;
import api.entities.common.BookingResponse;
import io.restassured.http.ContentType;
import jdk.jfr.Description;

import static api.constants.Constants.Urls.CREATE_BOOKING;
import static api.utils.RestAssuredConfiguration.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class Create {

    @Description("Create new booking")
    public static BookingResponse createNewBooking(String firstName, String lastName, Integer totalPrice, Boolean depositPaid,
                                                       String checkIn, String checkOut, String additionalNeeds) {
        BookingRequest bookingBody = new BookingRequest()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setTotalPrice(totalPrice)
                .setDepositPaid(depositPaid)
                .setBookingDates(new BookingRequest.BookingDates(checkIn, checkOut))
                .setAdditionalNeeds(additionalNeeds);
        BookingResponse newBooking = given()
                .contentType(ContentType.JSON)
                .body(bookingBody)
                .when()
                .post(CREATE_BOOKING)
                .as(BookingResponse.class);
        assertThat(newBooking, notNullValue());
        return newBooking;
    }
}
