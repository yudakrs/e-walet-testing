package G2.Project.Test.TS10;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_ConfirmMerchantTopup_03 extends TestBase {
    @SuppressWarnings("unchecked")
    @BeforeClass
    void SettingUp() {
        logger.info("***** "+ getClass().getName() + " ******");
        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        token = loadToken();
        httpRequest.header("Authorization","Bearer "+token);

        response = httpRequest.request(Method.GET, "/confirm-merchant-topup/"+Setting.GetPay()+"/200");
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
