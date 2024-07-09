package lk.ijse.bo.custom.impl;


import lk.ijse.bo.custom.DeliveryBO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dao.custom.impl.DeliveryDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.DeliveryDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {
    public DeliveryDTO searchById(String id) throws SQLException, ClassNotFoundException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        Delivery delivery = deliveryDAO.search(id);

        return new DeliveryDTO(delivery.getDeliveryId(),
                delivery.getStatus(),
                delivery.getDate(),
                delivery.getVehicalId() );
    }

    public ArrayList<DeliveryDTO> getAll() throws SQLException, ClassNotFoundException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        ArrayList<DeliveryDTO> alldelivery= new ArrayList<>();
        List<Delivery> all = deliveryDAO.getAll();
        for (Delivery d : all){
            alldelivery.add(new DeliveryDTO(d.getDeliveryId(),
                    d.getStatus(),
                    d.getDate(),
                    d.getVehicalId())
            );

        }
        return alldelivery;
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.currentId();
    }

    public boolean save(DeliveryDTO delivery) throws SQLException, ClassNotFoundException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.save(new Delivery(delivery.getDeliveryId(),delivery.getStatus(),delivery.getDate(),delivery.getVehicalId()));
    }

    public boolean update(DeliveryDTO delivery) throws SQLException, ClassNotFoundException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.update(new Delivery(delivery.getDeliveryId(),delivery.getStatus(),delivery.getDate(),delivery.getVehicalId()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.delete(id);
    }
}
