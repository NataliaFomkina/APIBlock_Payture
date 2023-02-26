package block;

import baseTest.BaseTest;
import baseTest.ErrorCodes;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.path.xml.XmlPath;
import org.testng.annotations.Test;
import java.util.*;
import static api.Specifications.*;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Неуспешное блокирование без 3Ds")
public class UnsuccessfulWithout3DsTest extends BaseTest {

    @Test (description = "TestCase № 8. Выдача ошибки WRONG_EXPIRE_DATE ")
    public void wrongExpireDateTest(){
        installSpecification(requestSpec(), responseSpec200());
        Map<String, Object> body = new HashMap<>();
        body.put("Key", key);
        body.put("OrderId", getRandomUniqueOrderId(key));
        body.put("Amount", getRandomInt());
        body.put("PayInfo", "PAN=" + unSuccessfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=19; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath postResponse = sendPostBlockRequest(body);
        verifyUnsuccessfulWithout3DSPostResponse(postResponse, body, ErrorCodes.WRONG_EXPIRE_DATE);

        body.put("OrderId", getRandomUniqueOrderId(key));
        body.put("PayInfo", "PAN=" + unSuccessfulCardWithout3Ds1.get("PAN") + "; EMonth=12; EYear=19; CardHolder=Ivan Ivanov; SecureCode=123;OrderId=" + body.get("OrderId") + "; Amount=" + body.get("Amount"));
        XmlPath getResponse = sendGetBlockRequest(body);
        verifyUnsuccessfulWithout3DSGetResponse(getResponse, body, ErrorCodes.WRONG_EXPIRE_DATE);
    }
}
