package baseTest;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import test_conf.props.TestConfig;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class BaseTest {

    public static final Map<String,String> successfulCardWithout3Ds1 = Map.of(
            "PAN", "5218851946955484",
            "SecureCode", "123",
            "EMonth", "12",
            "EYear", "25");

    public static final Map<String,String> unSuccessfulCardWithout3Ds1 = Map.of(
            "PAN", "4400000000000008",
            "SecureCode", "123",
            "EMonth", "12",
            "EYear", "19");
    public static final String key = "Merchant";

    public Transaction sendGetBlockRequest(Map<String,Object> data) {
        Response getResp = given()
                .formParams(data)
                .when()
                .get(TestConfig.PATH.Value + "Block")
                .then().log().all()
                .extract().response();
        String stringGetResponse = getResp.asString();
        return new Transaction(data, new XmlPath(stringGetResponse));
    }

    public Transaction sendPostBlockRequest(Map<String,Object> data) {
        Response getResp = given()
                .formParams(data)
                .when()
                .post(TestConfig.PATH.Value + "Block")
                .then().log().all()
                .extract().response();
        String stringPostResponse = getResp.asString();
        return new Transaction(data, new XmlPath(stringPostResponse));
    }

    public String getRandomOrderId() {
        return randomAlphanumeric((int) (Math.random() * 50) + 1);
    }

    public String getRandomInt(){
        return Integer.toString((int) (Math.random() * 1000000000) + 1);
    }
}
