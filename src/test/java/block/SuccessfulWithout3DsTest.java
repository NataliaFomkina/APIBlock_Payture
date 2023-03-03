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

        PayInfo payInfoPost = new PayInfo()
                .setPAN(successfulCardWithout3Ds1.get("PAN"))
                .setOrderId(getRandomOrderId())
                .setAmount(getRandomInt())
                .setEMonth(successfulCardWithout3Ds1.get("EMonth"))
                .setEYear(successfulCardWithout3Ds1.get("EYear"))
                .setCardHolder(successfulCardWithout3Ds1.get("CardHolder"))
                .setSecureCode(successfulCardWithout3Ds1.get("SecureCode"));
        Order orderPost = new Order()
                .setOrderId(payInfoPost.getOrderId())
                .setKey(key)
                .setAmount(payInfoPost.getAmount())
                .setPayInfo(payInfoPost.toString());

        sendPostBlockRequest(orderPost).verifySuccessfulResponse();

        PayInfo payInfoGet = new PayInfo()
                .setPAN(successfulCardWithout3Ds1.get("PAN"))
                .setOrderId(getRandomOrderId())
                .setAmount(getRandomInt())
                .setEMonth(successfulCardWithout3Ds1.get("EMonth"))
                .setEYear(successfulCardWithout3Ds1.get("EYear"))
                .setCardHolder(successfulCardWithout3Ds1.get("CardHolder"))
                .setSecureCode(successfulCardWithout3Ds1.get("SecureCode"));
        Order orderGet = new Order()
                .setOrderId(payInfoGet.getOrderId())
                .setKey(key)
                .setAmount(payInfoGet.getAmount())
                .setPayInfo(payInfoGet.toString());

        sendGetBlockRequest(orderGet).verifySuccessfulResponse();
    }
}
