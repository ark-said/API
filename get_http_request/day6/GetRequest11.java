package get_http_request.day6;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest11 {

    String endPoint= "http://www.gmibank.com/api/tp-customers";
    String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDQ0YXBpIiwiYXV0aCI6IlJPTEVfQ1VTVE9NRVIiLCJleHAiOjE2NTUxMTU1NjV9.-4tGYlhXvcEk0rPruOhtzLSRrS5HOxob7C3JoRrWHliUio6u6K7hRRmC1eWW_Os9XhUK6Vn4QHV7WCMvy3cfRg";

    @Test
    public void test11(){

        Response response = given().accept(ContentType.JSON)
                .header("Authorization", "Bearer " + bearerToken)
                .when().get(endPoint).then().extract().response();

        response.prettyPrint();
    }
}
