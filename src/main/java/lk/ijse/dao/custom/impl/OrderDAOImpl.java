package lk.ijse.dao.custom.impl;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('O', MAX(CAST(SUBSTRING(Order_ID, 2) AS UNSIGNED))) AS max_Order_ID FROM orders");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public Order search(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String tel) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
