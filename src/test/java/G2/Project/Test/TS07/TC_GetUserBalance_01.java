package G2.Project.Test.TS07;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_GetUserBalance_01 extends TestBase {
    @BeforeClass
    void setUpTCGetUserBalance01() {
        logger.info("***** " + getClass().getName() + " *****");

        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        String userToken = loadToken();
        httpRequest.header("Authorization", "Bearer " + userToken);
        response = httpRequest.request(Method.GET, "/get-user-balance");

        responseBody = response.getBody().asString();
    }

    @Test
    void checkResponseBody(){
        checkBody(false);
    }

    @Test
    void checkDataBody(){
        JsonPath data = response.jsonPath();
        int amount = data.get("amount");

        Assert.assertTrue(amount >= 0);
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
