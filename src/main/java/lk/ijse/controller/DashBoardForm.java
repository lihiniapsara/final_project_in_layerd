package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardForm {
    public AnchorPane root;
    public AnchorPane ancorpane;

    public void loadCustomerPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Customer_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadItemPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Item_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadPaymentPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Payment_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadStockPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Stock_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadSupplierPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Supplier_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadSalaryPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Salary_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadPlaceOrderPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/PlaceOrder_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadDeliveryPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Delivery_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadMachinePageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Machine_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadVehicalPageAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Vehical_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Login_form.fxml"));
        Parent rootNode = loader.load();

        // Get the stage
        Stage stage = (Stage) root.getScene().getWindow();
        if (stage == null) {
            System.err.println("Stage is null");
            return;
        }

        if (rootNode == null) {
            System.err.println("FXML file not loaded properly");
            return;
        }

        // Assuming 'ancorpane' is the correct reference to your AnchorPane
        AnchorPane anchorPane = (AnchorPane) rootNode;

        // Set the scene with the login form
        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    public void loadUserPageAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/User_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }

    public void loadIEmployeePageAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/Employee_form.fxml"));
        Parent rootNode = loader.load();
        ancorpane.getChildren().clear();
        ancorpane.getChildren().add(rootNode);
    }
}
