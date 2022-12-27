package api.methods;

import api.entities.auth.BookingAuthRequest;
import api.entities.auth.BookingAuthResponse;
import io.restassured.http.ContentType;
import jdk.jfr.Description;

import static api.constants.Constants.Urls.AUTHENTICATION;
import static api.utils.RestAssuredConfiguration.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

public class Authentication {
    @Description("Getting auth token")
    public static String getToken(String username, String password){
        BookingAuthRequest bookingBody = new BookingAuthRequest()
                .setUsername(username)
                .setPassword(password);
        BookingAuthResponse newBooking = given()
                .contentType(ContentType.JSON)
                .body(bookingBody)
                .when()
                .post(AUTHENTICATION)
                .as(BookingAuthResponse.class);
        assertThat(newBooking, notNullValue());
        return newBooking.getToken();
    }
}
