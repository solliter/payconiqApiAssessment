package api.entities.update;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class BookingUpdateResponse {

    @SerializedName("firstname")
    @Expose
    private String firstName;

    @SerializedName("lastname")
    @Expose
    private String lastName;

    @SerializedName("totalprice")
    @Expose
    private Integer totalPrice;

    @SerializedName("depositpaid")
    @Expose
    private Boolean depositPaid;

    @SerializedName("bookingdates")
    @Expose
    private BookingDates bookingDates;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class BookingDates {
        @SerializedName("checkin")
        @Expose
        private String checkIn;

        @SerializedName("checkout")
        @Expose
        private String checkOut;

    }
    @SerializedName("additionalneeds")
    @Expose
    private String additionalNeeds;
}
