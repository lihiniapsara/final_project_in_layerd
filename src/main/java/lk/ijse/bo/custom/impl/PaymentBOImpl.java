package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAOImpl paymentDAO=new PaymentDAOImpl();
    public ArrayList<PaymentDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PaymentDTO> allPayment= new ArrayList<>();
        List<Payment> all = paymentDAO.getAll();

        for (Payment p : all) {
            allPayment.add(new PaymentDTO(p.getPaymentId(),p.getMethod(), p.getDate(),p.getAmount()));
        }

        return allPayment;
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {

       return paymentDAO.currentId();
    }

    public boolean save(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return paymentDAO.save(new Payment(payment.getPaymentId(),payment.getMethod(), payment.getDate(),
                payment.getAmount()));
    }

    public PaymentDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Payment payment=paymentDAO.search(id);
        return new PaymentDTO(payment.getPaymentId(), payment.getMethod(), payment.getDate(), payment.getAmount());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return paymentDAO.delete(id);
    }

    public boolean update(PaymentDTO payment) throws SQLException, ClassNotFoundException {
        return paymentDAO.update(new Payment(payment.getPaymentId(),payment.getMethod(),payment.getDate(),payment.getAmount()));
    }
}

