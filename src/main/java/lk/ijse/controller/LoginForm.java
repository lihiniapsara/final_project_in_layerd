package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginForm {

    public AnchorPane rootNode;
    public TextField txtId;
    public TextField txtPw;
    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String id = txtId.getText();
        String pw = txtPw.getText();

        try {
            checkCredential(id, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    private void checkCredential(String id, String pw) throws SQLException, IOException {
        try {
            boolean isValidUser = userBO.checkCredentials(id, pw);
            if (isValidUser) {
                System.out.println("User name and password correct >>>>>");
                navigateDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "Sorry! Password is incorrect!").show();
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "An error occurred while checking credentials!").show();
        }

    }
    private void navigateDashboard() throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/resources/view/DashBoard_form.fxml"));

        Scene scene = new Scene(root);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");


    }

    public void linkRegistrationOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/resources/view/registration_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Registration Form");

        stage.show();


    }

    public void idtextKeyReleased(KeyEvent keyEvent) {
    }
}
