package baseTest;

import java.util.HashMap;

public class Order {
    String Key;
    String OrderId;
    String Amount;
    String PayInfo;
    String PaytureId;
    String CustomerKey;
    String CustomFields;
    String Cheque;

    public String getKey() {
        return Key;
    }
    public String getOrderId() {
        return OrderId;
    }
    public String getAmount() {
        return Amount;
    }
    public String getPayInfo() {
        return PayInfo;
    }
    public String getPaytureId() {
        return PaytureId;
    }
    public String getCustomerKey() {
        return CustomerKey;
    }
    public String getCustomFields() {
        return CustomFields;
    }
    public String getCheque() {
        return Cheque;
    }

    public Order setKey(String key) {
        this.Key = key;
        return this;
    }
    public Order setOrderId(String orderId) {
        this.OrderId = orderId;
        return this;
    }
    public Order setAmount(String amount) {
        this.Amount = amount;
        return this;
    }
    public Order setPayInfo(String payInfo) {
        this.PayInfo = payInfo;
        return this;
    }
    public Order setPaytureId(String paytureId) {
        this.PaytureId = paytureId;
        return this;
    }
    public Order setCustomerKey(String customerKey) {
        this.CustomerKey = customerKey;
        return this;
    }
    public Order setCustomFields(String customFields) {
        this.CustomFields = customFields;
        return this;
    }
    public Order setCheque(String cheque) {
        this.Cheque = cheque;
        return this;
    }

    public String toString() {
        String result = "";
        if (Key != null) result += "Key=" + Key + ";";
        if (OrderId != null) result += "OrderId=" + OrderId + ";";
        if (Amount != null) result += "Amount=" + Amount + ";";
        if (PayInfo != null) result += "PayInfo=" + PayInfo + ";";
        if (PaytureId != null) result += "PaytureId=" + PaytureId + ";";
        if (CustomerKey != null) result += "CustomerKey=" + CustomerKey + ";";
        if (CustomFields != null) result += "CustomFields=" + CustomFields + ";";
        if (Cheque != null) result += "Cheque=" + Cheque ;
        return result;
    }

    public HashMap<String, Object> convertToHashMap(){
        HashMap<String, Object> hashMap = new HashMap<>();
        if (Key != null) hashMap.put("Key", getKey());
        if (OrderId != null) hashMap.put("OrderId", getOrderId());
        if (Amount != null) hashMap.put("Amount", getAmount());
        if (PayInfo != null) hashMap.put("PayInfo", getPayInfo());
        if (PaytureId != null) hashMap.put("PaytureId", getPaytureId());
        if (CustomerKey != null) hashMap.put("CustomerKey", getCustomerKey());
        if (CustomFields != null) hashMap.put("CustomFields", getCustomFields());
        if (Cheque != null) hashMap.put("Cheque", getCheque());
        return hashMap;
    }
}
