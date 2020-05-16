package G2.Project.Test.TS13;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TC_TransferReceipt_02 extends Setting {
    @BeforeClass
    void SettingUp() {
        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        httpRequest.multiPart("transfer-receipt", new File("File/Foto3MB.png"));

        token = loadToken();
        httpRequest.header("Authorization","Bearer "+token);
        httpRequest.header("Content-Type", "multi-part/form-data");

        response = httpRequest.request(Method.POST, "upload-transfer-receipt/"+paytoken);

        responseBody = response.getBody().asString();
    }

    @Test
    void checkResponseBody() {
        checkBody(false);
    }

    @Test
    void checkStatusCode() {
        checkStatusCode("406");
    }

    @Test
    void checkStatusLine() {
        checkStatusLine("HTTP/1.1 406 ");
    }

    @Test
    void checkResponseTime() {
        checkResponseTime("5000");
    }

    @Test
    void checkHeader() {
        checkContentType();
    }
}
