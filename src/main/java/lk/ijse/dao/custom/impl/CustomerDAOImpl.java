package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    public Customer search(String tel) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE Contact = ?", tel);
        if (rst.next()) {
            return new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4));
        }
        return null;
    }

    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?)",entity.getCustomerId(),entity.getName(),entity.getAddress(),entity.getContact());
    }

    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET Name =? , Address = ? , Contact =?where  Customer_ID=?",customer.getName(),customer.getAddress(),customer.getContact(),customer.getCustomerId());
    }

    public boolean delete(String tel) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE Contact = ?",tel);
    }

    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('C', MAX(CAST(SUBSTRING(Customer_ID, 2) AS UNSIGNED))) AS max_p_id FROM customer");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public String getName(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM customer WHERE Customer_ID= ?",id);
        if (resultSet.next()){
            return resultSet.getString(1);

        }
        return  null;
    }

    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
            customerList.add(customer);
        }
        return  customerList;
    }


    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> customerList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT Customer_ID FROM customer  ");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            customerList.add(id);
        }
        return customerList;
    }
}

