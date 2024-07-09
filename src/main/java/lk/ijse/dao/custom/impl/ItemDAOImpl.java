package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    public List<Item> getAll() throws SQLException, ClassNotFoundException {

        List<Item> itemList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");

        while (resultSet.next()) {
            Item item = new Item(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
            itemList.add(item);
        }
        return itemList;
    }

    public String currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('I', MAX(CAST(SUBSTRING(Item_ID, 2) AS UNSIGNED))) AS max_i_id FROM item");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item VALUES(?, ?, ?,?,?)", item.getItemId(), item.getDescription(), item.getPrice(), item.getQty(), item.getStockId());

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM item WHERE Item_ID = ?", id);
    }

    public Item search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE Item_ID = ?", id);

        while (resultSet.next()) {
            return new Item(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
        }
        return null;
    }

    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET Description = ?, Price=? , Qty = ?   WHERE Item_ID = ?",
                item.getDescription(),item.getPrice(),item.getQty(),item.getItemId());
    }

    @Override
    public boolean updateqty(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET Qty = Qty - ? WHERE Item_ID = ?",item.getQty(),item.getItemId());
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {


        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT Item_ID FROM item");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}

