package block;

import baseTest.BaseTest;
import baseTest.ErrorCodes;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import static api.Specifications.*;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Неуспешное блокирование без 3Ds")
public class UnsuccessfulWithout3DsTest extends BaseTest {

    @Test (description = "TestCase № 8. Выдача ошибки WRONG_EXPIRE_DATE ")
    public void wrongExpireDateTest(){
        installSpecification(requestSpec(), responseSpec200());

        sendPostBlockRequest(generateOrder(unSuccessfulCardWithout3Ds1)).verifyUnsuccessfulResponse(ErrorCodes.WRONG_EXPIRE_DATE);
        sendGetBlockRequest(generateOrder(unSuccessfulCardWithout3Ds1)).verifyUnsuccessfulResponse(ErrorCodes.WRONG_EXPIRE_DATE);
    }
}
