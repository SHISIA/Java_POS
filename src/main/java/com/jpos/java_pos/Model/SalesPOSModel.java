package com.jpos.java_pos.Model;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.barcode.BarCode;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.jfoenix.controls.JFXButton;
import com.jpos.java_pos.Controller.HomeController;
import com.jpos.java_pos.Controller.SettingController;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.print.PrintService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesPOSModel {
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

    public JFXButton deleteTicket;
     
    public JFXButton stackBtn;
     
    public JFXButton priceCheckBtn;
     
    public JFXButton logOutBtn;

    public JFXButton checkoutBtn;
     
    public TableView<Product> productsTable;
     
    public TableColumn<Product,String> nameCol;
     
    public TableColumn<Product,Number> countCol;
     
    public TableColumn<Product,Number> priceCol;
     
    public TableColumn<Product,Number> totalCol;
     
    public TableColumn<Product, Button> deleteCol;
     
    public TableColumn<Product,Button> editCol;
     
    public TextField barcodeSearch;
     
    public Button findBtn;

    DbConnector connector=new DbConnector();

    public SalesPOSModel(TableView<Product> productsTable, TableColumn<Product,String> nameCol, TableColumn<Product,Number> countCol, TableColumn<Product,Number> priceCol
            , TableColumn<Product,Number> totalCol, TableColumn<Product,Button> editCol, TableColumn<Product, Button> deleteCol, TextField barcodeSearch, Button findBtn, JFXButton checkout,
                         Button plusBtn,Button minusBtn,TableView smartList,Button multiply,Button zero, Button one,Button two,JFXButton logOutBtn,
                         Button three,Button four,Button five,Button six,Button seven,Button eight,Button nine,Button doubleO,JFXButton clearBtn,JFXButton backSpaceBtn,JFXButton deleteTicket) {
        this.productsTable=productsTable;
        this.nameCol=nameCol;
        this.countCol=countCol;
        this.priceCol=priceCol;
        this.findBtn=findBtn;
        this.totalCol=totalCol;
        this.editCol=editCol;
        this.deleteCol=deleteCol;
        this.barcodeSearch=barcodeSearch;
        this.checkoutBtn=checkout;
        this.zero=zero;
        this.one=one;
        this.two=two;
        this.three=three;
        this.four=four;
        this.five=five;
        this.six=six;
        this.seven=seven;
        this.doubleO=doubleO;
        this.eight=eight;
        this.nine=nine;
        this.multiply=multiply;
        this.backSpaceBtn=backSpaceBtn;
        this.clearBtn=clearBtn;
        this.plusBtn=plusBtn;
        this.minusBtn=minusBtn;
        this.deleteTicket=deleteTicket;
        this.logOutBtn=logOutBtn;
        connector.setCredentials();
        checkoutBtn.setOnAction(e->checkOutTicket());
        onScreenKeys();
        logOut();
    }

    void logOut(){
        logOutBtn.setOnAction(e->{
            Stage stage=(Stage) logOutBtn.getScene().getWindow();
            stage.close();
            new ScreenLoader().load("/com/jpos/pos/Splash.fxml",false, StageStyle.UNDECORATED,"/images/pos_icon.png");
        }
      );
    }


    public void onScreenKeys(){
        if (!barcodeSearch.getText().isEmpty()){
            backSpaceBtn.setOnAction(e -> barcodeSearch.setText(barcodeSearch.getText().substring(0, barcodeSearch.getText().length() - 1)));
            clearBtn.setOnAction(e -> barcodeSearch.clear());
        }
        backSpaceBtn.setOnAction(e -> barcodeSearch.setText(barcodeSearch.getText().substring(0, barcodeSearch.getText().length() - 1)));
        zero.setOnAction(e -> barcodeSearch.appendText("0"));
        one.setOnAction(e -> barcodeSearch.appendText("1"));
        two.setOnAction(e -> barcodeSearch.appendText("2"));
        three.setOnAction(e -> barcodeSearch.appendText("3"));
        four.setOnAction(e -> barcodeSearch.appendText("4"));
        five.setOnAction(e -> barcodeSearch.appendText("5"));
        six.setOnAction(e -> barcodeSearch.appendText("6"));
        seven.setOnAction(e -> barcodeSearch.appendText("7"));
        eight.setOnAction(e -> barcodeSearch.appendText("8"));
        nine.setOnAction(e -> barcodeSearch.appendText("9"));
        clearBtn.setOnAction(e -> {
            barcodeSearch.clear();
          }
        );
        deleteTicket.setOnAction(e->{
               if (!(productsTable.getItems().isEmpty())){
                   productsTable.getItems().clear();

                   //add saved json functionality
               }
        });
        findProductByCode();
    }

    public void add_minus_Count(){
           plusBtn.setOnAction(e->{
        if (!(productsTable.getSelectionModel().getSelectedItem()==null)){
            productsTable.getSelectionModel().getSelectedItem().setCount(productsTable.getSelectionModel().getSelectedItem().getCount()+1);
            syncTotal();
        }else {
            new SettingController().notification("Please Select a product","warning.png",2);
        }
           });
           minusBtn.setOnAction(e->{
               if (!(productsTable.getSelectionModel().getSelectedItem()==null)){
               if (productsTable.getSelectionModel().getSelectedItem().getCount()<=0){
                   productsTable.getSelectionModel().getSelectedItem().setCount(0);
                                  syncTotal();
               }else {
                   productsTable.getSelectionModel().getSelectedItem().setCount(
                           productsTable.getSelectionModel().getSelectedItem().getCount()-1
                   );
                   syncTotal();
               }
           }else {
                   new SettingController().notification("Please Select a product","warning.png",2);
               }
           });
    }

    public void syncTotal(){
        productsTable.getSelectionModel().getSelectedItem().setTotal(
                productsTable.getSelectionModel().getSelectedItem().getCount()
                        *
                        productsTable.getSelectionModel().getSelectedItem().getPrice()
        );
    }

    public void loadData(){
        //add your data to the table here.
        nameCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        countCol.setCellValueFactory(cell -> cell.getValue().countProperty());
        priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty());
        totalCol.setCellValueFactory(cell -> cell.getValue().totalProperty());
        editCol.setCellValueFactory(new PropertyValueFactory<>("Edit"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));
        add_minus_Count();
        delete_Edit_Product();
    }


    public void delete_Edit_Product(){
        ObservableList<Product> products = productsTable.getItems();
        for (Product product : products){
            Button deleteBtn= product.getDelete();
            Button editBtn= product.getEdit();
            editBtn.setOnAction(e-> new ScreenLoader().load("/com/jpos/pos/ProductEditUI.fxml",false, StageStyle.TRANSPARENT,"/images/product.png"));
            deleteBtn.setOnAction(e->{
                products.remove(product);
                productsTable.setItems(products);
                productsTable.refresh();
            });
        }
    }

    public void findProductByCode(){
        findBtn.setOnAction(e->{
            String code=barcodeSearch.getText();
            if (!code.isEmpty()){
                productsTable.getItems().addAll(connector.loadProducts("select * from biz_hub_product_master where barcode ="+code+";"));
                loadData();
            }
        });
    }


    public void checkOutTicket(){
        Stage stage=new Stage();
        Confirmation confirmation=new Confirmation();
        confirmation.setMessage("Are you sure to Check Out, Print Ticket and Clear Data?");
        confirmation.setImageIcon("pos_icon.png");
        confirmation.getYesButton().setOnAction(print->{
            finalCheckout();
//            Stage stage1=(Stage) confirmation.getYesButton().getScene().getWindow();
            stage.close();
        });
        confirmation.start(stage);
    }
    /*this method sets the ticket details and eventually prints it to printer
     after "yes" confirmation
     */
    public void finalCheckout(){
        ObservableList<Product> products1=productsTable.getItems();
        if (!(products1.isEmpty())){
            for (Product product:products1){
                Ticket ticket=new Ticket();
                ticket.setTicketName(product.getProductName());
                ticket.setProductCount(product.getCount());
                ticket.setProductPrice(product.getPrice());
                ticket.setProductTotal(product.getTotal());
                ticket.appendDetails();
            }
            new Ticket().printTicket();
        }else {
            new SettingController().notification("Ticket Empty","puzzled.png",2);
        }
    }

}