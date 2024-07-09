package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor@Data@AllArgsConstructor



public class PaymentDTO   {
    private String paymentId;
    private String method;
    private String date;
    private String amount;
}
