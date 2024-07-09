package lk.ijse.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SupplierStockDetailDTO  implements Serializable {
    private String supplierId;
    private String stockId;
}
