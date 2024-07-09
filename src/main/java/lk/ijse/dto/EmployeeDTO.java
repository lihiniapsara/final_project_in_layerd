package lk.ijse.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO implements Serializable {
   private String employeeId ;
   private String employeename ;
   private String department;
   private String job_title ;
   private String address ;
   private String contact;
   private String userId;


}
