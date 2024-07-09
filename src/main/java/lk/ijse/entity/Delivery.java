package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Delivery {
    private String deliveryId;
    private String status;
    private String date;
    private String vehicalId;

    public Delivery(String deliveryId, String status, String date) {
    }
}
