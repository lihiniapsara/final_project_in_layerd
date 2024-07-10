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
import lk.ijse.bo.custom.StockBO;
import lk.ijse.bo.custom.impl.SupplierBOImpl;
import lk.ijse.dto.StockDTO;
import lk.ijse.dto.SupplierStockDetailDTO;
import lk.ijse.tm.StockTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockForm {

    public TableColumn <?,?> colLevel;
    public TextField txtLevel;
    public ChoiceBox<String> cbS_Id;
    public TextField txtCategory;


    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colId;


    @FXML
    private AnchorPane root;

    @FXML
    private TableView<StockTm> tblStock;

    @FXML
    private TextField txtId;

    StockBO stockBO= (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);
    SupplierBOImpl supplierBO= (SupplierBOImpl) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllStock();
       // SETStockCategory();
        setSupplierId();
        getCurrentepId();
    }

    private void getCurrentepId() {
        try {
            String currentId = stockBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("S");
            int idNum = Integer.parseInt(split[1]);
            return "S" + ++idNum;
        }
        return "S1";
    }
    private void setSupplierId() throws SQLException, ClassNotFoundException {
        ObservableList<String> sid = FXCollections.observableArrayList();

        List<String> idList = supplierBO.getIds();
         for (String id : idList){
             sid.add(id);
         }

        cbS_Id.setItems(sid);
    }
    private void loadAllStock() {
        ObservableList<StockTm> obList = FXCollections.observableArrayList();

        try {
            ArrayList<StockDTO> stockList = stockBO.getAll();
            for (StockDTO stock : stockList) {
                StockTm tm = new StockTm(
                        stock.getStockId(),
                        stock.getCategory(),
                        stock.getStock_level()
                );

                obList.add(tm);
            }

            tblStock.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colLevel.setCellValueFactory(new PropertyValueFactory<>("level"));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }
    private void clearFields() {
        txtId.setText("");
        txtCategory.setText("");
        txtLevel.setText("");
        cbS_Id.setValue("");

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
            boolean isDeleted = stockBO.delete(id);
            if (isDeleted) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "stock deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String category = txtCategory.getText();
        String level = txtLevel.getText();
        String s_id = cbS_Id.getValue();
        String sid = cbS_Id.getValue();
        String stockid = txtId.getText();

        StockDTO stock=new StockDTO(id,category,level);
        SupplierStockDetailDTO supplierStockDetailDTO = new SupplierStockDetailDTO(sid,stockid);
if (category.matches("^[a-zA-Z ]+$")&
level.matches("^\\d+(\\.\\d{2})?$")) {
    Boolean isSaved = stockBO.saveData(stock, supplierStockDetailDTO);

    if (isSaved) {
        new Alert(Alert.AlertType.CONFIRMATION, "data saved").show();
    } else {
        new Alert(Alert.AlertType.ERROR, "data not saved").show();
    }

}
    else {
    new Alert(Alert.AlertType.ERROR,"not valid data").show();

}
        clearFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String category = txtCategory.getText();
        String level = txtLevel.getText();
        String s_id = cbS_Id.getValue();

        StockDTO stock=new StockDTO(id,category,level);

        try {
            boolean isUpdated = stockBO.update(stock);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "stock updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        StockDTO stock = stockBO.searchById(id);
        if (stock != null) {
            txtId.setText(stock.getStockId());
            txtCategory.setText(stock.getCategory());
            txtLevel.setText(stock.getStock_level());


        } else {
            new Alert(Alert.AlertType.INFORMATION, "stock not found!").show();
        }
    }



    public void leveltextKeyReleased(KeyEvent keyEvent) {

    }

    public void idtextKeyReleased(KeyEvent keyEvent) {

    }

    public void categorytextKeyReleased(KeyEvent keyEvent) {

    }
}
