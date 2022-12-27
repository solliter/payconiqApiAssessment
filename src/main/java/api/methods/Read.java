package api.methods;

import api.entities.read.GetBookingIdsResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdk.jfr.Description;


import static api.constants.Constants.Urls.*;
import static api.utils.RestAssuredConfiguration.given;

public class Read {

    @Description("Getting booking by booking id")
    public static Response getBookingById(Integer id){
        return given().contentType(ContentType.JSON)
                .get(BOOKING_BY_ID(id));}

    @Description("Getting all bookings by booking id")
    public static GetBookingIdsResponse[] getAllBookings(){
        return given().contentType(ContentType.JSON)
                .get(GET_BOOKING_IDS_ALL)
                .as(GetBookingIdsResponse[].class);}

    @Description("Getting bookings by guest firstName and lastName")
    public static GetBookingIdsResponse[] getBookingsByName(String firstName, String lastName){
        return given().contentType(ContentType.JSON)
                .get(GET_BOOKING_IDS_BY_NAME(firstName, lastName))
                .as(GetBookingIdsResponse[].class);}

    @Description("Getting all bookings by dates checkIn and checkOut")
    public static GetBookingIdsResponse[] getBookingsByCheckInCheckOut(String checkIn, String checkOut){
        return given().contentType(ContentType.JSON)
                .get(GET_BOOKING_IDS_BY_CHECKIN_CHECKOUT(checkIn, checkOut))
                .as(GetBookingIdsResponse[].class);}
    }
