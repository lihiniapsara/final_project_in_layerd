package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDTO implements Serializable {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String stockId;


}
