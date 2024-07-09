package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAOImpl supplierDAO=new SupplierDAOImpl();
    public String getCurrentId() throws SQLException, ClassNotFoundException {
      return supplierDAO.currentId();
    }

    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<SupplierDTO> allSuppliers= new ArrayList<>();
        List<Supplier> all = supplierDAO.getAll();
        for (Supplier s : all) {
            allSuppliers.add(new SupplierDTO(s.getSupplierId(), s.getName(), s.getPaymentTerms(), s.getAddress(), s.getContact()));
        }
        return allSuppliers;
    }

    public SupplierDTO searchById(String tel) throws SQLException, ClassNotFoundException {
        Supplier supplier=supplierDAO.search(tel);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getName(),supplier.getPaymentTerms(),
                supplier.getAddress(),supplier.getContact());
    }

    public boolean save(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getPaymentTerms(),
                supplier.getAddress(),supplier.getContact()));
    }

    public boolean update(SupplierDTO supplier) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplier.getSupplierId(),supplier.getName(),supplier.getPaymentTerms(),supplier.getAddress(),supplier.getContact()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {
       return supplierDAO.getId();
    }
}
