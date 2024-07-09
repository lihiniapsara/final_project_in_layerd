package lk.ijse.bo.custom.impl;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.custom.PlaseOrderBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.dao.custom.OrderItemDetailDAO;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.db.DBConnection;
import lk.ijse.dto.OrderItemDetailDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.dto.PlaceOrderDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.Order;
import lk.ijse.entity.OrderItemDetail;
import lk.ijse.entity.Payment;
import lk.ijse.tm.CartTm;
import net.sf.jasperreports.engine.JRException;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacedOrderBOImpl implements PlaseOrderBO {
    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getBO(DAOFactory.DAOTypes.PAYMENT);
    OrderItemDetailDAO orderItemDetailDAO = (OrderItemDetailDAO) DAOFactory.getDaoFactory().getBO(DAOFactory.DAOTypes.ORDERITEMDETAIL);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getBO(DAOFactory.DAOTypes.ITEM);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getBO(DAOFactory.DAOTypes.ORDER);
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        OrderDAOImpl orderDAO=new OrderDAOImpl();
        return orderDAO.currentId();
    }

    @Override
    public Boolean purchesOrder(PlaceOrderDTO po, PaymentDTO paymentDTO, OrderItemDetailDTO iod, CartTm tt) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);


        try {

            boolean issaved = paymentDAO.save(new Payment(paymentDTO.getPaymentId(), paymentDTO.getMethod(), paymentDTO.getDate(), paymentDTO.getAmount()));

            if (issaved) {
                System.out.println("payment eka save una");
                boolean isSaved2 = orderDAO.save(new Order(po.getOrder().getOrderId(), po.getOrder().getDescription(), po.getOrder().getDescription(), po.getOrder().getDate(), po.getOrder().getCustomerId(), po.getOrder().getDeliveryId(), po.getOrder().getPaymentId()));
                if (isSaved2) {
                    System.out.println("Order ekath save una");

                    boolean issaved3 = orderItemDetailDAO.save(new OrderItemDetail(iod.getOrderId(), iod.getProductId()));

                    if (issaved3) {
                        System.out.println("Order items ekath save una");
                        boolean issaved5 = false;
                        boolean issaved4 = itemDAO.updateqty(new Item(tt.getId(), "gfhj", "200.00", tt.getQty(), "S002"));
                        //plaseOrderBO.sss(tm);
                        if (!issaved4) {
                            connection.rollback();
return false;
                        } else {
                            issaved5 = true;

                        }
                        if (issaved5) {
                            connection.commit();


                            new Alert(Alert.AlertType.CONFIRMATION, "order placed").show();

                        return true;}

                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return false;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
return false;

    }}
