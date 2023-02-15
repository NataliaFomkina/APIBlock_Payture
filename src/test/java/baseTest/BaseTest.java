package baseTest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import pojos.Block;
import pojos.GetState;
import test_conf.props.TestConfig;

import java.util.HashMap;
import java.util.Map;

import static api.Specifications.*;
import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class BaseTest {
public String key = "Merchant";
    public String getRandomUniqueOrderId(String merchant) {
        String uniqueOrderId = "";
        int randomStringLength = 0;
        installSpecification(requestSpec(), responseSpec200());
        while(uniqueOrderId.equals("")) {
            randomStringLength = (int) (Math.random() * 50) + 1;
            String randomOrderId = randomAlphanumeric(randomStringLength);
            GetState GetStatePojo = GetState.builder()
                    .Key(merchant)
                    .OrderId(randomOrderId)
                    .build();
            HashMap<String, String> GetStateHashMap = convertPOJOtoHashMap(GetStatePojo);
            Response resp =
                    given().contentType("application/x-www-form-urlencoded")
                            .accept("*/*")
                            .body(GetStateHashMap).relaxedHTTPSValidation()
                            .when()
                            .get(TestConfig.PATH.Value + "GetState")
                            .then().log().all()
                            .extract().response();
            String success = resp.path("Success");
            if(success.equals("Success")) uniqueOrderId = success;
        }
        return uniqueOrderId;
    }


    public HashMap<String, String> convertPOJOtoHashMap(Object pojo){
        ObjectMapper oMapper = new ObjectMapper();
        return (java.util.HashMap<String, String>) oMapper.convertValue(pojo, new TypeReference<Map<String, String>>() {});
    }

    public Block convertHashMaptoBlock(HashMap<String, String> hashMap){
        ObjectMapper oMapper = new ObjectMapper();
        return oMapper.convertValue(hashMap, Block.class);
    }

}
