import api.entities.common.BookingResponse;
import api.methods.Authentication;
import api.methods.Create;
import api.methods.Delete;
import api.methods.Read;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.Test;

import static api.constants.Constants.Variables.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class DeleteBookingTests extends BaseApi {

    @Test()
    @Description("Creating booking, deleting it, checking if deleted")
    public void deleteBooking(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);
        String token = Authentication.getToken(login, password);
        Response response = Delete.deleteBooking(newBooking.getBookingId(), token);
        assertThat(response.statusCode(), is(201));
        Response responseRead = Read.getBookingById(newBooking.getBookingId());
        assertThat(responseRead.statusCode(), is(404));
    }

    @Test()
    @Description("Creating random id that not exist, trying to delete it and checking error status")
    public void deleteNonExistedIdBooking(){
        String token = Authentication.getToken(login, password);
        Response response = Delete.deleteBooking(randomNumbers(9), token);
        assertThat(response.statusCode(), is(405));
    }

    @Test()
    @Description("Creating booking, deleting it using wrong random token, checking error status")
    public void deleteBookingWithWrongToken(){
        BookingResponse newBooking = Create.createNewBooking(firstName,lastName,price,true,checkIn,checkOut,
                additionalNeeds);
        Response response = Delete.deleteBooking(newBooking.getBookingId(), randomString(9));
        assertThat(response.statusCode(), is(403));
    }
}
