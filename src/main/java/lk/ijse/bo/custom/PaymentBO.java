package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBO extends SuperBO {
    public ArrayList<PaymentDTO> getAll() throws SQLException, ClassNotFoundException ;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public boolean save(PaymentDTO payment) throws SQLException, ClassNotFoundException ;

    public PaymentDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(PaymentDTO payment) throws SQLException, ClassNotFoundException ;
}
