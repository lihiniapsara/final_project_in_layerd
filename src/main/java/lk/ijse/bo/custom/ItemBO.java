package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException ;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public boolean save(ItemDTO item) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public ItemDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException ;

    List<String> getIds() throws SQLException, ClassNotFoundException;
}
