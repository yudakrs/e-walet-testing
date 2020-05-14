package G2.Project.Base;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.logging.Logger;

public class TestBase {
    protected static RequestSpecification httpRequest;
    protected static Response response;
    protected String BaseURI = null; //nanti diganti kalo dah ada linknya
    protected String responseBody;
    protected JSONObject requestParams;
    protected String token;

    protected Logger logger;

    @BeforeClass
    public void setup() {
        logger=Logger.getLogger("e-Walet");
        PropertyConfigurator.configure("Log4j.properties");
    }

    // Ini kalo di response ada data kasih true, kalo ga ada data kasih false
    public void checkData(Boolean isNull) {
        logger.info("***** Check Data Field *****");

        JsonPath jsondata = response.jsonPath();
        String data = jsondata.get("data");
        logger.info("Data = " + data);
        Assert.assertTrue(data.isEmpty() == isNull);
    }

    public void checkBody(boolean isNull) {
        logger.info("***** Check Body Data *****");

        Assert.assertTrue(responseBody.isEmpty() == isNull);
    }

    // Check di header ada token atau tidak
    public void checkToken(String token) {
        logger.info("***** Check Token in Response ******");

        String headertoken = response.header("Authorization");
        logger.info("Token = " + headertoken);
        Assert.assertEquals(token, headertoken);
    }

    public void checkStatusCode(String sc) {
        logger.info("***** Check Status Code *****");

        int statusCode = response.getStatusCode();
        logger.info("Status Code = " + statusCode);
        Assert.assertEquals(statusCode, Integer.parseInt(sc));
    }

    public void checkResponseTime(String rt) {
        logger.info("***** Check Response Time *****");

        long responseTime = response.getTime();
        logger.info("Response Time = " + responseTime);
        Assert.assertTrue(responseTime<Long.parseLong(rt));
    }

    public void checkStatusLine(String sl) {
        logger.info("***** Check Status Line *****");

        String statusLine = response.getStatusLine();
        logger.info("Status Line = " + statusLine);
        Assert.assertEquals(statusLine, sl);
    }

    public void checkContentType() {
        logger.info("***** Check Content Type *****");

        String contentType = response.header("Content-Type");
        Assert.assertEquals(contentType, "application/json");
    }

    public void checkContentLength(String cl) {
        logger.info("***** Check Content Length *****");

        String contentLength = response.header("Content-Length");
        logger.info("Content Encoding = " + contentLength);

        int contLeng = Integer.parseInt(contentLength);
        int contLengParam = Integer.parseInt(cl);

        if(contLeng < contLengParam)
        {
            logger.warning("Content Length is less than " + contLengParam);
        }

        Assert.assertTrue(contLeng>contLengParam);
    }

    public String loadToken(){
        String tokenuser = "";
        try {
            FileReader fr = new FileReader("Token/SDETBEUserToken.txt");
            BufferedReader br = new BufferedReader(fr);

            tokenuser = br.readLine();
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenuser;
    }
}
