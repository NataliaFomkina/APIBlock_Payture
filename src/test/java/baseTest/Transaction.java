package baseTest;

import io.restassured.path.xml.XmlPath;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class Transaction {
    private final Map<String,Object> requestBody;
    private final XmlPath responseBody;

    public Transaction(Map<String, Object> requestBody, XmlPath responseBody) {
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public void verifySuccessfulResponse() {
        assertEquals(responseBody.get("Block.@Success"), "True", "Параметр Success не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@OrderId"), requestBody.get("OrderId"), "Параметр OrderId не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@Key"), requestBody.get("Key"), "Параметр Key не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@Amount"), requestBody.get("Amount"), "Параметр Amount не соответствует ожидаемому");

    }

    public void verifyUnsuccessfulResponse(ErrorCodes expectedErrorCode) {
        assertEquals(responseBody.get("Block.@Success"), "False", "Параметр Success не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@OrderId"), requestBody.get("OrderId"), "Параметр OrderId не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@Key"), requestBody.get("Key"), "Параметр Key не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@ErrCode"), expectedErrorCode.toString(), "Параметр ErrCode не соответствует ожидаемому");
    }
}
