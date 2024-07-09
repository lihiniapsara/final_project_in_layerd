package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    public String currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('SU', MAX(CAST(SUBSTRING(Supplier_ID, 3) AS UNSIGNED))) AS max_su_id FROM supplier");
        if(resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {

            Supplier supplier = new Supplier(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
            supplierList.add(supplier);
        }
        return supplierList;
    }

    public Supplier search(String tel) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE Contact = ?",tel);
        if (resultSet.next()) {
            return new Supplier(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getString(5));
        }
        return null;
    }

    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?,?,?)",supplier.getSupplierId(),supplier.getName(),supplier.getPaymentTerms(),supplier.getAddress(),supplier.getContact());
    }

    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET Name = ?, Payment_Terms  = ?,Address=?,Contact=? WHERE Supplier_ID = ?",
                supplier.getName(),supplier.getPaymentTerms(),supplier.getAddress(),supplier.getContact(),supplier.getSupplierId());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE Supplier_ID = ?",id);
    }

    public List<String> getId() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT  Supplier_ID FROM supplier");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}

