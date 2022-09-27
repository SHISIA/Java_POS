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
    @FXML
    public JFXButton backSpaceBtn;
    @FXML
    public JFXButton clearBtn;
    @FXML
    public Button one;
    @FXML
    public Button two;
    @FXML
    public Button three;
    @FXML
    public Button four;
    @FXML
    public Button five;
    @FXML
    public Button six;
    @FXML
    public Button seven;
    @FXML
    public Button eight;
    @FXML
    public Button nine;
    @FXML
    public Button doubleO;
    @FXML
    public Button zero;
    @FXML
    public Button multiply;
    @FXML
    public Button plusBtn;
    @FXML
    public Button minusBtn;
    @FXML
    public TableView smartList;
    @FXML
    public JFXButton checkout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SalesPOSModel(productsTable,nameCol,countCol,priceCol,totalCol,editCol,deleteCol,barcodeSearch,findBtn,checkout,
        plusBtn,minusBtn,smartList,multiply,zero,one,two,logOutBtn,three,four,five,six,seven,eight,nine,doubleO,clearBtn,backSpaceBtn,deleteTicket);
    }
}
