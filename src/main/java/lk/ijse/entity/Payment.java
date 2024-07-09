package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor@Data@AllArgsConstructor



public class Payment {
    private String paymentId;
    private String method;
    private String date;
    private String amount;
}
