package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data@AllArgsConstructor@NoArgsConstructor
public class Stock {
    private String stockId;
    private String category;
    private String stock_level;
    //private String supplierId;


}
