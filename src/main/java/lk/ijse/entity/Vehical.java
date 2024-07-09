package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehical {
    private String vehicleId;
    private String type;
    private String model;
    private String employeeId ;
}
