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
import lk.ijse.bo.custom.SalaryBO;
import lk.ijse.bo.custom.impl.SalaryBOImpl;
import lk.ijse.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.dto.SalaryDTO;
import lk.ijse.tm.SalaryTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryForm {

    @FXML
    private ChoiceBox<String> cbE_Id;

    @FXML
    private ChoiceBox<String> cbPayPeriod;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPay_Period;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtId;

    SalaryBO salaryBO = (SalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);
    public void initialize() {
        setCellValueFactory();
        loadAllSalary();
        setSalaryPayPeriod();
        setItemsEmployeeId();
        getCurrentepId();
    }
    private void setSalaryPayPeriod() {
        ObservableList<String> Period = FXCollections.observableArrayList(
                "Monthly","Weekly"
        );
        cbPayPeriod.setItems(Period);
    }
    private void setItemsEmployeeId() {
        ObservableList<String> Employee_ID = FXCollections.observableArrayList(
                "E001", "E002 ", "E003", "E004","E005"
        );
        cbE_Id.setItems(Employee_ID);
    }

    private void getCurrentepId() {
        try {
            String currentId = salaryBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("SA");
            int idNum = Integer.parseInt(split[1]);
            return "SA" + ++idNum;
        }
        return "SA1";
    }

    private void loadAllSalary() {
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<SalaryDTO> salaryList = salaryBO.getAll();
            for (SalaryDTO salary : salaryList) {
                SalaryTm tm = new SalaryTm(
                        salary.getSalaryId(),
                        salary.getPayPeriod()
                );

                obList.add(tm);
            }

            tblSalary.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPay_Period.setCellValueFactory(new PropertyValueFactory<>("period"));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        cbPayPeriod.setValue(null);
        cbE_Id.setValue(null);

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
            boolean isDeleted = salaryBO.delete(id);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, " deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String period = cbPayPeriod.getValue();
        String e_id = cbE_Id.getValue();


        SalaryDTO salary = new SalaryDTO(id,period,e_id);

        System.out.println(salary);

        try {
            boolean isSaved = salaryBO.save(salary);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, " saved!").show();
                initialize();
                clearFields();
            }
        } catch (SQLException | ClassNotFoundException e) {
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
           e.printStackTrace();

        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String period = cbPayPeriod.getValue();
        String e_id = cbE_Id.getValue();


        SalaryDTO salary = new SalaryDTO(id,period,e_id);

        try {
            boolean isUpdated = salaryBO.update(salary);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, " updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        SalaryDTO salary = salaryBO.searchById(id);
        if (salary != null) {
            txtId.setText(salary.getSalaryId());
            cbPayPeriod.setValue(salary.getPayPeriod());
            cbE_Id.setValue(salary.getEmployeeId());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    public void idtextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ID,txtId);
    }
}
