package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dto.OrderItemDetailDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.PlaceOrderDTO;
import lk.ijse.tm.CartTm;

import java.sql.SQLException;

public interface PlaseOrderBO extends SuperBO {
    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    Boolean purchesOrder(PlaceOrderDTO po, PaymentDTO paymentDTO, OrderItemDetailDTO iod, CartTm tt) throws SQLException, ClassNotFoundException;
}
