package baseTest;

import io.restassured.path.xml.XmlPath;

import static org.testng.Assert.assertEquals;

public class Transaction {
    private final Order requestBody;
    private final XmlPath responseBody;

    public Transaction(Order requestBody, XmlPath responseBody) {
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public void verifySuccessfulResponse() {
        assertEquals(responseBody.get("Block.@Success"), "True", "Параметр Success не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@OrderId"), requestBody.getOrderId(), "Параметр OrderId не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@Key"), requestBody.getKey(), "Параметр Key не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@Amount"), requestBody.getAmount(), "Параметр Amount не соответствует ожидаемому");

    }

    public void verifyUnsuccessfulResponse(ErrorCodes expectedErrorCode) {
        assertEquals(responseBody.get("Block.@Success"), "False", "Параметр Success не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@OrderId"), requestBody.getOrderId(), "Параметр OrderId не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@Key"), requestBody.getKey(), "Параметр Key не соответствует ожидаемому");
        assertEquals(responseBody.get("Block.@ErrCode"), expectedErrorCode.toString(), "Параметр ErrCode не соответствует ожидаемому");
    }
}
