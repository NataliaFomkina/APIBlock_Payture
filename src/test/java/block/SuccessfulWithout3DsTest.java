package block;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import java.util.*;
import static api.Specifications.*;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Успешное блокирование без 3Ds")
public class SuccessfulWithout3DsTest extends BaseTest {

    @Test (description = "TestCase № 2. Успешное блокирование средств при корректном заполнении всех обязательных параметров и отсутствии необязательных")
    public void mandatoryFieldsWithoutOptionalTest(){
        installSpecification(requestSpec(), responseSpec200());
        Map<String, Object> body = new HashMap<>();
        body.put("Key", key);
        body.put("OrderId", getRandomOrderId());
        body.put("Amount", getRandomInt());
        body.put("PayInfo", "PAN=" + successfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=25; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        sendPostBlockRequest(body).verifySuccessfulResponse();

        body.put("OrderId", getRandomOrderId());
        body.put("PayInfo", "PAN=" + successfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=25; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        sendGetBlockRequest(body).verifySuccessfulResponse();
    }
}
