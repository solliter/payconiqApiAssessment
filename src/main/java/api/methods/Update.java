package api.methods;

import api.entities.common.BookingRequest;
import api.entities.update.BookingUpdateResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;

import static api.constants.Constants.Urls.BOOKING_BY_ID;
import static api.utils.RestAssuredConfiguration.given;

public class Update {

    @Description("Change part of booking")
    public static BookingUpdateResponse partialUpdateBooking(BookingRequest bookingBody, String authorization, Integer id) {
        return given()
                .contentType(ContentType.JSON)
                .cookies("token", authorization)
                .body(bookingBody)
                .when()
                .patch(BOOKING_BY_ID(id))
                .as(BookingUpdateResponse.class);
    }

    @Description("Change part of booking(negative) to check status code")
    public static Response partialUpdateBookingNegative(BookingRequest bookingBody, String authorization, Integer id) {
        return given()
                .contentType(ContentType.JSON)
                .cookies("token", authorization)
                .body(bookingBody)
                .when()
                .patch(BOOKING_BY_ID(id));
    }
}
