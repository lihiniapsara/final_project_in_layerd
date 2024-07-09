package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('E', MAX(CAST(SUBSTRING(Employee_ID, 2) AS UNSIGNED))) AS max_e_id FROM employee");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        List<Employee> employeesList = new ArrayList<>();

        while (resultSet.next()) {
            Employee employee = new Employee(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)

            );
            employeesList.add(employee);
        }
        return employeesList;
    }

    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?,?,?,?)", entity.getEmployeeId(), entity.getEmployeename(), entity.getDepartment(),
                entity.getJob_title(), entity.getAddress(), entity.getContact(), entity.getUserId());
    }

    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE employee SET Name = ?,Department=?,Job_Title=?, Address =  ?, Contact  = ?   WHERE Employee_ID = ?",
                entity.getEmployeename(), entity.getDepartment(), entity.getJob_title(), entity.getAddress(),
                entity.getContact(), entity.getEmployeeId());
    }

    public Employee search(String tel) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE Contact = ?", tel);
        if (resultSet.next()) {
            return new Employee(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),resultSet.getString(7));
        }
        return null;
    }

    public boolean delete(String tel) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM employee WHERE Contact = ?",tel);
    }
}
