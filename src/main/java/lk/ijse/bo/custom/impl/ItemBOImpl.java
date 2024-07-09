package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAOImpl itemDAO = new ItemDAOImpl();

    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItem= new ArrayList<>();
        List<Item> all = itemDAO.getAll();
        for (Item i : all) {
            allItem.add(new ItemDTO(i.getItemId(),i.getDescription(),i.getPrice(),i.getQty(),i.getStockId()));
        }
        return allItem;
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return itemDAO.currentId();
    }

    public boolean save(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(item.getItemId(),item.getDescription(),item.getPrice(),item.getQty(),item.getStockId()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    public ItemDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(id);
        return new ItemDTO(item.getItemId(),item.getDescription(),item.getPrice(),item.getQty(),item.getStockId());
    }

    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(item.getItemId(),item.getDescription(),item.getPrice(),item.getQty(),item.getStockId()));
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getIds();
    }
}
