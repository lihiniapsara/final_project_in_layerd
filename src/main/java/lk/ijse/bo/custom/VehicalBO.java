package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.VehicalDTO;
import lk.ijse.entity.Vehical;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicalBO extends SuperBO {
    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public ArrayList<VehicalDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean save(VehicalDTO vehical) throws SQLException, ClassNotFoundException ;

    public boolean update(VehicalDTO vehical) throws SQLException, ClassNotFoundException ;

    public VehicalDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
}
