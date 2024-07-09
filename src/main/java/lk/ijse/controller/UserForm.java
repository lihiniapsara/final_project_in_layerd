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
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;
import lk.ijse.tm.UserTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserForm {

    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colpw;
    public TableColumn colTe;
    @FXML
    private TableView<UserTm> tblUser;
    @FXML
    public AnchorPane root;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;
    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllUsers();
        getCurrentepId();
    }
    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colpw.setCellValueFactory(new PropertyValueFactory<>("password"));
        colTe.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    private void getCurrentepId() throws SQLException, ClassNotFoundException {
        String currentId = userBO.getCurrentId();

        String nextOrderId = generateNextOrderId(currentId);
        txtId.setText(nextOrderId);
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("U");
            int idNum = Integer.parseInt(split[1]);
            return "U" + ++idNum;
        }
        return "U1";
    }
    private void loadAllUsers() {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<UserDTO> userList = userBO.getAll();
            for (UserDTO user : userList) {
                UserTm tm = new UserTm(
                        user.getUserId(),
                        user.getUserName(),
                        user.getPassword(),
                        user.getContact()
                );

                obList.add(tm);
            }

            tblUser.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtPassword.setText("");
        txtContact.setText("");
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
            boolean isDeleted = userBO.delete(id);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "user deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String tel = txtContact.getText();

        UserDTO user = new UserDTO(id, name,  password, tel);

        try {
            boolean isSaved = userBO.save(user);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
                initialize();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String tel = txtContact.getText();

        UserDTO user = new UserDTO(id, name,  password, tel);
        System.out.println(user);

        try {
            boolean isUpdated = userBO.update(user);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "user updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String tel = txtContact.getText();

        UserDTO user = userBO.searchById(tel);
        if (user != null) {
            txtId.setText(user.getUserId());
            txtName.setText(user.getUserName());
            txtPassword.setText(user.getPassword());
            txtContact.setText(user.getContact());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "user not found!").show();
        }

    }

    public void idtextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ID,txtId);
    }

    public void nametextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName);
    }

    public void passwordtextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PASSWORD,txtPassword);
    }

    public void teltextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.CONTACT,txtContact);
    }
}
