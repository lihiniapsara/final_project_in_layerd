package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.VehicalBO;
import lk.ijse.bo.custom.impl.VehicalBOImpl;
import lk.ijse.dto.VehicalDTO;
import lk.ijse.entity.Vehical;
import lk.ijse.tm.VehicalTm;


import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicalForm {

    public TextField txtsearch;
    @FXML
   private ChoiceBox<String> cbE_Id;


    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colModel;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<VehicalTm> tblVehical;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtType;
    VehicalBO vehicalBO= (VehicalBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.VEHICAL);
    public void initialize() {
        setCellValueFactory();
        loadAllVehicals();
        setV_EmployeeId();
        getCurrentepId();
    }

    private void setV_EmployeeId() {
        ObservableList<String> Employee_ID = FXCollections.observableArrayList(
                "E001", "E002 ", "E003", "E004","E005"
        );
        cbE_Id.setItems(Employee_ID);
    }

    private void getCurrentepId() {
        try {
            String currentId = vehicalBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("V");
            int idNum = Integer.parseInt(split[1]);
            return "V" + ++idNum;
        }
        return "V1";
    }
    private void loadAllVehicals() {
        ObservableList<VehicalTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<VehicalDTO> vehicalList = vehicalBO.getAll();
            for (VehicalDTO vehical : vehicalList) {
                VehicalTm tm = new VehicalTm(
                        vehical.getVehicleId(),
                        vehical.getModel(),
                        vehical.getType()
                       ///vehical.getEmployeeId()
                );

                obList.add(tm);
            }

            tblVehical.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtModel.setText("");
        txtType.setText("");
        cbE_Id.setValue(null);
        txtsearch.setText("");
    }
    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = vehicalBO.delete(id);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "vehical deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String model = txtModel.getText();
        String type = txtType.getText();
        String e_id = cbE_Id.getValue();

        VehicalDTO vehical = new VehicalDTO(id, model, type, e_id);


       // System.out.println("palaweni eka   "+vehical);

        try {
            boolean isSaved = vehicalBO.save(vehical);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "vehical saved!").show();
                initialize();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String model = txtModel.getText();
        String type = txtType.getText();
        String e_id = cbE_Id.getValue();

        VehicalDTO vehical = new VehicalDTO(id, model, type, e_id);

        try {
            boolean isUpdated = vehicalBO.update(vehical);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "vehical updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtsearch.getText();

        VehicalDTO vehical = vehicalBO.searchById(id);
        if (vehical != null) {
                txtId.setText(vehical.getVehicleId());
                txtModel.setText(vehical.getModel());
                txtType.setText(vehical.getType());
                cbE_Id.setValue(vehical.getEmployeeId());

            } else {
            new Alert(Alert.AlertType.INFORMATION, "vehical not found!").show();
        }
    }

    public void modeltextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.MODEL,txtModel);
    }

    public void idtextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ID,txtId);
    }

    public void typetextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.TYPE,txtType);
    }
}
