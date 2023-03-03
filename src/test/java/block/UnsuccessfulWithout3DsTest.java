package block;

import baseTest.BaseTest;
import baseTest.ErrorCodes;
import baseTest.Order;
import baseTest.PayInfo;
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

        PayInfo payInfoPost = new PayInfo()
                .setPAN(unSuccessfulCardWithout3Ds1.get("PAN"))
                .setOrderId(getRandomOrderId())
                .setAmount(getRandomInt())
                .setEMonth(unSuccessfulCardWithout3Ds1.get("EMonth"))
                .setEYear(unSuccessfulCardWithout3Ds1.get("EYear"))
                .setCardHolder(unSuccessfulCardWithout3Ds1.get("CardHolder"))
                .setSecureCode(unSuccessfulCardWithout3Ds1.get("SecureCode"));
        Order orderPost = new Order()
                .setOrderId(payInfoPost.getOrderId())
                .setKey(key)
                .setAmount(payInfoPost.getAmount())
                .setPayInfo(payInfoPost.toString());

        sendPostBlockRequest(orderPost).verifyUnsuccessfulResponse(ErrorCodes.WRONG_EXPIRE_DATE);

        PayInfo payInfoGet = new PayInfo()
                .setPAN(unSuccessfulCardWithout3Ds1.get("PAN"))
                .setOrderId(getRandomOrderId())
                .setAmount(getRandomInt())
                .setEMonth(unSuccessfulCardWithout3Ds1.get("EMonth"))
                .setEYear(unSuccessfulCardWithout3Ds1.get("EYear"))
                .setCardHolder(unSuccessfulCardWithout3Ds1.get("CardHolder"))
                .setSecureCode(unSuccessfulCardWithout3Ds1.get("SecureCode"));
        Order orderGet = new Order()
                .setOrderId(payInfoGet.getOrderId())
                .setKey(key)
                .setAmount(payInfoGet.getAmount())
                .setPayInfo(payInfoGet.toString());

        sendPostBlockRequest(orderGet).verifyUnsuccessfulResponse(ErrorCodes.WRONG_EXPIRE_DATE);
    }
}
