package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.PaymentDAO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    public List<Payment> getAll() throws SQLException, ClassNotFoundException {
        List<Payment> paymentList = new ArrayList<>();
        ResultSet rst= SQLUtil.execute("SELECT * FROM payment");

        while (rst.next()) {
            Payment payment = new Payment(rst.getString(1),
                    rst.getString(2),rst.getString(3),
                    rst.getString(4));
            paymentList.add(payment);
        }
        return  paymentList;
    }

    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('P', MAX(CAST(SUBSTRING(Payment_ID, 2) AS UNSIGNED))) AS max_Payment_ID FROM payment");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }


    public boolean save(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO payment VALUES(?, ?, ?, ?)",payment.getPaymentId(),payment.getMethod(),payment.getDate(),payment.getAmount());

    }

    public Payment search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment WHERE Payment_ID = ?",id);
        if (resultSet.next()) {
            return new Payment(resultSet.getString(1
            ), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }

        return null;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM payment WHERE Payment_ID = ?",id);
    }

    public boolean update(Payment payment) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE payment SET Method = ?, Date = ?,Amount=? WHERE Payment_ID = ?",payment.getMethod(),payment.getDate(),payment.getAmount(),payment.getPaymentId());
    }
}

