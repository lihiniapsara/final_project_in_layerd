package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SalaryDTO;
import lk.ijse.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SalaryBO extends SuperBO {
    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public ArrayList<SalaryDTO> getAll() throws SQLException, ClassNotFoundException ;

    public SalaryDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public boolean save(SalaryDTO salary) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(SalaryDTO salary) throws SQLException, ClassNotFoundException ;
}
