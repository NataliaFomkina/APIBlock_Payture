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
@Feature(value = "Успешное блокирование без 3Ds")
public class SuccessfulWithout3DsTest extends BaseTest {

    @Test (description = "TestCase № 2. Успешное блокирование средств при корректном заполнении всех обязательных параметров и отсутствии необязательных")
    public void mandatoryFieldsWithoutOptionalTest(){
        installSpecification(requestSpec(), responseSpec200());
        Map<String, Object> body = new HashMap<>();
        body.put("Key", key);
        body.put("OrderId", getRandomUniqueOrderId(key));
        body.put("Amount", getRandomInt());
        body.put("PayInfo", "PAN=" + successfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=25; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath xmlPathPost = sendPostBlockRequest(body);
        assertEquals(xmlPathPost.get("Block.@Success"), "True", "Метод POST. Параметр Success не соответствует ожидаемому");
        assertEquals(xmlPathPost.get("Block.@OrderId"), body.get("OrderId"), "Метод POST. Параметр OrderId не соответствует ожидаемому");
        assertEquals(xmlPathPost.get("Block.@Key"), body.get("Key"), "Метод POST. Параметр Key не соответствует ожидаемому");
        assertEquals(xmlPathPost.get("Block.@Amount"), body.get("Amount"), "Метод POST. Параметр Amount не соответствует ожидаемому");

        body.put("OrderId", getRandomUniqueOrderId(key));
        body.put("PayInfo", "PAN=" + successfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=25; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath xmlPathGet = sendGetBlockRequest(body);
        assertEquals(xmlPathGet.get("Block.@Success"), "True", "Метод GET. Параметр Success не соответствует ожидаемому");
        assertEquals(xmlPathGet.get("Block.@OrderId"), body.get("OrderId"), "Метод GET. Параметр OrderId не соответствует ожидаемому");
        assertEquals(xmlPathGet.get("Block.@Key"), body.get("Key"), "Метод GET. Параметр Key не соответствует ожидаемому");
        assertEquals(xmlPathGet.get("Block.@Amount"), body.get("Amount"), "Метод GET. Параметр Amount не соответствует ожидаемому");
    }
}
