package G2.Project.Test.TS01;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_Signup_04 extends TestBase {
    @SuppressWarnings("unchecked")
    @BeforeClass
    @Parameters({"first_name_valid", "last_name_valid", "phone_unregistered", "email_invalid", "pass_invalid"})
    void SettingUp(String first_name, String last_name, String phone, String email, String pass){
        logger.info("# "+ getClass().getName() +" #");
        RestAssured.baseURI = BaseURI;

        httpRequest = RestAssured.given();
        requestParams.put("first_name", first_name);
        requestParams.put("last_name", last_name);
        requestParams.put("email", email);
        requestParams.put("phone_number", phone);
        requestParams.put("password", pass);

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/sign-up");

        responseBody = response.getBody().asString();
    }

    @Test
    void checkResponseBody(){
        checkBody(false);
    }

    @Test
    void checkDataBody(){
        checkData(true);
    }

    @Test
    void checkStatusCode() {
        checkStatusCode("400");
    }

    @Test
    void checkStatusLine() {
        checkStatusLine("HTTP/1.1 400 Bad Request");
    }

    @Test
    void checkResponseTime() {
        checkResponseTime("1000");
    }

    @Test
    void checkHeader() {
        checkContentType();
    }

    @Test
    void checkLength() {
        checkContentLength("10");
    }
}
