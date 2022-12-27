package tests;

import api.entities.common.BookingResponse;
import api.entities.read.GetBookingIdsResponse;
import api.methods.Authentication;
import api.methods.Create;
import api.methods.Delete;
import api.methods.Read;
import jdk.jfr.Description;
import org.junit.Test;

import java.util.Arrays;

import static api.constants.Constants.Variables.*;
import static api.constants.Constants.Variables.additionalNeeds;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class GetBookingIdsTest extends BaseApi {

    @Test()
    @Description("Creating new booking saving id, get all bookings and check is there new booking id")
    public void getAllBookingIds(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                    additionalNeeds);

        GetBookingIdsResponse[] bookings = Read.getAllBookings();
        assertThat("new bookingId not found", Arrays.stream(bookings)
                .anyMatch(c -> c.getBookingId().equals(newBooking.getBookingId())));

        String token = Authentication.getToken(login, password);
        assertThat(Delete.deleteBooking(newBooking.getBookingId(), token).statusCode(), is(201));
    }

    @Test()
    @Description("Creating new booking saving id, get all bookings by name and check is there new booking id")
    public void getBookingByName(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);

        GetBookingIdsResponse[] bookings = Read.getBookingsByName(firstName,lastName);
        assertThat("new bookingId not found", Arrays.stream(bookings)
                .anyMatch(c -> c.getBookingId().equals(newBooking.getBookingId())));

        String token = Authentication.getToken(login, password);
        assertThat(Delete.deleteBooking(newBooking.getBookingId(), token).statusCode(), is(201));
    }

    @Test()
    @Description("Get all bookings by wrong name and check that it's empty")
    public void getBookingByNameNegative(){
        GetBookingIdsResponse[] bookings = Read.getBookingsByName(wrongFirstName,wrongLastName);
        assertThat(bookings.length, is(0));
    }

    @Test()
    @Description("Creating new booking saving id, get all bookings by checkIn and checkOut dates and check is there new booking id")
    public void getBookingByCheckInCheckOutDates(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);

        GetBookingIdsResponse[] bookings = Read.getBookingsByCheckInCheckOut("2021-12-01","2022-01-03");
        assertThat("new bookingId not found", Arrays.stream(bookings)
                .anyMatch(c -> c.getBookingId().equals(newBooking.getBookingId())));

        String token = Authentication.getToken(login, password);
        assertThat(Delete.deleteBooking(newBooking.getBookingId(), token).statusCode(), is(201));
    }

    @Test()
    @Description("Get all bookings by wrong checkIn and checkOut dates and check that it's empty")
    public void getBookingByCheckInCheckOutDatesNegative(){
        GetBookingIdsResponse[] bookings = Read.getBookingsByCheckInCheckOut(wrongCheckIn,wrongCheckOut);
        assertThat(bookings.length, is(0));
    }
}
