package lk.ijse.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
   private String employeeId ;
   private String employeename ;
   private String department;
   private String job_title ;
   private String address ;
   private String contact;
   private String userId;


}
