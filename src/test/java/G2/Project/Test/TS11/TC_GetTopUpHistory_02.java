package G2.Project.Test.TS11;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_GetTopUpHistory_02 extends TestBase {
    @BeforeClass
    @Parameters({"invalid_token"})
    void settingUp(String invalidtoken){
        logger.info("***** " + getClass().getName() + " *****");

        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        httpRequest.header("Authorization", "Bearer " + invalidtoken);
        response = httpRequest.request(Method.GET, "/get-topup-history");

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
        checkStatusLine("HTTP/1.1 401 ");
    }

    @Test
    void checkResponseTime() {
        checkResponseTime("2000");
    }
}
