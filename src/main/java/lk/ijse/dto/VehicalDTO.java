package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicalDTO implements Serializable {
    private String vehicleId;
    private String type;
    private String model;
    private String employeeId ;
}
