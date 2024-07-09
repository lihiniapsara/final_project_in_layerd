package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SalaryBO;
import lk.ijse.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.SalaryDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAOImpl salaryDAO=new SalaryDAOImpl();
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return salaryDAO.currentId();
    }

    public ArrayList<SalaryDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SalaryDTO> allSalaries= new ArrayList<>();
        List<Salary> all = salaryDAO.getAll();
        for (Salary salary : all) {
            allSalaries.add(new SalaryDTO(salary.getSalaryId(),salary.getPayPeriod(),salary.getEmployeeId())
            );
        }
        return allSalaries;
    }

    public SalaryDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Salary salary = salaryDAO.search(id);
        return new SalaryDTO(salary.getSalaryId(),salary.getPayPeriod(),salary.getEmployeeId());
    }

    public boolean save(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(salary.getSalaryId(),salary.getPayPeriod(),salary.getEmployeeId()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    public boolean update(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(salary.getSalaryId(),salary.getPayPeriod(),salary.getEmployeeId()));
    }
}
