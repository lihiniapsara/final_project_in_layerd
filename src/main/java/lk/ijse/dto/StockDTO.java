package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data@AllArgsConstructor@NoArgsConstructor
public class StockDTO implements Serializable {
    private String stockId;
    private String category;
    private String stock_level;


}
