package G2.Project.Test.TS10;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_ConfirmMerchantTopup_01 extends TestBase {
    @SuppressWarnings("unchecked")
    @BeforeClass
    void SettingUp() {
        logger.info("***** "+ getClass().getName() + " ******");
        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        requestParams.put("phone_number","6281325618362");
        requestParams.put("code","E-20");
        requestParams.put("payment_method_id","2");

        httpRequest.body(requestParams.toJSONString());
        httpRequest.header("Content-Type","application/json");
        token = loadToken();
        httpRequest.header("Authorization","Bearer "+token);

        response = httpRequest.request(Method.GET, "/confirm-merchant-topup/"+Setting.GetPay()+"/"+Setting.GetId());
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
        checkStatusCode("200");
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
