package G2.Project.Base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.PropertyConfigurator;
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
}
