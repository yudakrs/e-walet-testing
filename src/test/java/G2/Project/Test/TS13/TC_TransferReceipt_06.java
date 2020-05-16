package G2.Project.Test.TS13;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class TC_TransferReceipt_06 extends Setting {
    @BeforeClass
    void SettingUp() {
        RestAssured.baseURI = BaseURI;
        httpRequest = RestAssured.given();

        httpRequest.multiPart("transfer_receipt", new File("File/ImageLessThan2MB.jpg"));

        token = loadToken();
        httpRequest.header("Authorization","Bearer "+token);
        httpRequest.header("Content-Type", "multipart/form-data");

        response = httpRequest.request(Method.POST, "upload-transfer-receipt/628132561832");

        responseBody = response.getBody().asString();
    }

    @Test
    void checkResponseBody() {
        checkBody(true);
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
        checkResponseTime("2000");
    }

    @Test
    void checkHeader() {
        checkContentType();
    }
}
