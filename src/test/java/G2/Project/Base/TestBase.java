package G2.Project.Base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;

import java.util.logging.Logger;

public class TestBase {
    protected static RequestSpecification httpRequest;
    protected static Response response;
    protected String BaseURI = null;

    protected Logger logger;

    @BeforeClass
    public void setup() {
        logger=Logger.getLogger("e-Walet");
        PropertyConfigurator.configure("Log4j.properties");
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

    public void checkContentType(String ct) {
        logger.info("***** Check Content Type *****");

        String contentType = response.header("Content-Type");
        logger.info("Content Type = " + contentType);
        Assert.assertEquals(contentType, ct);
    }

    public void checkServerType(String st) {
        logger.info("***** Check Server Type *****");

        String serverType = response.header("Server");
        logger.info("Server Type = " + serverType);
        Assert.assertEquals(serverType, st);
    }

    public void checkContentEncoding(String ce) {
        logger.info("***** Check Content Encoding *****");

        String contentEncoding = response.header("Content-Encoding");
        logger.info("Content Encoding = " + contentEncoding);
        Assert.assertEquals(contentEncoding, ce);
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

    public void checkCookies(String ck) {
        logger.info("***** Check Cookies *****");
        String cookie = response.getCookie(ck);
        logger.info("Cookie = " + cookie);
    }

    public void tearDown(String message) {
        logger.info("***** " + message + " *****");
    }
}
