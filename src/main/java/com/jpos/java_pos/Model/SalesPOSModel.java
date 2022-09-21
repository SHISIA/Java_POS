package com.jpos.java_pos.Model;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class SalesPOSModel {


    public JFXButton deleteTicket;
     
    public JFXButton stackBtn;
     
    public JFXButton priceCheckBtn;
     
    public JFXButton logOutBtn;
     
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
            , TableColumn<Product,Number> totalCol, TableColumn<Product,Button> editCol, TableColumn<Product, Button> deleteCol, TextField barcodeSearch, Button findBtn) {
        this.productsTable=productsTable;
        this.nameCol=nameCol;
        this.countCol=countCol;
        this.priceCol=priceCol;
        this.findBtn=findBtn;
        this.totalCol=totalCol;
        this.editCol=editCol;
        this.deleteCol=deleteCol;
        this.barcodeSearch=barcodeSearch;
        loadData();
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
        System.out.println("erfjk4nrgvd");
        List<Product> products = this.products;
        for (Product product : products){
            Button deleteBtn= product.getDelete();
            deleteBtn.setOnAction(e-> System.out.println("Position "));
        }
    }


    public void findProductByCode(){
        findBtn.setOnAction(e->{
            String code=barcodeSearch.getText();
            if (!code.isEmpty()){
                products=connector.loadProducts("select * from biz_hub_product_master where barcode ="+code+";");
                productsTable.getItems().clear();
                productsTable.setItems(products);
            }
            System.out.println("Empty");
        });
    }

}