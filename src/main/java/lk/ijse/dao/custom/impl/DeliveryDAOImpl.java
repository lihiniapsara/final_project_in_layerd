package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.DeliveryDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {


    public Delivery search(String id) throws SQLException, ClassNotFoundException {


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM delivery WHERE Delivery_ID = ?",id);
        if (resultSet.next()) {
            return new Delivery(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        }
        return null;

    }

    public List<Delivery> getAll() throws SQLException, ClassNotFoundException {
        List<Delivery> deliveryList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM delivery");

        while (resultSet.next()) {
            Delivery delivery = new Delivery(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            deliveryList.add(delivery);
        }
        return deliveryList;
    }

    public String currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('D', MAX(CAST(SUBSTRING(Delivery_ID, 2) AS UNSIGNED))) AS max_p_id FROM delivery");
        if(resultSet.next()) {
            return resultSet.getString(1);

        }
        return null;
    }



    public boolean save(Delivery entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO delivery VALUES(?, ?, ?,?)",
                entity.getDeliveryId(),
                entity.getStatus(),
                entity.getDate(),
                entity.getVehicalId()
        );

    }

    public boolean update(Delivery delivery) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE delivery SET Status = ?, Date = ? WHERE Delivery_ID = ?",delivery.getStatus(),delivery.getDate(),delivery.getDeliveryId());

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM delivery WHERE Delivery_ID = ?",id);
    }
}
