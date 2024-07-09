package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalaryDAO;
import lk.ijse.dao.custom.StockDAO;
import lk.ijse.entity.Stock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('S', MAX(CAST(SUBSTRING(Stock_ID, 2) AS UNSIGNED))) AS max_s_id FROM stock");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public List<Stock> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock");
        List<Stock> stockList = new ArrayList<>();

        while (resultSet.next()) {
            Stock stock = new Stock(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
            stockList.add(stock);
        }
        return stockList;
    }

    public Stock search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM stock WHERE Stock_ID = ?",id);
        if (resultSet.next()) {
            return new Stock(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
         return null;
    }

    @Override
    public boolean save(Stock entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO stock VALUES(?,  ?, ? )",entity.getStockId(),entity.getCategory(),entity.getStock_level());
    }

    public boolean update(Stock stock) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE stock SET Category  = ?, Stock_level = ?   WHERE Stock_ID = ?",stock.getCategory(),stock.getStock_level(),stock.getStockId());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM stock WHERE Stock_ID = ?",id);
    }
}
