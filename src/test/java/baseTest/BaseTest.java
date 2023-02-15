package baseTest;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import test_conf.props.TestConfig;
import java.util.HashMap;
import java.util.Map;
import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class BaseTest {

    public Map<String,String> successfulCardWithout3Ds1 = Map.of(
            "PAN", "5218851946955484",
            "SecureCode", "123",
            "EMonth", "12",
            "EYear", "25");

    public Map<String,String> unSuccessfulCardWithout3Ds1 = Map.of(
            "PAN", "4400000000000008",
            "SecureCode", "123",
            "EMonth", "12",
            "EYear", "19");
    public String key = "Merchant";
    public String getRandomUniqueOrderId(String merchant) {
        String uniqueOrderId = "";
        int randomStringLength;
        installSpecification(requestSpec(), responseSpec200());
        while(uniqueOrderId.equals("")) {
            randomStringLength = (int) (Math.random() * 50) + 1;
            String randomOrderId = randomAlphanumeric(randomStringLength);
            Map<String, Object> body = new HashMap<>();
            body.put("OrderId", randomOrderId);
            body.put("Key",merchant);
            Response resp =
                    given()
                            .formParams(body)
                            .when()
                            .get(TestConfig.PATH.Value + "GetState")
                            .then().log().all()
                            .extract().response();
            String stringResponse = resp.asString();
            XmlPath xmlPath = new XmlPath(stringResponse);
            String success = xmlPath.get("GetState.@Success");
            if(success.equals("False")) uniqueOrderId = randomOrderId;
        }
        return uniqueOrderId;
    }

    public String getRandomInt(){
        return Integer.toString((int) (Math.random() * 1000000000) + 1);
    }
}
