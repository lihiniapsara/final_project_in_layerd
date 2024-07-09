package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.MachineBO;
import lk.ijse.dao.custom.impl.MachineDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.MachineDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Machine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineBOImpl implements MachineBO {
    MachineDAOImpl machineDAO = new MachineDAOImpl();
    public ArrayList<MachineDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<MachineDTO> allMachines= new ArrayList<>();
        List<Machine> all = machineDAO.getAll();
        for (Machine m:all){
            allMachines.add(new MachineDTO(m.getMachineId(),m.getModel(),m.getType(),m.getEmployeeId()));
        }
        return allMachines;
    }

    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return machineDAO.currentId();
    }

    public MachineDTO searchById(String id) throws SQLException, ClassNotFoundException {
        Machine machine = machineDAO.search(id);
        return new MachineDTO(machine.getMachineId(),machine.getModel(),machine.getType(),machine.getEmployeeId());
    }

    public boolean save(MachineDTO machine) throws SQLException, ClassNotFoundException {
        return machineDAO.save(new Machine(machine.getMachineId(),machine.getModel(),machine.getType(),machine.getEmployeeId()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.delete(id);
    }

    public boolean update(MachineDTO machine) throws SQLException, ClassNotFoundException {
        return machineDAO.update(new Machine(machine.getMachineId(),machine.getModel(),machine.getType(),machine.getEmployeeId()));
    }
}
