package block;

import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.path.xml.XmlPath;
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
        body.put("OrderId", getRandomUniqueOrderId(key));
        body.put("Amount", getRandomInt());
        body.put("PayInfo", "PAN=" + successfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=25; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath postResponse = sendPostBlockRequest(body);
        verifySuccessfulWithout3DSPostResponse(postResponse, body);

        body.put("OrderId", getRandomUniqueOrderId(key));
        body.put("PayInfo", "PAN=" + successfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=25; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath getResponse = sendGetBlockRequest(body);
        verifySuccessfulWithout3DSGetResponse(getResponse, body);
    }
}
