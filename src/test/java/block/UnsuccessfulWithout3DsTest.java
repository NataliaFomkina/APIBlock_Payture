package block;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;
import java.util.*;
import static api.Specifications.*;
import static org.testng.Assert.assertEquals;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Неуспешное блокирование без 3Ds")
public class UnsuccessfulWithout3DsTest extends BaseTest {

    @Test (description = "TestCase № 8. Выдача ошибки WRONG_EXPIRE_DATE ")
    public void wrongExpireDateTest(){
        String OrderId = getRandomUniqueOrderId(key);
        installSpecification(requestSpec(), responseSpec200());
        Map<String, Object> body = new HashMap<>();
        body.put("Key", key);
        body.put("OrderId", OrderId);
        body.put("Amount", getRandomInt());
        body.put("PayInfo", "PAN=" + unSuccessfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=19; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath xmlPathPost = sendPostBlockRequest(body);
        assertEquals(xmlPathPost.get("Block.@Success"), "False", "Метод POST. Параметр Success не соответствует ожидаемому");
        assertEquals(xmlPathPost.get("Block.@OrderId"), body.get("OrderId"), "Метод POST. Параметр OrderId не соответствует ожидаемому");
        assertEquals(xmlPathPost.get("Block.@Key"), body.get("Key"), "Метод POST. Параметр Key не соответствует ожидаемому");
        assertEquals(xmlPathPost.get("Block.@ErrCode"), "WRONG_EXPIRE_DATE", "Метод POST. Параметр ErrCode не соответствует ожидаемому");

        OrderId = getRandomUniqueOrderId(key);
        body.put("OrderId", OrderId);
        body.put("PayInfo", "PAN=" + unSuccessfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=19; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath xmlPathGet = sendGetBlockRequest(body);
        assertEquals(xmlPathGet.get("Block.@Success"), "False", "Метод GET. Параметр Success не соответствует ожидаемому");
        assertEquals(xmlPathGet.get("Block.@OrderId"), body.get("OrderId"), "Метод GET. Параметр OrderId не соответствует ожидаемому");
        assertEquals(xmlPathGet.get("Block.@Key"), body.get("Key"), "Метод GET. Параметр Key не соответствует ожидаемому");
        assertEquals(xmlPathGet.get("Block.@ErrCode"), "WRONG_EXPIRE_DATE", "Метод GET. Параметр ErrCode не соответствует ожидаемому");
    }
}
