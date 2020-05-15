package G2.Project.Test.TS10;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeTest;

public class Setting extends TestBase {
    protected static String paytoken;
    protected static int invoice;
    @SuppressWarnings("unchecked")
    @BeforeTest
    void SettingUp() {
        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        requestParams.put("phone_number", "6281905218958");
        requestParams.put("code", "E-20");
        requestParams.put("payment_method_id", "2");

        httpRequest.body(requestParams.toJSONString());
        httpRequest.header("Content-Type", "application/json");
        token = loadToken();
        httpRequest.header("Authorization", "Bearer "+token);

        response = httpRequest.request(Method.POST, "/topup-balance");

        JsonPath resp = response.jsonPath();
        paytoken = resp.get("data.token");
        invoice = resp.get("data.invoice_id");
    }

    public static String GetPay() {
        return paytoken;
    }

    public static int GetId() {
        return invoice;
    }
}
