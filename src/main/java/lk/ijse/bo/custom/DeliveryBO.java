package lk.ijse.bo.custom;


import lk.ijse.bo.SuperBO;
import lk.ijse.dto.DeliveryDTO;


import java.sql.SQLException;
import java.util.ArrayList;


public interface DeliveryBO extends SuperBO {
    public DeliveryDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public ArrayList<DeliveryDTO> getAll() throws SQLException, ClassNotFoundException ;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public boolean save(DeliveryDTO delivery) throws SQLException, ClassNotFoundException ;

    public boolean update(DeliveryDTO delivery) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
}
