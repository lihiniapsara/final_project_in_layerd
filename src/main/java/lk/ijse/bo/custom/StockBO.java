package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.StockDTO;
import lk.ijse.dto.SupplierStockDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StockBO extends SuperBO {
    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public ArrayList<StockDTO> getAll() throws SQLException, ClassNotFoundException ;

    public StockDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(StockDTO stock) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    Boolean saveData(StockDTO stock, SupplierStockDetailDTO supplierStockDetailDTO) throws SQLException, ClassNotFoundException;
}
