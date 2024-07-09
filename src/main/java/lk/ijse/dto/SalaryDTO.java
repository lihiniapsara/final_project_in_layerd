package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryDTO  implements Serializable {
    private String salaryId;
    private String payPeriod;
    private String employeeId ;
}
