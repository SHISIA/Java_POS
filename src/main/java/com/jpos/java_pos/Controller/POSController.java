package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jpos.java_pos.Model.SalesPOSModel;
import com.jpos.java_pos.Model.Table;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class POSController implements Initializable {
    @FXML
    public JFXButton deleteTicket;
    @FXML
    public JFXButton stackBtn;
    @FXML
    public JFXButton priceCheckBtn;
    @FXML
    public JFXButton logOutBtn;
    @FXML
    public TableView<Table> productsTable;
    @FXML
    public TableColumn<Table,String> nameCol;
    @FXML
    public TableColumn<Table,Number> countCol;
    @FXML
    public TableColumn<Table,Number> priceCol;
    @FXML
    public TableColumn<Table,Number> totalCol;
    @FXML
    public TableColumn<Table,Button> deleteCol;
    @FXML
    public TableColumn<Table,Button> editCol;
    @FXML
    public TextField barcodeSearch;
    @FXML
    public Button findBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SalesPOSModel(productsTable,nameCol,countCol,priceCol,totalCol,editCol,deleteCol,barcodeSearch);
    }


}
