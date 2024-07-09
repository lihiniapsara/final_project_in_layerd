package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String orderId;
    private String description;

    private String total;
    private String date;
    private String deliveryId;
    private String paymentId;
    private String customerId ;
}
