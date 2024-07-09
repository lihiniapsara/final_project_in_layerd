package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.MachineDAO;
import lk.ijse.entity.Machine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDAOImpl implements MachineDAO {
    public List<Machine> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine");
        List<Machine> machineList = new ArrayList<>();

        while (resultSet.next()) {
            Machine machine = new Machine(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4));

            machineList.add(machine);
        }
        return  machineList;
    }

    public String currentId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CONCAT('M', MAX(CAST(SUBSTRING(Machine_ID, 2) AS UNSIGNED))) AS max_m_id FROM machine");
        if(resultSet.next()) {
            return resultSet.getString(1);

        }
        return null;
    }

    public Machine search(String id) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM machine WHERE Machine_ID = ?",id);
        if (resultSet.next()) {

            return new Machine(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
        }
        return null;

    }

    public boolean save(Machine entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO machine VALUES(?, ?, ?, ?)",entity.getMachineId(),entity.getModel(),entity.getType(),entity.getEmployeeId());
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM machine WHERE Machine_ID = ?",id);
    }

    public boolean update(Machine machine) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE machine SET Model = ?, Type = ? ,Employee_ID = ? WHERE Machine_ID = ?",machine.getModel(),machine.getType(),machine.getEmployeeId(),machine.getMachineId());
    }
}
