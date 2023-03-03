package block;

import baseTest.BaseTest;
import baseTest.Order;
import baseTest.PayInfo;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import static api.Specifications.*;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Успешное блокирование без 3Ds")
public class SuccessfulWithout3DsTest extends BaseTest {

    @Test (description = "TestCase № 2. Успешное блокирование средств при корректном заполнении всех обязательных параметров и отсутствии необязательных")
    public void mandatoryFieldsWithoutOptionalTest(){
        installSpecification(requestSpec(), responseSpec200());

        PayInfo payInfoPost = generatePayInfo(successfulCardWithout3Ds1);
        Order orderPost = new Order()
                .setOrderId(payInfoPost.getOrderId())
                .setKey(key)
                .setAmount(payInfoPost.getAmount())
                .setPayInfo(payInfoPost);

        sendPostBlockRequest(orderPost).verifySuccessfulResponse();

        PayInfo payInfoGet = generatePayInfo(successfulCardWithout3Ds1);
        Order orderGet = new Order()
                .setOrderId(payInfoGet.getOrderId())
                .setKey(key)
                .setAmount(payInfoGet.getAmount())
                .setPayInfo(payInfoGet);

        sendGetBlockRequest(orderGet).verifySuccessfulResponse();
    }
}
