package block;

import baseTest.APIMethods;
import baseTest.BaseTest;
import baseTest.Order;
import baseTest.PayInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static api.Specifications.*;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Успешное блокирование без 3Ds")
public class SuccessfulWithout3DsTest extends BaseTest {

    @ParameterizedTest(name = "{index} {0} TestCase № 2. Успешное блокирование средств при корректном заполнении всех обязательных параметров и отсутствии необязательных")
    @EnumSource(APIMethods.class)
    public void mandatoryFieldsWithoutOptionalTest(APIMethods method) {
        installSpecification(requestSpec(), responseSpec200());

        PayInfo payInfoPost = generatePayInfo(successfulCardWithout3Ds1);
        Order orderPost = new Order()
                .setOrderId(payInfoPost.getOrderId())
                .setKey(key)
                .setAmount(payInfoPost.getAmount())
                .setPayInfo(payInfoPost);

        sendRequest(method, orderPost).verifySuccessfulResponse();
    }
}
