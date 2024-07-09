package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;


import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.impl.CustomerBOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;
import lk.ijse.tm.CustomerTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CustomerForm {
    public TableView tblCustomer;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    public AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;
    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllCustomers();
        getCurrentecId();
    }


    private void getCurrentecId() throws SQLException, ClassNotFoundException {
        String currentId = customerBO.getCurrentId();

        String nextOrderId = generateNextOrderId(currentId);
        txtId.setText(nextOrderId);
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("C");
            int idNum = Integer.parseInt(split[1]);
            return "C" + ++idNum;
        }
        return "C1";
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<CustomerDTO> customerList = customerBO.getAll();
            for (CustomerDTO customer : customerList) {
                obList.add(new CustomerTm(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getContact()));
                
            }

            tblCustomer.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

@FXML
    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
    AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_form.fxml"));
    Stage stage = (Stage) root.getScene().getWindow();

    stage.setScene(new Scene(anchorPane));
    stage.setTitle("Dashboard Form");
    stage.centerOnScreen();
    }
@FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
    clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String tel = txtTel.getText();

        try {
            boolean isDeleted = customerBO.deletecustomer(tel);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
@FXML
    public void btnUpdateOnAction(ActionEvent actionEvent) {


    String id = txtId.getText();
    String name = txtName.getText();
    String address = txtAddress.getText();
    String tel = txtTel.getText();


    try {
        boolean isUpdated = customerBO.updatecustomer(new CustomerDTO(id, name, address, tel));
        if (isUpdated) {
            initialize();
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
        }
    } catch (SQLException | ClassNotFoundException e) {
        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
    }
    }
@FXML
    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    String id = txtId.getText();
    String name = txtName.getText();
    String address = txtAddress.getText();
    String tel = txtTel.getText();


    if(name.matches("^[A-Za-z\\s]+$")&
             address.matches("^[\\w\\s,.-]+$")&
            tel.matches("^(\\+\\d{1,3}[- ]?)?\\d{10}$")
    ) {


        try {
            boolean isSaved = customerBO.savecustomer(new CustomerDTO(id, name, address, tel));
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                initialize();
                clearFields();
            }
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Customer saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save customer!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }else {
        new Alert(Alert.AlertType.ERROR,"not valid data").show();

    }
}
@FXML
    public void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String tel = txtTel.getText();

        CustomerDTO customer = customerBO.searchById(tel);
        if (customer != null) {
            txtId.setText(customer.getCustomerId());
            txtName.setText(customer.getName());
            txtAddress.setText(customer.getAddress());
            txtTel.setText(customer.getContact());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    public void nametextKeyReleased(KeyEvent keyEvent) {

    }

    public void teltextKeyReleased(KeyEvent keyEvent) {
    }

    public void addresstextKeyReleased(KeyEvent keyEvent) {
    }

    public void idtextKeyReleased(KeyEvent keyEvent) {
    }
}
