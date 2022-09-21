package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jpos.java_pos.Model.SalesPOSModel;
import com.jpos.java_pos.Model.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    public TableView<Product> productsTable;
    @FXML
    public TableColumn<Product,String> nameCol;
    @FXML
    public TableColumn<Product,Number> countCol;
    @FXML
    public TableColumn<Product,Number> priceCol;
    @FXML
    public TableColumn<Product,Number> totalCol;
    @FXML
    public TableColumn<Product,Button> deleteCol;
    @FXML
    public TableColumn<Product,Button> editCol;
    @FXML
    public TextField barcodeSearch;
    @FXML
    public Button findBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SalesPOSModel(productsTable,nameCol,countCol,priceCol,totalCol,editCol,deleteCol,barcodeSearch,findBtn);
        selectedItemEvent();
    }

    public void selectedItemEvent(){
        productsTable.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {

                    barcodeSearch.setText(newValue.getProductName() + " "
                            + newValue.getProductName());
                    // If you want to get the value of a selected student cell at
                    // anytime, even if it hasn't changed. Just do e.g.
                    // studentsTable.getSelectionModel().getSelectedItem().getFirstName()
                });
    }


}
