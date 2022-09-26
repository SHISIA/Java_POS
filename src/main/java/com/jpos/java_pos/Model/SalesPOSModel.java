package com.jpos.java_pos.Model;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.barcode.BarCode;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.StageStyle;

import javax.print.PrintService;
import java.io.IOException;
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

    ObservableList<Product> products;

    public SalesPOSModel(TableView<Product> productsTable, TableColumn<Product,String> nameCol, TableColumn<Product,Number> countCol, TableColumn<Product,Number> priceCol
            , TableColumn<Product,Number> totalCol, TableColumn<Product,Button> editCol, TableColumn<Product, Button> deleteCol, TextField barcodeSearch, Button findBtn, JFXButton checkout,
                         Button plusBtn,Button minusBtn,TableView smartList,Button multiply,Button zero, Button one,Button two,
                         Button three,Button four,Button five,Button six,Button seven,Button eight,Button nine,Button doubleO,JFXButton clearBtn,JFXButton backSpaceBtn) {
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
        checkout.setOnAction(e->checkOutTicket());
        loadData();
        onScreenKeys();
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
            loadData();
          }
        );
        add_minusCount();
    }

    public void add_minusCount(){
       try {
           plusBtn.setOnAction(e->productsTable.getSelectionModel().getSelectedItem().setCount(
                   productsTable.getSelectionModel().getSelectedItem().getCount()+1
           ));
           minusBtn.setOnAction(e->{
               if (productsTable.getSelectionModel().getSelectedItem().getCount()<=0){
                   productsTable.getSelectionModel().getSelectedItem().setCount(0);
               }else {
                   productsTable.getSelectionModel().getSelectedItem().setCount(
                           productsTable.getSelectionModel().getSelectedItem().getCount()-1
                   );
               }
           });
       }catch (NullPointerException np){
           System.out.println("nullpointer. no product selected");
       }

    }

    public void loadData(){
        //add your data to the table here.
        products=connector.loadProducts("select * from biz_hub_product_master;");
        productsTable.setItems(products);

        nameCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        countCol.setCellValueFactory(cell -> cell.getValue().countProperty());
        priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty());
        totalCol.setCellValueFactory(cell -> cell.getValue().totalProperty());
        editCol.setCellValueFactory(new PropertyValueFactory<>("Edit"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));

        // add your data here from any source
        products.addListener((ListChangeListener<? super Product>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    System.out.println(change.getAddedSubList().get(0)
                            + " was added to the list!");
                } else if (change.wasRemoved()) {
                    System.out.println(change.getRemoved().get(0)
                            + " was removed from the list!");
                }
            }
        });
        deleteProduct();
        findProductByCode();
    }


    public void deleteProduct(){
        List<Product> products = this.products;
        for (Product product : products){
            Button deleteBtn= product.getDelete();
            Button editBtn= product.getEdit();
            editBtn.setOnAction(e-> new ScreenLoader().load("/com/jpos/pos/ProductEditUI.fxml",false, StageStyle.TRANSPARENT,"/images/product.png"));
            deleteBtn.setOnAction(e->{
                product.deleteProduct();
                this.products.remove(product);
                productsTable.setItems(this.products);
                productsTable.refresh();
            });
        }
    }


    public void findProductByCode(){
        findBtn.setOnAction(e->{
            String code=barcodeSearch.getText();
            if (!code.isEmpty()){
                products=connector.loadProducts("select * from biz_hub_product_master where barcode ="+code+";");
                productsTable.setItems(products);
            }
        });
    }


    public void checkOutTicket(){
        String printer="";
       try {
           for (Product product : this.products) {
               System.out.println(product.getProductName() + "  Price =" + product.getPrice() + " Count =" + product.getCount());
              printer=new DbConnector().streamReader("selected.txt");
           }
           PrintService printService = PrinterOutputStream.getPrintServiceByName(printer);
           PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
           EscPos escpos = new EscPos(printerOutputStream);
           escpos.writeLF("Hello world");
           escpos.feed(5).cut(EscPos.CutMode.FULL);
           BarCode barcode = new BarCode();
           escpos.write(barcode, "hello barcode");
           escpos.close();
           System.out.println("Printing Complete");
       }
       catch (IllegalArgumentException e){
           System.out.println("No Printer with such name");
       }
       catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

}