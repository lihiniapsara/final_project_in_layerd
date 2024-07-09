package lk.ijse.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderItemDetailDTO implements Serializable {
    private String productId;
    private String orderId;
}
