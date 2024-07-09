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
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.tm.ItemTm;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemForm {
    public TableColumn <?,?>colDescription;
    public ComboBox<String> cbStockId;
    public TextField txtQty;
    public TextField txtsearch;
    @FXML
    private ComboBox<String> cmbDescription;
    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colPrice;
    public AnchorPane root;
    @FXML
    private TableView<ItemTm> tblItem;
    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPrice;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
        setItemsDescription();
        setItemsStockId();
        getCurrentepId();
    }
    private void setItemsDescription() {
        ObservableList<String> Description = FXCollections.observableArrayList(
                "GO(7*4)", "KPI(10*5) ", "VARGINE(11*5)", "10*14(W)","JML(12*18)"
        );
        cmbDescription.setItems(Description);
    }
    private void setItemsStockId() {
        ObservableList<String> Stock_ID = FXCollections.observableArrayList(
                "S001", "S002 ", "S003", "S004","S005"
        );
        cbStockId.setItems(Stock_ID);
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();


        try {
            ArrayList<ItemDTO> itemList = itemBO.getAll();
            for (ItemDTO item : itemList) {
                ItemTm tm = new ItemTm(
                        item.getItemId(),
                        item.getDescription(),
                        item.getPrice()
                );


                obList.add(tm);
            }

            tblItem.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));



    }
    public void btnDashboardOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
    private void clearFields() {
        txtId.setText("");
        cmbDescription.setValue(null);
        txtPrice.setText("");
        txtQty.setText("");
        cbStockId.setValue(null);
        txtsearch.setText("");
    }
    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void getCurrentepId() {
        try {
            String currentId = itemBO.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("I");
            int idNum = Integer.parseInt(split[1]);
            return "I" + ++idNum;
        }
        return "I1";
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();

        try {
            boolean isDeleted = itemBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "item deleted!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String qty = txtQty.getText();
        String description = cmbDescription.getValue();
        String price = txtPrice.getText();
        String s_id = cbStockId.getValue();

        ItemDTO item = new ItemDTO(id, description,price,qty,s_id);


        try {
            boolean isUpdated = itemBO.update(item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clearFields();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String qty = txtQty.getText();

        String id = txtId.getText();
        String description = cmbDescription.getValue();
        String price = txtPrice.getText();
        String s_id = cbStockId.getValue();
        //String qty = txtQty.getText();


        ItemDTO item = new ItemDTO(id,description,price,qty,s_id);

        if (description.matches("\\b[sS][aA][lL][eE][sS]\\b\n")&
        price.matches("^\\d+(\\.\\d{2})?$")) {

            try {
                boolean isSaved = itemBO.save(item);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "item saved!").show();
                    clearFields();
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"not valid data").show();
        }
    }

   /* public void cmbItemOnAction(ActionEvent event) {
    }
*/
    public void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {

        String id = txtsearch.getText();

        ItemDTO item = itemBO.searchById(id);
        if (item != null) {
                txtId.setText(item.getItemId());
                cmbDescription.setValue(item.getDescription());
                txtQty.setText(item.getQty());
                txtPrice.setText(item.getPrice());
                cbStockId.setValue(item.getStockId());

            } else {
            new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
        }
    }

    public void pricetextKeyReleased(KeyEvent keyEvent) {

    }

    public void idtextKeyReleased(KeyEvent keyEvent) {

    }
    public void qtytextKeyReleased(KeyEvent keyEvent) {

    }
}
