package G2.Project.Test.TS06;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_GetUserProfile_02 extends TestBase {
    @BeforeClass
    @Parameters({"invalid_token"})
    void setUpTCGetUserProfile02(String token) {
        logger.info("***** " + getClass().getName() + " *****");

        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        httpRequest.header("Authorization", "Bearer " + token);
        response = httpRequest.request(Method.GET, "/get-user-profile");

        responseBody = response.getBody().asString();
    }

    @Test
    void checkResponseBody(){
        checkBody(true);
    }

    @Test
    void checkStatusCode() {
        checkStatusCode("401");
    }

    @Test
    void checkStatusLine() {
        checkStatusLine("HTTP/1.1 401 Unauthorized");
    }

    @Test
    void checkResponseTime() {
        checkResponseTime("2000");
    }

    @Test
    void checkHeader() {
        checkContentType();
    }

    @Test
    void checkLength() {
        checkContentLength("100");
    }
}
