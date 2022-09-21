package com.jpos.java_pos.Model;

import com.jfoenix.controls.JFXButton;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SalesPOSModel {


    public JFXButton deleteTicket;
     
    public JFXButton stackBtn;
     
    public JFXButton priceCheckBtn;
     
    public JFXButton logOutBtn;
     
    public TableView<Table> productsTable;
     
    public TableColumn<Table,String> nameCol;
     
    public TableColumn<Table,Number> countCol;
     
    public TableColumn<Table,Number> priceCol;
     
    public TableColumn<Table,Number> totalCol;
     
    public TableColumn<Table, Button> deleteCol;
     
    public TableColumn<Table,Button> editCol;
     
    public TextField barcodeSearch;
     
    public Button findBtn;

    DbConnector connector=new DbConnector();
    ObservableList<Table> products;

    public SalesPOSModel(TableView<Table> productsTable,TableColumn<Table,String> nameCol,TableColumn<Table,Number> countCol, TableColumn<Table,Number> priceCol
            , TableColumn<Table,Number> totalCol,TableColumn<Table,Button> editCol,TableColumn<Table, Button> deleteCol,TextField barcodeSearch) {
        this.productsTable=productsTable;
        this.nameCol=nameCol;
        this.countCol=countCol;
        this.priceCol=priceCol;
        this.totalCol=totalCol;
        this.editCol=editCol;
        this.deleteCol=deleteCol;
        this.barcodeSearch=barcodeSearch;
        loadData();
    }

    public void loadData(){
        nameCol.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        countCol.setCellValueFactory(cell -> cell.getValue().countProperty());
        priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty());
        totalCol.setCellValueFactory(cell -> cell.getValue().totalProperty());
        editCol.setCellValueFactory(new PropertyValueFactory<>("Edit"));
        deleteCol.setCellValueFactory(new PropertyValueFactory<>("Delete"));

        //add your data to the table here.
        products=connector.loadProducts("select * from biz_hub_product_master");

        productsTable.setItems(products);

        // add your data here from any source
        products.addListener((ListChangeListener<? super Table>) change -> {
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
        products.add(new Table("e2q3r",2000,22322.,232334.,new Button("edit"),new Button("delete")));
    }


    public void deleteProduct(){
        System.out.println("erfjk4nrgvd");
        List<Table> tables= products;
        for (Table table:tables){
            Button deleteBtn=table.getDelete();
            deleteBtn.setOnAction(e-> System.out.println("Position "));
        }
    }


    public void findProductByCode(){
        findBtn.setOnAction(e->{
            String code=barcodeSearch.getText();
            if (!code.isEmpty()){
                products=connector.loadProducts("select * from biz_hub_product_master where code ="+Integer.parseInt(code)+";");
                productsTable.setItems(products);
            }
            System.out.println("Empty");
        });
    }

}