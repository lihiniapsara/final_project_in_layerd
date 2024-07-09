package lk.ijse.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.ijse.bo.custom.StockBO;
import lk.ijse.dao.custom.impl.StockDAOImpl;
import lk.ijse.dao.custom.impl.SupplierStockDetailDAOImpl;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.StockDTO;
import lk.ijse.dto.SupplierStockDetailDTO;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    StockDAOImpl stockDAO = new StockDAOImpl();

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return stockDAO.currentId();
    }

    public ArrayList<StockDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<StockDTO> allStocks = new ArrayList<>();
        List<Stock> all = stockDAO.getAll();
        for (Stock s : all) {
            allStocks.add(new StockDTO(s.getStockId(), s.getCategory(), s.getStock_level()));
        }
        return allStocks;
    }

    public StockDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Stock stock = stockDAO.search(id);
        return new StockDTO(stock.getStockId(), stock.getCategory(), stock.getStock_level());
    }

    public boolean update(StockDTO stock) throws SQLException, ClassNotFoundException {
        return stockDAO.update(new Stock(stock.getStockId(), stock.getCategory(), stock.getStock_level()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return stockDAO.delete(id);
    }

    @Override
    public Boolean saveData(StockDTO stock, SupplierStockDetailDTO supplierStockDetailDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        SupplierStockDetailDAOImpl supplierStockDetailDAO = new SupplierStockDetailDAOImpl();
        try {
            boolean isSaved = stockDAO.save(new Stock(stock.getStockId(), stock.getCategory(), stock.getStock_level()));
            if (isSaved) {

                boolean issaved2 = supplierStockDetailDAO.save(supplierStockDetailDTO);
                if (issaved2) {
                    connection.commit();
                    return true;

                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }
}
