package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.VehicalDAO;
import lk.ijse.entity.Vehical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicalDAOImpl implements VehicalDAO {
    public String currentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('V', MAX(CAST(SUBSTRING(Vehical_ID, 2) AS UNSIGNED))) AS max_v_id FROM vehical");
        if(resultSet.next()) {
            return resultSet.getString(1);

        }
        return null;
    }

    public List<Vehical> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehical");

        List<Vehical> vehicalList = new ArrayList<>();

        while (resultSet.next()) {
            Vehical vehical = new Vehical(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
            vehicalList.add(vehical);
        }
        return vehicalList;
    }

    public boolean save(Vehical vehicalDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO vehical VALUES(?, ?, ?, ?)",vehicalDTO.getVehicleId(),vehicalDTO.getType(),vehicalDTO.getModel(),vehicalDTO.getEmployeeId());
    }

    public boolean update(Vehical vehical) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE vehical SET Model = ?, Type = ?, Employee_Id = ? WHERE Vehical_ID = ?",vehical.getModel(),vehical.getType(),vehical.getEmployeeId(),vehical.getVehicleId());
    }

    public Vehical search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehical WHERE Vehical_ID = ?",id);
        if (resultSet.next()) {
            return new Vehical(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));

        }

        return null;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM vehical WHERE Vehical_ID = ?",id);
    }
}
