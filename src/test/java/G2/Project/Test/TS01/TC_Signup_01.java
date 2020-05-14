package G2.Project.Test.TS01;

import G2.Project.Base.TestBase;
import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.ITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_Signup_01 extends TestBase {
    @BeforeClass
    void SettingUp(){
        logger.info("# "+ getClass().getName() +" #");
        RestAssured.baseURI = BaseURI;


    }
}
