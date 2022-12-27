package api.constants;

public class Constants {

    //Comment : at first thought to create url factory, but it was bigger then all code all together, so no point to do it here
    public static class Urls {

        public static final String MAIN_PAGE = "https://restful-booker.herokuapp.com/";

        //Authentication
        public static final String AUTHENTICATION = ""+MAIN_PAGE+"auth";

        //GetBookingIds
        //All ids
        public static final String GET_BOOKING_IDS_ALL = ""+MAIN_PAGE+"booking";
        //Filter by name
        public static String GET_BOOKING_IDS_BY_NAME(String firstName, String lastName){
            return ""+MAIN_PAGE+"booking?firstname="+firstName+"&lastname="+lastName+"";}
        //Filter by checkIn/checkOut dateTime
        public static String GET_BOOKING_IDS_BY_CHECKIN_CHECKOUT(String checkIn, String checkOut){
            return ""+MAIN_PAGE+"booking?checkin="+checkIn+"&checkout="+checkOut+"";}

        //Get booking/Partial update booking/Delete booking
        public static String BOOKING_BY_ID(Integer id){
            return ""+MAIN_PAGE+"booking/"+id+"";}

        //Create booking
        public static final String CREATE_BOOKING = ""+MAIN_PAGE+"booking";

    }

    public static class Variables {
        public static final String firstName = "firstName";
        public static final String lastName = "lastName";
        public static final String newFirstName = "newFirstName";
        public static final String newLastName = "newLastName";
        public static final String wrongFirstName = "wrongFirstName";
        public static final String wrongLastName = "wrongLastName";

        public static final Integer price = 111;
        public static final Integer newPrice = 222;

        public static final String checkIn = "2022-01-01";
        public static final String checkOut = "2022-01-02";
        public static final String newCheckIn = "2023-01-01";
        public static final String newCheckOut = "2023-01-02";
        public static final String wrongCheckIn = "1988-01-01";
        public static final String wrongCheckOut = "1988-01-02";

        public static final String additionalNeeds = "Breakfast";
        public static final String newAdditionalNeeds = "Beer";


        public static final String login = "admin";
        public static final String password = "password123";

    }
}
