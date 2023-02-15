package pojos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PayInfoBlock {
    private String PAN;
    private String EMonth;
    private String EYear;
    private String OrderId;
    private String Amount;
    private String SecureCode;
    private String CardHolder;
}
