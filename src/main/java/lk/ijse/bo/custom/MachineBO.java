package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.MachineDTO;
import lk.ijse.entity.Machine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MachineBO extends SuperBO {
    public ArrayList<MachineDTO> getAll() throws SQLException, ClassNotFoundException ;

    public String getCurrentId() throws SQLException, ClassNotFoundException ;

    public MachineDTO searchById(String id) throws SQLException, ClassNotFoundException ;

    public boolean save(MachineDTO machine) throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(MachineDTO machine) throws SQLException, ClassNotFoundException ;
}
