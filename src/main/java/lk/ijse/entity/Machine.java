package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Machine {
    private String machineId;
    private String model;
    private String type;
    private String employeeId ;
}
