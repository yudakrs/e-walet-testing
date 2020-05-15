package G2.Project.Test.TS02;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_Login_05 extends TestBase {
    @SuppressWarnings("unchecked")
    @BeforeClass
    @Parameters({"invalid_username", "registered_password"})
    void setUpTCLogin05(String username, String password) {
        logger.info("***** " + getClass().getName() + " *****");

        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("username", username);
        requestParams.put("password", password);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/login");

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

    @Test
    void checkHeader() {
        checkContentType();
    }
}
