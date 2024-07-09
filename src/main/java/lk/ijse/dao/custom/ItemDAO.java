package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Item;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<Item> {
    boolean updateqty(Item item) throws SQLException, ClassNotFoundException;
}
