package G2.Project.Test.TS12;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_TopUpBalance_04 extends TestBase {
    @SuppressWarnings("unchecked")
    @BeforeClass
    @Parameters({"phone_number_registered", "code_invalid", "payment_id_valid"})
    void SettingUp(String phone_number, String code, String payment_id) {
        token = loadToken();

        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        httpRequest.header("Authorization", "Bearer "+token);
        httpRequest.header("Content-Type", "application/json");

        requestParams.put("phone_number",phone_number);
        requestParams.put("code",code);
        requestParams.put("payment_method_id",payment_id);

        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.POST, "/topup-balance");
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
        checkStatusCode("400");
    }

    @Test
    void checkStatusLine() {
        checkStatusLine("HTTP/1.1 400 ");
    }

    @Test
    void checkResponseTime() {
        checkResponseTime("1000");
    }

    @Test
    void checkHeader() {
        checkContentType();
    }

}
