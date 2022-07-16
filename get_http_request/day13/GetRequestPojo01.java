package get_http_request.day13;

import base_url.DummyBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DataPojo;
import pojos.DummyPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequestPojo01 extends DummyBaseUrl {
     /*
GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
                           Status code is 200
{
 "status": "success",
 "data": {
   "id": 1,
   "employee_name": "Tiger Nixon",
   "employee_salary": 320800,
   "employee_age": 61,
   "profile_image": ""
   },
 "message": "Successfully! Record has been fetched."
 }
*/

    // calismazsa pojoslara bunu koyabiliriz
     // @JsonIgnoreProperties(ignoreUnknown = true)

    @Test
    public void test(){
        //1 URL
        // http://dummy.restapiexample.com/api/v1/employee/1
        spec02.pathParams("1", "api", "2", "v1", "3", "employee", "4", 1);
        //2 expected
        DataPojo dataPojo=new DataPojo(1,"Tiger Nixon",320800,61,"");
        DummyPojo expectedData=new DummyPojo("success",dataPojo,"Successfully! Record has been fetched.");
        System.out.println("expectedData = " + expectedData);
        //reponse
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec02)
                .when()
                .get("/{1}/{2}/{3}/{4}");
        response.prettyPrint();
        //dogrulama
        DummyPojo actualData=response.as(DummyPojo.class);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());


        // Serialization -> Java yapisindaki datayi JSON formatina donusturme
        Gson gson=new Gson();
        String jsonFromJava=gson.toJson(actualData);
        System.err.println("jsonFromJava = " + jsonFromJava);
        //  System.err.println .err kirmizi yazdirir
        //jsonFromJava =
        // {"status":"success",
        // "data":{"id":1,
        // "employee_name":"Tiger Nixon",
        // "employee_salary":320800,"employee_age":61,
        // "profile_image":""},
        // "message":"Successfully! Record has been fetched."}

    }
}
