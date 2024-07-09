package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.VehicalBO;
import lk.ijse.dao.custom.impl.VehicalDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.VehicalDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Vehical;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicalBOImpl implements VehicalBO {
    VehicalDAOImpl vehicalDAO=new VehicalDAOImpl();
    public String getCurrentId() throws SQLException, ClassNotFoundException {
     return vehicalDAO.currentId();
    }

    public ArrayList<VehicalDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<VehicalDTO> allVehicals= new ArrayList<>();
        List<Vehical> all = vehicalDAO.getAll();
        for (Vehical v:all){
            allVehicals.add(new VehicalDTO(v.getVehicleId(),v.getModel(),v.getType(),v.getEmployeeId()));
        }
        return allVehicals;
    }

    public boolean save(VehicalDTO vehical) throws SQLException, ClassNotFoundException {
        return vehicalDAO.save(new Vehical(vehical.getVehicleId(),vehical.getType(),vehical.getModel(),vehical.getEmployeeId()));
    }

    public boolean update(VehicalDTO vehical) throws SQLException, ClassNotFoundException {
        return vehicalDAO.update(new Vehical(vehical.getVehicleId(),vehical.getType(),vehical.getModel(),vehical.getEmployeeId()));
    }

    public VehicalDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Vehical vehical=vehicalDAO.search(id);
        return new VehicalDTO(vehical.getVehicleId(), vehical.getModel(),vehical.getType(),vehical.getEmployeeId());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return vehicalDAO.delete(id);
    }
}
