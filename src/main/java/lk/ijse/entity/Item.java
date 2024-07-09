package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private String itemId;
    private String description;
    private String price;
    private String qty;
    private String stockId;



    public Item(String id, String description, String price, int qty, String s002) {
    }
}
