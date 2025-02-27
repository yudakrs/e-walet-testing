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
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestBase {
    protected static RequestSpecification httpRequest;
    protected static Response response;
    // protected String BaseURI = "http://336f1b2a.ngrok.io"; //nanti diganti kalo dah ada linknya
    protected String BaseURI = "http://ec2-54-224-208-37.compute-1.amazonaws.com:9701";
    protected String responseBody;
    protected JSONObject requestParams = new JSONObject();
    protected String token;

    protected Logger logger;

    @BeforeClass
    public void setup() {
        logger=Logger.getLogger("e-Walet");
        PropertyConfigurator.configure("Log4j.properties");

        try {
            FileHandler fh = new FileHandler("logs/restAPI.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Ini kalo di response ada data kasih false, kalo ga ada data kasih true
    public void checkData(Boolean isNull) {
        logger.info("***** Check Data Field *****");

        JsonPath jsondata = response.jsonPath();
        String data = jsondata.get("data").toString();
        logger.info("Data = " + data);
        Assert.assertTrue(data.isEmpty() == isNull);
    }

    public void checkBody(boolean isNull) {
        logger.info("***** Check Body Data *****");

        Assert.assertTrue(responseBody.isEmpty() == isNull);
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
