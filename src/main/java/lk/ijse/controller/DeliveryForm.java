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
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DeliveryBO;
import lk.ijse.dto.DeliveryDTO;
import lk.ijse.tm.DeliveryTm;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeliveryForm {
    public TextField txtSatus;
    @FXML
    private ComboBox<String> cmbVehicalId;
    @FXML
    private DatePicker dpDeliveryDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    public AnchorPane root;

    @FXML
    private TableView<DeliveryTm> tblDelivery;

    @FXML
    private TextField txtId;

    DeliveryBO deliveryBO= (DeliveryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DELIVERY);


    public void initialize() {
        setCellValueFactory();
        loadAllDeliverys();
        setDeliveryVehicalId();
        getCurrentepId();
    }
    private void setDeliveryVehicalId() {
        ObservableList<String> Vehical_ID = FXCollections.observableArrayList(
                "V001", "V002 ", "V003", "V004","V005"
        );
        cmbVehicalId.setItems(Vehical_ID);
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("deliveryId"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void getCurrentepId() {

        try {
            String currentId = deliveryBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("D");
            int idNum = Integer.parseInt(split[1]);
            return "D" + ++idNum;
        }
        return "D1";
    }
    private void loadAllDeliverys() {

        ObservableList<DeliveryTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<DeliveryDTO> deliveryList = deliveryBO.getAll();
            for (DeliveryDTO delivery : deliveryList) {
                DeliveryTm tm = new DeliveryTm(
                        delivery.getDeliveryId(),
                        delivery.getStatus(),
                        delivery.getDate()
                );

                obList.add(tm);
            }

            tblDelivery.setItems(obList);
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

    public void btnDeleteOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();

        try {
            boolean isDeleted = deliveryBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "deliver deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtSatus.setText("");
        dpDeliveryDate.setValue(null);
        cmbVehicalId.setValue(null);
    }
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = txtId.getText();
        String  date = String.valueOf(dpDeliveryDate.getValue());
        String status = txtSatus.getText();
        String v_id = cmbVehicalId.getValue();

        DeliveryDTO delivery = new DeliveryDTO(id, status,date,v_id);

        try {
            boolean isUpdated = deliveryBO.update(delivery);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "deliver updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {


        String id = txtId.getText();
        String status = txtSatus.getText();
        String date = String.valueOf(dpDeliveryDate.getValue());
        String v_id = cmbVehicalId.getValue();


        DeliveryDTO delivery = new DeliveryDTO(id,status,date,v_id);

if (status.matches("^[a-zA-Z ]+$")){
        try {
            boolean isSaved = deliveryBO.save(delivery);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "deliver saved!").show();
                initialize();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
}else{
    new Alert(Alert.AlertType.ERROR,"not valid data").show();
}clearFields();
    }

    public void txtsearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String id = txtId.getText();

        DeliveryDTO delivery = deliveryBO.searchById(id);

        if (delivery != null) {
            txtId.setText(delivery.getDeliveryId());
            txtSatus.setText(delivery.getStatus());
            dpDeliveryDate.setValue(LocalDate.parse(delivery.getDate()));
            cmbVehicalId.setValue(delivery.getDeliveryId());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "deliver not found!").show();
        }
    }

    public void dpDeliveryOnAction(ActionEvent event) {
    }

    public void cmbVehicalOnAction(ActionEvent event) {
    }

    public void idtextKeyReleased(KeyEvent keyEvent) {

    }

    public void statustextKeyReleased(KeyEvent keyEvent) {

    }

}
