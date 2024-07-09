package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderItemDetailDAO;
import lk.ijse.entity.OrderItemDetail;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDetailDAOImpl implements OrderItemDetailDAO {
    @Override
    public String currentId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderItemDetail search(String tel) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderItemDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("insert into item_order_detail values (?,?)",entity.getProductId(),entity.getOrderId());
    }

    @Override
    public boolean update(OrderItemDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String tel) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<OrderItemDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
