package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.custom.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;


import java.io.IOException;
import java.sql.SQLException;

public class RegistrationForm {

    public TextField txtName;
    public TextField txtPw;
    public TextField txtId;
    public TextField txtContact;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        String id = txtId.getText();
        String name = txtName.getText();
        String password = txtPw.getText();
        String tel = txtContact.getText();


        try {
            UserDTO user = new UserDTO(id, name,  password, tel);
            boolean isSaved = userBO.save(user);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
                clearField();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();


        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/resources/view/Login_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");

        stage.show();
    }


    private void clearField() {
        txtId.setText("");
        txtName.setText("");
        txtPw.setText("");

    }

    public void idtextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ID,txtId);
    }

    public void nametextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName);
    }

    public void passwordtextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.PASSWORD,txtPw);
    }

    public void teltextKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.TEL,txtContact);
    }
}