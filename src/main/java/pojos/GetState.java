package pojos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetState {
    private String Key;
    private String OrderId;
}
