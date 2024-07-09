package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    UserDTO checkCredentials(String id) throws SQLException, ClassNotFoundException;
}
