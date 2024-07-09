package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalaryDAO;
import lk.ijse.entity.Salary;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('SA', MAX(CAST(SUBSTRING(Salary_ID, 3) AS UNSIGNED))) AS max_s_id \n" +
                "FROM salary;\n");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public List<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary");

        List<Salary> salaryList = new ArrayList<>();

        while (resultSet.next()) {
            salaryList.add( new Salary(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));

        }
        return salaryList;
    }

    public Salary search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary WHERE Salary_ID = ?",id);
        if (resultSet.next()) {
            return new Salary(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
        }
        return null;
    }

    public boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary VALUES(?, ?, ?)",salary.getSalaryId(),salary.getPayPeriod(),salary.getEmployeeId());

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM salary WHERE Salary_ID = ?",id);
    }

    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Salary SET  Pay_period= ?, Employee_ID = ? WHERE Salary_ID = ?",salary.getPayPeriod(),salary.getEmployeeId(),salary.getSalaryId());
    }
}
