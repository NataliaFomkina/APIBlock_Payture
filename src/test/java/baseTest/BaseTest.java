package baseTest;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import test_conf.props.TestConfig;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.testng.Assert.assertEquals;

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

    public XmlPath sendGetBlockRequest(Map<String,Object> data) {
        Response getResp = given()
                .formParams(data)
                .when()
                .get(TestConfig.PATH.Value + "Block")
                .then().log().all()
                .extract().response();
        String stringGetResponse = getResp.asString();
        return new XmlPath(stringGetResponse);
    }

    public XmlPath sendPostBlockRequest(Map<String,Object> data) {
        Response getResp = given()
                .formParams(data)
                .when()
                .post(TestConfig.PATH.Value + "Block")
                .then().log().all()
                .extract().response();
        String stringPostResponse = getResp.asString();
        return new XmlPath(stringPostResponse);
    }

    public void verifySuccessfulWithout3DSGetResponse(XmlPath response, Map<String, Object> body) {
        assertEquals(response.get("Block.@Success"), "True", "Метод GET. Параметр Success не соответствует ожидаемому");
        assertEquals(response.get("Block.@OrderId"), body.get("OrderId"), "Метод GET. Параметр OrderId не соответствует ожидаемому");
        assertEquals(response.get("Block.@Key"), body.get("Key"), "Метод GET. Параметр Key не соответствует ожидаемому");
        assertEquals(response.get("Block.@Amount"), body.get("Amount"), "Метод GET. Параметр Amount не соответствует ожидаемому");
    }

    public void verifySuccessfulWithout3DSPostResponse(XmlPath response, Map<String, Object> body) {
        assertEquals(response.get("Block.@Success"), "True", "Метод POST. Параметр Success не соответствует ожидаемому");
        assertEquals(response.get("Block.@OrderId"), body.get("OrderId"), "Метод POST. Параметр OrderId не соответствует ожидаемому");
        assertEquals(response.get("Block.@Key"), body.get("Key"), "Метод POST. Параметр Key не соответствует ожидаемому");
        assertEquals(response.get("Block.@Amount"), body.get("Amount"), "Метод POST. Параметр Amount не соответствует ожидаемому");
    }

    public void verifyUnsuccessfulWithout3DSGetResponse(XmlPath response, Map<String, Object> body, ErrorCodes error) {
        assertEquals(response.get("Block.@Success"), "False", "Метод GET. Параметр Success не соответствует ожидаемому");
        assertEquals(response.get("Block.@OrderId"), body.get("OrderId"), "Метод GET. Параметр OrderId не соответствует ожидаемому");
        assertEquals(response.get("Block.@Key"), body.get("Key"), "Метод GET. Параметр Key не соответствует ожидаемому");
        assertEquals(response.get("Block.@ErrCode"), error.toString(), "Метод GET. Параметр ErrCode не соответствует ожидаемому");
    }

    public void verifyUnsuccessfulWithout3DSPostResponse(XmlPath response, Map<String, Object> body, ErrorCodes error) {
        assertEquals(response.get("Block.@Success"), "False", "Метод POST. Параметр Success не соответствует ожидаемому");
        assertEquals(response.get("Block.@OrderId"), body.get("OrderId"), "Метод POST. Параметр OrderId не соответствует ожидаемому");
        assertEquals(response.get("Block.@Key"), body.get("Key"), "Метод POST. Параметр Key не соответствует ожидаемому");
        assertEquals(response.get("Block.@ErrCode"), error.toString(), "Метод POST. Параметр ErrCode не соответствует ожидаемому");
    }

    public String getRandomOrderId() {
        return randomAlphanumeric((int) (Math.random() * 50) + 1);
    }

    public String getRandomInt(){
        return Integer.toString((int) (Math.random() * 1000000000) + 1);
    }
}
