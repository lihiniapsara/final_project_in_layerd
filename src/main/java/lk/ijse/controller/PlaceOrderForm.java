package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.ItemBO;
import lk.ijse.bo.custom.PaymentBO;
import lk.ijse.bo.custom.PlaseOrderBO;
import lk.ijse.bo.custom.impl.ItemBOImpl;
import lk.ijse.db.DBConnection;


import lk.ijse.dto.*;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderItemDetail;
import lk.ijse.tm.CartTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class PlaceOrderForm {

    public Label txtO_Id;
    public Label l_id;
    public Label lCustomerName;
    public Label lUnitPrice;
    public Label lQtyOnHand;
    public Label netTotlelbl;
    public Label lblOrderDate;
    public JFXButton btnAddToCart;
    @FXML
    private JFXButton btnAddToCartOnAction;

    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemId;

    @FXML
    private JFXComboBox<String> cmbPAymentMethod;

    @FXML
    private TableColumn<?, ?> colAction1;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colqty;


    @FXML
    private Label lDescription;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CartTm> tblOrderCart;

    @FXML
    private TextField txtQty;

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
    PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    PlaseOrderBO plaseOrderBO = (PlaseOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACEORDER);

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();



    public void initialize() throws SQLException, ClassNotFoundException {
        getCurrentid();
        setCmbCustomerId();
        getCustomerName();
        setCmbItemId();
        setPaymentMethodtype();
        setCellValueFactory();
        setDate();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction1.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void getCustomerName() {
        try {
            String id = cmbCustomerId.getValue();
            String name = customerBO.getName(id);


            lCustomerName.setText(name);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void getCurrentid() {

        try {
            String currentId = plaseOrderBO.getCurrentId();

            String nextLocationIdId = generateNextLocationId(currentId);
            l_id.setText(nextLocationIdId);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextLocationId(String currentId) {
        try {
            if (currentId != null) {
                String[] split = currentId.split("O");
                int idNum = Integer.parseInt(split[1]);
                return "O" + (++idNum);
            }

        }catch (Exception e){

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return "O1";
    }

    private void setCmbCustomerId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> customerList = customerBO.getIds();

            for (String customer : customerList) {
                obList.add(customer);
            }
            cmbCustomerId.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/resources/view/Dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/resources/view/Customer_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String Pid = paymentBO.getCurrentId();
        String nextPid= nextPid(Pid);
        String orderId = l_id.getText();
        String cusId = cmbCustomerId.getValue();
        String desc = lDescription.getText();
        String date = lblOrderDate.getText();
        OrderDTO order = new OrderDTO(orderId,desc, date,netTotlelbl.getText(),"D001",nextPid,cusId);

        List<OrderItemDetailDTO> odList = new ArrayList<>();
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            CartTm tm = obList.get(i);

            OrderItemDetailDTO od = new OrderItemDetailDTO(
                    tm.getId(),
                    orderId
            );
            odList.add(od);
        }


        PlaceOrderDTO po = new PlaceOrderDTO(order, odList);
        System.out.println(po);

        String method = cmbPAymentMethod.getValue();

        PaymentDTO paymentDTO = new PaymentDTO(nextPid,method,date,netTotlelbl.getText());

        OrderItemDetailDTO iod = new OrderItemDetailDTO(l_id.getText(),cmbItemId.getValue());
        CartTm tt = null;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            CartTm tm = obList.get(i);
            tt= tm;
        }
        Boolean isplaced = plaseOrderBO.purchesOrder(po, paymentDTO, iod ,tt);

    }

    private String nextPid(String pid) {
        try {
            if (pid != null) {
                String[] split = pid.split("P");
                int idNum = Integer.parseInt(split[1]);
                return "P" + (++idNum);
            }

        }catch (Exception e){

            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        return "P1";
    }

    private void setCmbItemId() throws SQLException, ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> itemList =itemBO.getIds();

        for (String item : itemList) {
            obList.add(item);
        }
        cmbItemId.setItems(obList);

    }
    public void customerCmbOnAction(ActionEvent event) {
       getCustomerName();
    }
    private void setPaymentMethodtype() {
        ObservableList<String> paymentmethod = FXCollections.observableArrayList(
                "Cash", "Credit Card", "Debit Card", "Check"
        );
        cmbPAymentMethod.setItems(paymentmethod);
    }

    public void itemCmbOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ItemBOImpl itemBO=new ItemBOImpl();
         ItemDTO item =  itemBO.searchById(cmbItemId.getValue());

         lDescription.setText(item.getDescription());
         lUnitPrice.setText(item.getPrice());
         lQtyOnHand.setText(item.getQty());
    }

    public void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemId.getValue();
        String description = lDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lUnitPrice.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                
                obList.remove(selectedIndex);

                tblOrderCart.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if(code.equals(colItemId.getCellData(i))) {

                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;

                tm.setQty(qty);
                tm.setTotal(total);

                tblOrderCart.refresh();

                calculateNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(code,description,qty,unitPrice,total,btnRemove);
        obList.add(tm);

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }

   /* void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemId.getValue();
        String description = lDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lUnitPrice.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblOrderCart.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if(code.equals(colItemId.getCellData(i))) {

                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;

                tm.setQty(qty);
                tm.setTotal(total);

                tblOrderCart.refresh();

                calculateNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(code, description, qty, unitPrice, total, btnRemove);
        obList.add(tm);

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }
*/

    private void calculateNetTotal() {
        int netTotal = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
        }
        netTotlelbl.setText(String.valueOf(netTotal));
    }

    public void PrintOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/KPI_BILL.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> dataa = new HashMap<>();
        dataa.put("orderId",l_id.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, dataa, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

}
