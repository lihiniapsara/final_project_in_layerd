package lk.ijse.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTm {
    private String id;
    private String name;
    private String department;
    private String title;
    private String address;
    private String tel;
}
