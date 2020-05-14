package G2.Project.Test.TS02;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_Login_10 extends TestBase {
    @SuppressWarnings("unchecked")
    @BeforeClass
    void setUpTCLogin10() {
        logger.info("***** " + getClass().getName() + " *****");

        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.POST, "/login");

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
