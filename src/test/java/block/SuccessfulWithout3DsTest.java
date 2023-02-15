package block;
import baseTest.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pojos.Block;
import pojos.PayInfoBlock;
import test_conf.props.TestConfig;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Epic(value = "Двухстадийный платеж")
@Feature(value = "Блокирование без 3Ds")
public class SuccessfulWithout3DsTest extends BaseTest {
public String successfulWithout3DsPAN = "5218851946955484";
    @Test
    public void mandatoryFieldsWithoutOptionalTest(){
        String OrderId = getRandomUniqueOrderId(key);
        installSpecification(requestSpec(), responseSpec200());
        assertEquals(given().log().uri()
                    .body(Block.builder()
                            .Key(key)
                            .OrderId(OrderId)
                            .Amount("12500")
                            .PayInfo(PayInfoBlock.builder()
                                    .PAN(successfulWithout3DsPAN)
                                    .EMonth("12")
                                    .EYear("25")
                                    .OrderId(OrderId)
                                    .Amount("12500")
                                    .build())
                            .build())
                        .when()
                        .put(TestConfig.PATH.Value + "Build")
                        .then().log().all()
                        .extract().statusCode(), 200, "Актуальный статус-код отличается от ожидаемого");

    }
}
