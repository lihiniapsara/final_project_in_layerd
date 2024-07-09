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
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.custom.impl.PaymentBOImpl;
import lk.ijse.dto.PaymentDTO;
import lk.ijse.tm.PaymentTm;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PaymentForm {

    @FXML
    private ChoiceBox<String> cbMethod;

    @FXML
    private TableColumn<?, ?> coAmount;

    @FXML
    private TableColumn<?, ?> coDate;

    @FXML
    private TableColumn<?, ?> coId;

    @FXML
    private TableColumn<?, ?> coMethod;

    @FXML
    private DatePicker dpDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtId;

    PaymentBO paymentBO= (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllPayments();
        setPaymentMethodtype();
        getCurrentepId();
       // setPaymentType();
        //setorderIdcmb();
    }


    private void setPaymentMethodtype() {
        ObservableList<String> paymentmethod = FXCollections.observableArrayList(
                "Cash", "Credit Card", "Debit Card", "Check"
        );
        cbMethod.setItems(paymentmethod);
    }


    private void loadAllPayments() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<PaymentDTO> paymentList = paymentBO.getAll();
            for (PaymentDTO payment : paymentList) {
                PaymentTm tm = new PaymentTm(
                        payment.getPaymentId(),
                        payment.getMethod(),
                        payment.getDate(),
                        payment.getAmount()
                );

                obList.add(tm);
            }
            tblPayment.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        coId.setCellValueFactory(new PropertyValueFactory<>("id"));
        coMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        coDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        coAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
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
        cbMethod.setValue(null);
        dpDate.setValue(null);
        txtAmount.setText(null);
    }

    private void getCurrentepId() {
        try {
            String currentId = paymentBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("P");
            int idNum = Integer.parseInt(split[1]);
            return "P" + ++idNum;
        }
        return "P1";
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {

            boolean isdeleted = paymentBO.delete(id);
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
        String method = cbMethod.getValue();
        String  date = String.valueOf(dpDate.getValue());
        String amount = txtAmount.getText();

        PaymentDTO payment = new PaymentDTO(id,method, date,amount);

        try {
            boolean isUpdated = paymentBO.update(payment);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String method = cbMethod.getValue();
        String  date = String.valueOf(dpDate.getValue());
        String amount = txtAmount.getText();


        PaymentDTO payment = new PaymentDTO(id,method, date,amount);

        try {
            boolean isSaved = paymentBO.save(payment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment saved!").show();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void clearFields() {
        txtId.setText("");
        cbMethod.getValue();
        dpDate.getValue();
        txtAmount.setText("");

    }

    public void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        PaymentDTO payment = paymentBO.searchById(id);
        if (payment != null) {
            txtId.setText(payment.getPaymentId());
            cbMethod.setValue(payment.getMethod());
            dpDate.setValue(LocalDate.parse(payment.getDate()));
            txtAmount.setText(payment.getAmount());


        } else {
            new Alert(Alert.AlertType.INFORMATION, "payment not found!").show();
        }
    }

    public void amounttextKeyReleased(KeyEvent keyEvent) {
    }

    public void idtextKeyReleased(KeyEvent keyEvent) {

    }
}
