package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {
    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException ;

    public UserDTO searchById(String tel) throws SQLException, ClassNotFoundException ;

    public boolean save(UserDTO user) throws SQLException, ClassNotFoundException ;

    public boolean update(UserDTO user) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    boolean checkCredentials(String id, String pw) throws SQLException, ClassNotFoundException;
}
