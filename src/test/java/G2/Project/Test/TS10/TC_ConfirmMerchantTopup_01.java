package G2.Project.Test.TS10;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_ConfirmMerchantTopup_01 extends Setting {
    @SuppressWarnings("unchecked")
    @BeforeClass
    void SettingUp() {
        logger.info("***** "+ getClass().getName() + " ******");
        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        token = loadToken();
        httpRequest.header("Authorization","Bearer "+token);

        response = httpRequest.request(Method.GET, "/confirm-merchant-topup/"+paytoken+"/"+invoice);
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
