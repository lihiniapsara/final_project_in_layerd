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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.SupplierBO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.tm.SupplierTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SupplierForm {
    public ChoiceBox <String>cmbTerms;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colTerms;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colTel;
    public AnchorPane root;
    @FXML
    private TableView<SupplierTm> tblSupplier;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtTel;

    SupplierBO supplierBO= (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    public void initialize() {
       setCellValueFactory();
       loadAllSuppliers();
        setSupplierPaymentTerms();
        getCurrentepId();
    }

    private void getCurrentepId() {
        try {
            String currentId = supplierBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("SU");
            int idNum = Integer.parseInt(split[1]);
            return "SU" + ++idNum;
        }
        return "SU1";
    }
    private void setSupplierPaymentTerms() {
        ObservableList<String> Terms = FXCollections.observableArrayList(
                "30 days","45 days","14 days","7 days"
        );
        cmbTerms.setItems(Terms);
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTerms.setCellValueFactory(new PropertyValueFactory<>("terms"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierList = supplierBO.getAll();
            for (SupplierDTO supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getName(),
                        supplier.getPaymentTerms(),
                        supplier.getAddress(),
                        supplier.getContact()
                );

                obList.add(tm);
            }

            tblSupplier.setItems(obList);
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
            boolean isDeleted = supplierBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "supp deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        cmbTerms.setValue(null);
        txtAddress.setText("");
        txtTel.setText("");
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String terms = cmbTerms.getValue();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        SupplierDTO supplier = new SupplierDTO(id, name, terms, address, tel);

if (name.matches("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{2,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})")&
address.matches("^[A-z|\\\\s]{3,}$")&
tel.matches("^\\d{10}$")) {
    try {
        boolean isSaved = supplierBO.save(supplier);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
            initialize();
            clearFields();
        }
    } catch (SQLException | ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}else {
    new Alert(Alert.AlertType.ERROR,"not valid data").show();

}
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String terms = cmbTerms.getValue();
        String address = txtAddress.getText();
        String tel = txtTel.getText();

        SupplierDTO supplier = new SupplierDTO(id,name,terms,address,tel);

        try {
            boolean isUpdated = supplierBO.update(supplier);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String tel = txtTel.getText();

        SupplierDTO supplier = supplierBO.searchById(tel);

        if (supplier != null) {
                txtId.setText(supplier.getSupplierId());
                txtName.setText(supplier.getName());
                cmbTerms.setValue(supplier.getPaymentTerms());
                txtAddress.setText(supplier.getAddress());
                txtTel.setText(supplier.getContact());

            } else {
            new Alert(Alert.AlertType.INFORMATION, "supplier not found!").show();
        }
    }

    public void cmbItemOnAction(MouseEvent mouseEvent) {
    }

    public void idtextKeyReleased(KeyEvent keyEvent) {

    }

    public void nametextKeyReleased(KeyEvent keyEvent) {

    }

    public void addresstextKeyReleased(KeyEvent keyEvent) {

    }

    public void teltextKeyReleased(KeyEvent keyEvent) {

    }
}
