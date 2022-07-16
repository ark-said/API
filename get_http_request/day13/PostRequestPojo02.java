package get_http_request.day13;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestPojo02 extends HerOkuAppBaseUrl {
   /*
 https://restful-booker.herokuapp.com/booking
 request body
 { "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-03-01",
                "checkout": "2022-03-11"
             }
 }}
Status code is 200
 response body
  {
 "bookingid": 11,
        "booking": {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 500,
                "depositpaid": true,
                "bookingdates": {
                                "checkin": "2022-03-01",
                                "checkout": "2022-03-11"
                          }
                      }
                  }
 */

    @Test
    public void test(){

        //1) URL OLUSTUR
        spec05.pathParam("bir", "booking");

        //2)Expected data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2022-03-01", "2022-03-11");
        System.out.println("bookingDates = " + bookingDates);

        BookingPojo bookingPojo = new BookingPojo("Ali","Can",500,true, bookingDates);
        System.out.println("bookingPojo = " + bookingPojo);

        // 3 Request response
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec05)
                .auth()
                .basic("admin","password123")
                .body(bookingPojo)
                .post("/{bir}");
        response.prettyPeek();

        // 4 dogrulama

        BookingResponsePojo actualData=response.as(BookingResponsePojo.class);
        assertEquals(200,response.statusCode());

        assertEquals(bookingPojo.getFirstName(),actualData.getBooking().getFirstName());
        assertEquals(bookingPojo.getLastName(),actualData.getBooking().getLastName());
        assertEquals(bookingPojo.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(bookingPojo.isDepositpaid(),actualData.getBooking().isDepositpaid());

        assertEquals(bookingPojo.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingPojo.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
    }
}
