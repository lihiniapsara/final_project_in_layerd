package lk.ijse.bo.custom;


import lk.ijse.bo.SuperBO;
import lk.ijse.dto.CustomerDTO;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public CustomerDTO searchById(String tel) throws SQLException, ClassNotFoundException ;

    public boolean savecustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException ;

    public boolean updatecustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException ;

    public boolean deletecustomer(String tel) throws SQLException, ClassNotFoundException ;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException ;

    String getName(String id) throws SQLException, ClassNotFoundException;

    List<String> getIds() throws SQLException, ClassNotFoundException;

/*
    List<CustomerDTO> getIds() throws SQLException, ClassNotFoundException;
*/
}
