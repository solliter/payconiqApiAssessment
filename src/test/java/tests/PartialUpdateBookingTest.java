package tests;

import api.entities.common.BookingRequest;
import api.entities.common.BookingResponse;
import api.entities.update.BookingUpdateResponse;
import api.methods.Authentication;
import api.methods.Create;
import api.methods.Delete;
import api.methods.Update;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.Test;
import static api.constants.Constants.Variables.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class PartialUpdateBookingTest extends BaseApi {


    @Test()
    @Description("Creating new booking, getting token, updating booking with name fields, checking all entities")
    public void updateNameFields(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);
        String token = Authentication.getToken(login, password);
        BookingRequest bookingBody = new BookingRequest()
                .setFirstName(newFirstName)
                .setLastName(newLastName);
        BookingUpdateResponse response = Update.partialUpdateBooking(bookingBody,token,newBooking.getBookingId());
        assertThat(response.getFirstName(), is(newFirstName));
        assertThat(response.getLastName(), is(newLastName));
        assertThat(response.getTotalPrice(), is(price));
        assertThat(response.getDepositPaid(), is(true));
        assertThat(response.getBookingDates().getCheckIn(), is(checkIn));
        assertThat(response.getBookingDates().getCheckOut(), is(checkOut));
        assertThat(response.getAdditionalNeeds(), is(additionalNeeds));
        assertThat(Delete.deleteBooking(newBooking.getBookingId(), token).statusCode(), is(201));
    }
    @Test()
    @Description("Creating new booking, getting token, updating booking with all new fields, checking all new entities")
    public void updateAllFields(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);
        String token = Authentication.getToken(login, password);
        BookingRequest bookingBody = new BookingRequest()
                .setFirstName(newFirstName)
                .setLastName(newLastName)
                .setTotalPrice(newPrice)
                .setDepositPaid(false)
                .setBookingDates(new BookingRequest.BookingDates(newCheckIn, newCheckOut))
                .setAdditionalNeeds(newAdditionalNeeds);
        BookingUpdateResponse response = Update.partialUpdateBooking(bookingBody,token,newBooking.getBookingId());
        assertThat(response.getFirstName(), is(newFirstName));
        assertThat(response.getLastName(), is(newLastName));
        assertThat(response.getTotalPrice(), is(newPrice));
        assertThat(response.getDepositPaid(), is(false));
        assertThat(response.getBookingDates().getCheckIn(), is(newCheckIn));
        assertThat(response.getBookingDates().getCheckOut(), is(newCheckOut));
        assertThat(response.getAdditionalNeeds(), is(newAdditionalNeeds));
        assertThat(Delete.deleteBooking(newBooking.getBookingId(), token).statusCode(), is(201));
    }


    @Test()
    @Description("Getting token, updating booking with all new fields, sending wrong idBooking checking error status")
    public void updateAllFieldsWrongId(){
        String token = Authentication.getToken(login, password);
        BookingRequest bookingBody = new BookingRequest()
                .setFirstName(newFirstName)
                .setLastName(newLastName)
                .setTotalPrice(newPrice)
                .setDepositPaid(false)
                .setBookingDates(new BookingRequest.BookingDates(newCheckIn, newCheckOut))
                .setAdditionalNeeds(newAdditionalNeeds);
        Response response = Update.partialUpdateBookingNegative(bookingBody,token, BaseApi.randomNumbers(9));
        assertThat(response.statusCode(), is(405));
    }

    @Test()
    @Description("Creating new booking, updating booking with all new fields, sending wrong token, checking error status")
    public void updateAllFieldsWrongToken(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);
        String token = Authentication.getToken(login, password);
        BookingRequest bookingBody = new BookingRequest()
                .setFirstName(newFirstName)
                .setLastName(newLastName)
                .setTotalPrice(newPrice)
                .setDepositPaid(false)
                .setBookingDates(new BookingRequest.BookingDates(newCheckIn, newCheckOut))
                .setAdditionalNeeds(newAdditionalNeeds);
        Response response = Update.partialUpdateBookingNegative(bookingBody, BaseApi.randomString(9),newBooking.getBookingId());
        assertThat(response.statusCode(), is(403));
        assertThat(Delete.deleteBooking(newBooking.getBookingId(), token).statusCode(), is(201));
    }

    @Test()
    @Description("Creating new booking, getting token, sending empty request to check that all fields are optional, checking response")
    public void updateAllFieldsEmptyRequest(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);
        String token = Authentication.getToken(login, password);
        BookingRequest bookingBody = new BookingRequest();
        BookingUpdateResponse response = Update.partialUpdateBooking(bookingBody,token,newBooking.getBookingId());
        assertThat(response.getFirstName(), is(firstName));
        assertThat(response.getLastName(), is(lastName));
        assertThat(response.getTotalPrice(), is(price));
        assertThat(response.getDepositPaid(), is(true));
        assertThat(response.getBookingDates().getCheckIn(), is(checkIn));
        assertThat(response.getBookingDates().getCheckOut(), is(checkOut));
        assertThat(response.getAdditionalNeeds(), is(additionalNeeds));
        assertThat(Delete.deleteBooking(newBooking.getBookingId(), token).statusCode(), is(201));
    }
}
