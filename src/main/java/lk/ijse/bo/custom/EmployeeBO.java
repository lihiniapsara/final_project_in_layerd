package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {

    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean save(EmployeeDTO employee) throws SQLException, ClassNotFoundException ;

    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException ;

    public EmployeeDTO searchById(String tel) throws SQLException, ClassNotFoundException ;

    public boolean delete(String tel) throws SQLException, ClassNotFoundException ;
}
