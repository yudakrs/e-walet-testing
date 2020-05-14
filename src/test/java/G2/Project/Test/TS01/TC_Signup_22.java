package G2.Project.Test.TS01;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_Signup_22 extends TestBase {
    @SuppressWarnings("unchecked")
    @BeforeClass
    void SettingUp(){
        logger.info("# "+ getClass().getName() +" #");
        RestAssured.baseURI = BaseURI;

        httpRequest = RestAssured.given();
        requestParams.put("first_name", null);
        requestParams.put("last_name", null);
        requestParams.put("email", null);
        requestParams.put("phone_number", null);
        requestParams.put("password", null);

        httpRequest.header("Content-Type", "application/json");

        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/sign-up");

        responseBody = response.getBody().asString();
    }

    @Test
    void checkResponseBody(){
        checkBody();
    }

    @Test
    void checkDataBody(){
        checkData(false);
    }

    @Test
    void checkStatusCode() {
        checkStatusCode("201");
    }

    @Test
    void checkStatusLine() {
        checkStatusLine("HTTP/1.1 200 OK");
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
