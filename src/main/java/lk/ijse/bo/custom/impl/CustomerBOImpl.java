package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAOImpl customerDAO = new CustomerDAOImpl();

    public CustomerDTO searchById(String tel) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(tel);
        return new CustomerDTO(customer.getCustomerId(),
                customer.getName(),
                customer.getAddress(),
                customer.getContact()
        );
    }

    public boolean savecustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
     return customerDAO.save(new Customer(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getContact()));
    }

    public boolean updatecustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getContact()));
    }

    public boolean deletecustomer(String tel) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(tel);
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return customerDAO.currentId();
    }

    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        List<Customer> all = customerDAO.getAll();
        for (Customer c : all) {
            allCustomers.add(new CustomerDTO(c.getCustomerId(),
                    c.getName(),
                    c.getAddress(),
                    c.getContact())
            );
        }
        return allCustomers;
    }

    @Override
    public String getName(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.getName(id);
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getIds();
    }

   /* @Override
    public List<CustomerDTO> getIds() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers= new ArrayList<>();
        List<Customer> all = customerDAO.getAll();
        return customerDAO.getIds();
    }*/

}

