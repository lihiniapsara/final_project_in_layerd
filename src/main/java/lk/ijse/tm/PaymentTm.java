package lk.ijse.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class PaymentTm {
    private String id;
    private String method;
    private String date;
    private String amount;
}
