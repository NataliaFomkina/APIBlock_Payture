package baseTest;

public class Card {
    private final String PAN;
    private final String SecureCode;
    private final String EMonth;
    private final String EYear;
    private final String CardHolder;

    public Card(String PAN, String secureCode, String EMonth, String EYear, String CardHolder) {
        this.PAN = PAN;
        this.SecureCode = secureCode;
        this.EMonth = EMonth;
        this.EYear = EYear;
        this.CardHolder = CardHolder;
    }

    public String getPAN() {
        return PAN;
    }

    public String getSecureCode() {
        return SecureCode;
    }

    public String getEMonth() {
        return EMonth;
    }

    public String getEYear() {
        return EYear;
    }

    public String getCardHolder() {
        return CardHolder;
    }
}
