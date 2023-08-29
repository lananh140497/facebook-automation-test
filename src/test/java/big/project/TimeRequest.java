package big.project;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeRequest {
    private String Date;
    private String timeCheckin;
    private String timeCheckout;
}
