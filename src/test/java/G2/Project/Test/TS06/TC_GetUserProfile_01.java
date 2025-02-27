package G2.Project.Test.TS06;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_GetUserProfile_01 extends TestBase {
    @BeforeClass
    void setUpTCGetUserProfile01() {
        logger.info("***** " + getClass().getName() + " *****");

        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        String userToken = loadToken();

        httpRequest.header("Authorization", "Bearer " + userToken);
        response = httpRequest.request(Method.GET, "/get-user-profile");

        responseBody = response.getBody().asString();
    }

    @Test
    void checkResponseBody(){
        checkBody(false);
    }

    @Test
    void checkDataBody(){
        checkData(false);
    }

    @Test
    void checkStatusCode() {
        checkStatusCode("200");
    }

    @Test
    void checkStatusLine() {
        checkStatusLine("HTTP/1.1 200 ");
    }

    @Test
    void checkResponseTime() {
        checkResponseTime("2000");
    }

    @Test
    void checkHeader() {
        checkContentType();
    }

}
