package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jpos.java_pos.Model.Product;
import com.jpos.java_pos.Model.SalesPOSModel;
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
    public JFXButton backSpaceBtn;
    public JFXButton clearBtn;
    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;
    public Button six;
    public Button seven;
    public Button eight;
    public Button nine;
    public Button doubleO;
    public Button zero;
    public Button multiply;
    public Button plusBtn;
    public Button minusBtn;
    public TableView smartList;
    public Button checkout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SalesPOSModel(productsTable,nameCol,countCol,priceCol,totalCol,editCol,deleteCol,barcodeSearch,findBtn,checkout,
        plusBtn,minusBtn,smartList,multiply,zero,one,two,three,four,five,six,seven,eight,nine,doubleO,clearBtn,backSpaceBtn);
    }
}
