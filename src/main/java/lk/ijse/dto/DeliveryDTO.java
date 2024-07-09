package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryDTO implements Serializable {
    private String deliveryId;
    private String status;
    private String date;
    private String vehicalId;


}
