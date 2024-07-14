package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor@NoArgsConstructor@Data
public class UserDTO  {
    private String userId;
    private String userName;
    private String password;
    private String contact;

    public UserDTO(String password, String contact) {
        this.password = password;
        this.contact = contact;
    }
}
