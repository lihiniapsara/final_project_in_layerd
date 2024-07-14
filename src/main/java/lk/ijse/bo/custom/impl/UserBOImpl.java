package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.UserDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserBOImpl implements UserBO {
    UserDAOImpl userDAO = new UserDAOImpl();

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return userDAO.currentId();
    }

    public ArrayList<UserDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUsers= new ArrayList<>();
        List<User> all = userDAO.getAll();
        for(User u:all){
            allUsers.add(new UserDTO(u.getUserId(),u.getUserName(),u.getPassword(),u.getContact()));
        }
        return allUsers;
    }

    public UserDTO searchById(String tel) throws SQLException, ClassNotFoundException {
        User user=userDAO.search(tel);
        return new UserDTO(user.getUserId(),user.getUserName(),user.getPassword(),user.getContact());
    }

    public boolean save(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(user.getUserId(),user.getUserName(),user.getPassword(),user.getContact()));
    }

    public boolean update(UserDTO user) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(user.getUserId(),user.getUserName(),user.getPassword(),user.getContact()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public boolean checkCredentials(String id, String pw) throws SQLException, ClassNotFoundException {
        String userPw = userDAO.checkCredentials(id);
        if (userPw != null) {
            return pw.equals(userPw);
        }
        return false;
    }
}
