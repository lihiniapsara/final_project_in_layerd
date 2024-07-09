package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('U', MAX(CAST(SUBSTRING(User_ID, 2) AS UNSIGNED))) AS max_u_id FROM user");
        if(resultSet.next()) {
            return resultSet.getString(1);

        }
        return null;
    }

    public List<User> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");

        List<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
            userList.add(user);
        }
        return userList;
    }

    public User search(String tel) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE Contact = ?",tel);
        if (resultSet.next()) {
            User user = new User(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));

            return user;
        }

        return null;
    }

    public boolean save(User user) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO user VALUES(?, ?, ?, ?)",user.getUserId(),user.getUserName(),user.getPassword(),user.getContact());
    }

    public boolean update(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE user SET Name = ?, Password = ?, Contact = ? WHERE User_ID = ?",user.getUserName(),user.getPassword(),user.getContact(),user.getUserId());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM user WHERE User_ID = ?",id);
    }

    @Override
    public UserDTO checkCredentials(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT User_id, Password FROM user WHERE User_ID = ?",id);
        if (resultSet.next()) {
            String userId = resultSet.getString("User_id");
            String password = resultSet.getString("Password");

            return new UserDTO();
        }
        return null;
    }
}
