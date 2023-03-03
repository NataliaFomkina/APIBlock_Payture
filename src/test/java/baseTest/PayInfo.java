package baseTest;

public class PayInfo {
    String PAN;
    String EMonth;
    String EYear;
    String OrderId;
    String Amount;
    String SecureCode;
    String CardHolder;

    public String getPAN() {
        return PAN;
    }
    public String getEMonth() {
        return EMonth;
    }
    public String getEYear() {
        return EYear;
    }
    public String getOrderId() {
        return OrderId;
    }
    public String getAmount() {
        return Amount;
    }
    public String getSecureCode() {
        return SecureCode;
    }
    public String getCardHolder() {
        return CardHolder;
    }

    public PayInfo setPAN(String PAN) {
        this.PAN = PAN;
        return this;
    }

    public PayInfo setEMonth(String EMonth) {
        this.EMonth = EMonth;
        return this;
    }

    public PayInfo setEYear(String EYear) {
        this.EYear = EYear;
        return this;
    }

    public PayInfo setOrderId(String orderId) {
        this.OrderId = orderId;
        return this;
    }

    public PayInfo setAmount(String amount) {
        this.Amount = amount;
        return this;
    }

    public PayInfo setSecureCode(String secureCode) {
        this.SecureCode = secureCode;
        return this;
    }

    public PayInfo setCardHolder(String cardHolder) {
        this.CardHolder = cardHolder;
        return this;
    }

    public String toString() {
        String result = "";
        if (PAN != null) result += "PAN=" + PAN + ";";
        if (EMonth != null) result += "EMonth=" + EMonth + ";";
        if (EYear != null) result += "EYear=" + EYear + ";";
        if (OrderId != null) result += "OrderId=" + OrderId + ";";
        if (Amount != null) result += "Amount=" + Amount + ";";
        if (SecureCode != null) result += "SecureCode=" + SecureCode + ";";
        if (CardHolder != null) result += "CardHolder=" + CardHolder;
        return result;
    }

}
