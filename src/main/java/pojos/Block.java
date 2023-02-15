package pojos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Block {
    private String Key;
    private String OrderId;
    private String Amount;
    private PayInfoBlock PayInfo;
    private String PaytureId;
    private String CustomerKey;
    private String CustomFields;
    private String Cheque;
}
