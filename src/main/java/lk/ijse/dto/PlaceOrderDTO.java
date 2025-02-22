package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlaceOrderDTO  implements Serializable {
    private OrderDTO order;
    private List<OrderItemDetailDTO> odList;
}
