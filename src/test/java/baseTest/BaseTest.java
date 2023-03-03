package baseTest;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import test_conf.props.TestConfig;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class BaseTest {
    public static final Card successfulCardWithout3Ds1 = new Card("5218851946955484", "123", "12", "25", "Ivan Ivanov");
    public static final Card unSuccessfulCardWithout3Ds1 = new Card("4400000000000008", "123", "12", "19", "Ivan Ivanov");
    public static final String key = "Merchant";

    public Transaction sendPostBlockRequest(Order order) {
        Response getResp = given()
                .formParams(order.convertToHashMap())
                .when()
                .post(TestConfig.PATH.Value + "Block")
                .then().log().all()
                .extract().response();
        String stringPostResponse = getResp.asString();
        return new Transaction(order, new XmlPath(stringPostResponse));
    }

    public Transaction sendGetBlockRequest(Order order) {
        Response getResp = given()
                .formParams(order.convertToHashMap())
                .when()
                .get(TestConfig.PATH.Value + "Block")
                .then().log().all()
                .extract().response();
        String stringPostResponse = getResp.asString();
        return new Transaction(order, new XmlPath(stringPostResponse));
    }

    public PayInfo generatePayInfo(Card card) {
        return new PayInfo()
                .setPAN(card.getPAN())
                .setOrderId(getRandomOrderId())
                .setAmount(getRandomInt())
                .setEMonth(card.getEMonth())
                .setEYear(card.getEYear())
                .setCardHolder(card.getCardHolder())
                .setSecureCode(card.getSecureCode());
    }
    public String getRandomOrderId() {
        return randomAlphanumeric((int) (Math.random() * 50) + 1);
    }

    public String getRandomInt(){
        return Integer.toString((int) (Math.random() * 1000000000) + 1);
    }
}
