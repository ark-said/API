package get_http_request.day6;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest14 extends GMIBankBaseUrl {

     /*
    http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın
    "firstName": "Melva",
    "lastName": "Bernhard",
    "email": "chas.kuhlman@yahoo.com"
    "zipCode": "40207"
    "country" "name": "San
    "login": "delilah.metz"
     */

    @Test
    public void test14(){

        spec03.pathParams("bir", "tp-customers", "iki", "114351");

        //http://www.gmibank.com/api
        Response response=given()
                .spec(spec03)
                .header("Authorization", "Bearer " + generateToken())
                .when().get("/{bir}/{iki}");
        // "/{bir}/{iki}" -> /tp-customers/114351

        response.prettyPrint();

        //MATCHERS CLASS iLE
        response.then().assertThat()
                .body("firstName", equalTo("Della")
                        , "lastName", equalTo("Heaney")
                        , "email", equalTo("ricardo.larkin@yahoo.com")
                        , "zipCode", equalTo("53081")
                        , "country.name", equalTo("USA")
                        , "user.login", equalTo(""));

        //JSON PATH iLE
        JsonPath json = response.jsonPath();
        Assert.assertEquals("Melva", json.getString("firstName"));
        Assert.assertEquals("Bernhard", json.getString("lastName"));
        Assert.assertEquals("chas.kuhlman@yahoo.com", json.getString("email"));
        Assert.assertEquals("40207", json.getString("zipCode"));
        Assert.assertEquals("San Jose", json.getString("country.name"));
        Assert.assertEquals("delilah.metz", json.getString("user.login"));
    }
}
