package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.SupplierStockDetailDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierStockDetailDAOImpl {
    public boolean save(SupplierStockDetailDTO supplierStockDetailDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into  supplier_stock_detail values (?,?)",supplierStockDetailDTO.getSupplierId(),supplierStockDetailDTO.getStockId());
    }
}
