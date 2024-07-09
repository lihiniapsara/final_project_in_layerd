package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.MachineBO;
import lk.ijse.dto.MachineDTO;
import lk.ijse.tm.MachineTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MachineForm {
    public AnchorPane root;
    public ChoiceBox <String>cbemployeeId;
    public TextField txtType;
    public TableColumn <?, ?>colId;
    public TableColumn <?, ?>colModel;
    public TableColumn<?, ?> colType;
    public TableView <MachineTm>tblMachine;
    public TextField txtModel;
    public TextField txtId;
    public TextField txtsearch;

    MachineBO machineBO = (MachineBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MACHINE);

    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllMachines();
        getCurrentepId();
        setMachineEmployeeId();
    }
    private void setMachineEmployeeId() {
        ObservableList<String> Employee_ID = FXCollections.observableArrayList(
                "E001", "E002 ", "E003", "E004","E005"
        );
        cbemployeeId.setItems(Employee_ID);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("machineId"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    private void loadAllMachines() {
        ObservableList<MachineTm> obList = FXCollections.observableArrayList();
        try {
            ArrayList<MachineDTO> machineList = machineBO.getAll();

            for (MachineDTO machine : machineList) {
                MachineTm tm = new MachineTm(
                        machine.getMachineId(),
                        machine.getModel(),
                        machine.getType());

                obList.add(tm);
            }

            tblMachine.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtId.setText(null);
        txtModel.setText(null);
        txtType.setText(null);
        cbemployeeId.setValue(null);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {

        String id = txtId.getText();
        try {

            boolean isdeleted = machineBO.delete(id);
            if (isdeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "delete").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "not delete").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();
        String model = txtModel.getText();
        String type = txtType.getText();
        String e_id =  cbemployeeId.getValue();
        try {
            MachineDTO machine = new MachineDTO(id, model, type, e_id);

            boolean issaved = machineBO.update(machine);
            if (issaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "machine update ").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "failed update").show();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        String model = txtModel.getText();
        String type = txtType.getText();
        String e_id = cbemployeeId.getValue();

        if (model.matches("^[a-zA-Z ]+$") &
                type.matches("^[a-zA-Z ]+$")) {
            try {
                MachineDTO machine = new MachineDTO(id, model, type, e_id);

                boolean issaved = machineBO.save(machine);
                if (issaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "machine saved ").show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "failed saved").show();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "not valid data").show();
        }

    }

    private void getCurrentepId() {
        try {
            String currentId = machineBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("M");
            int idNum = Integer.parseInt(split[1]);
            return "M" + ++idNum;
        }
        return "M1";
    }
    public void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtsearch.getText();

        MachineDTO machine = machineBO.searchById(id);
        if (machine != null) {
            txtId.setText(machine.getMachineId());
            txtModel.setText(machine.getModel());
            txtType.setText(machine.getType());
            cbemployeeId.setValue(machine.getEmployeeId());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "machine not found!").show();
        }
    }

    public void idtextKeyReleased(KeyEvent keyEvent) {
    }

    public void modeltextKeyReleased(KeyEvent keyEvent) {
    }

    public void typetextKeyReleased(KeyEvent keyEvent) {
    }
}
