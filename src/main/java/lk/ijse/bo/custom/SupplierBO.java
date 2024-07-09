package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException ;

    public SupplierDTO searchById(String tel) throws SQLException, ClassNotFoundException ;

    public boolean save(SupplierDTO supplier) throws SQLException, ClassNotFoundException ;

    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
}
