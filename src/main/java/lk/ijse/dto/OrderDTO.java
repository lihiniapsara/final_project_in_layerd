package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO  implements Serializable {
    private String orderId;
    private String description;
    private String date;
    private String total;
    private String deliveryId;
    private String paymentId;
    private String customerId ;
}
