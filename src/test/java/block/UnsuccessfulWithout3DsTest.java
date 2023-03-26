package block;

import baseTest.APIMethods;
import baseTest.BaseTest;
import baseTest.ErrorCodes;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static api.Specifications.*;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Неуспешное блокирование без 3Ds")
public class UnsuccessfulWithout3DsTest extends BaseTest {

    @ParameterizedTest(name = "{index} {0} TestCase № 8. Выдача ошибки WRONG_EXPIRE_DATE")
    @EnumSource(APIMethods.class)
    public void wrongExpireDateTest(APIMethods method) {
        installSpecification(requestSpec(), responseSpec200());
        sendRequest(method, generateOrder(unSuccessfulCardWithout3Ds1)).verifyUnsuccessfulResponse(ErrorCodes.WRONG_EXPIRE_DATE);
    }
}
