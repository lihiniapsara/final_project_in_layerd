package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MachineDTO   {
    private String machineId;
    private String model;
    private String type;
    private String employeeId ;
}
