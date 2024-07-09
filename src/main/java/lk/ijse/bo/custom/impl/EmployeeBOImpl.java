package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;

import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

    public String getCurrentId() throws SQLException, ClassNotFoundException {

        return employeeDAO.currentId();
    }

    public ArrayList<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<EmployeeDTO> allEmployees = new ArrayList<>();
        List<Employee> all = employeeDAO.getAll();
        for (Employee employee : all) {
            allEmployees.add(new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeename(),employee.getDepartment(),employee.getJob_title(),employee.getAddress(),employee.getContact(),employee.getUserId()));
        }
        return allEmployees;
    }

    public boolean save(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employee.getEmployeeId(),employee.getEmployeename(),employee.getDepartment(),employee.getJob_title(),employee.getAddress(),employee.getContact(),employee.getUserId()));

    }

    public boolean update(EmployeeDTO employee) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employee.getEmployeeId(),employee.getEmployeename(),
                employee.getDepartment(),employee.getJob_title(),employee.getAddress(),employee.getContact(),employee.getUserId()));
    }

    public EmployeeDTO searchById(String tel) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(tel);
        return new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeename(),employee.getDepartment(),employee.getJob_title(),employee.getAddress(),employee.getContact(),employee.getUserId());
    }

    public boolean delete(String tel) throws SQLException, ClassNotFoundException {
            return employeeDAO.delete(tel);
    }
}
