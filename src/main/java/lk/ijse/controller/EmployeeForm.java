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
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.tm.EmployeeTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeForm {

    public Label Id;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private TableColumn<?, ?> colTitle;
    @FXML
    private TableColumn<?, ?> colDepartment;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtDepartment;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);


    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();
        getCurrentepId();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void getCurrentepId() {
        try {
            String currentId = employeeBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("E");
            int idNum = Integer.parseInt(split[1]);
            return "E" + ++idNum;
        }
        return "E1";
    }
    private void loadAllEmployees() {


        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<EmployeeDTO> employeeList = employeeBO.getAll();
            for (EmployeeDTO employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getEmployeeId(),
                        employee.getEmployeename(),
                        employee.getDepartment(),
                        employee.getJob_title(),
                        employee.getAddress(),
                        employee.getContact()
                );

                obList.add(tm);
            }

            tblEmployee.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {


        String id = txtId.getText();
        String name = txtName.getText();
        String department = txtDepartment.getText();
        String title = txtTitle.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();


        EmployeeDTO employee = new EmployeeDTO(id, name, department, title, address, tel,"U001");
        if (name.matches("(^[A-Za-z]{3,16})([ ]{0,1})([A-Za-z]{2,16})?([ ]{0,1})?([A-Za-z]{3,16})?([ ]{0,1})?([A-Za-z]{3,16})")&
                department.matches("^[a-zA-Z ]+$")&
                        title.matches("\\b[sS][aA][lL][eE][sS]\\b\n")&
                                address.matches("^[A-z|\\\\s]{3,}$")&
                                        tel.matches("^\\d{10}$"))
        {
            try {
                boolean isSaved = employeeBO.save(employee);
                if (isSaved) {
                    loadAllEmployees();
                    new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"not valid data").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String id = txtId.getText();
        String name = txtName.getText();
        String department = txtDepartment.getText();
        String title = txtTitle.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        //String u_id = txtId.getText();

        EmployeeDTO employee = new EmployeeDTO(id,name, department, title, address, tel,"U001");

        try {
            boolean isUpdated = employeeBO.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                loadAllEmployees();

            }else{
                new Alert(Alert.AlertType.ERROR,"not updated");
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {


        String tel = txtTel.getText();

        EmployeeDTO employee = employeeBO.searchById(tel);
        if (employee != null) {
            txtId.setText(employee.getEmployeeId());
            txtName.setText(employee.getEmployeename());
            txtDepartment.setText(employee.getDepartment());
            txtTitle.setText(employee.getJob_title());
            txtAddress.setText(employee.getAddress());
            txtTel.setText(employee.getContact());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "employee not found!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        String tel = txtTel.getText();

        try {
            boolean isDeleted = employeeBO.delete(tel);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtDepartment.setText("");
        txtTitle.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


    public void idtextKeyReleased(KeyEvent keyEvent) {

    }

    public void nametextKeyReleased(KeyEvent keyEvent) {

    }

    public void teltextKeyReleased(KeyEvent keyEvent) {

    }

    public void addresstextKeyReleased(KeyEvent keyEvent) {

    }

    public void departmenttextKeyReleased(KeyEvent keyEvent) {

    }

    public void titletextKeyReleased(KeyEvent keyEvent) {
    }
}