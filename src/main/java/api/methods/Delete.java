package api.methods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;

import static api.constants.Constants.Urls.BOOKING_BY_ID;
import static api.utils.RestAssuredConfiguration.given;

public class Delete {

    @Description("Delete booking with cookie")
    public static Response deleteBooking(Integer id, String authorization) {
        return given().contentType(ContentType.JSON)
                .cookie("token", authorization)
                .delete(BOOKING_BY_ID(id));}
}
