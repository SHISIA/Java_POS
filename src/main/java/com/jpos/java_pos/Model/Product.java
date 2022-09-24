package com.jpos.java_pos.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Product {
    private SimpleIntegerProperty count;
    private SimpleStringProperty productName;
    private SimpleDoubleProperty total;
    private SimpleDoubleProperty price;
    private Button edit;

    public Button getEdit() {
        return edit;
    }

    public void setEdit(Button edit) {
        this.edit = edit;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    private Button delete;

    public Product(String productName, int count, Double price, Double total, Button edit, Button delete) {
        this.count = new SimpleIntegerProperty(count);
        this.productName = new SimpleStringProperty(productName);
        this.price = new SimpleDoubleProperty(price);
        this.total=new SimpleDoubleProperty(total);
        this.edit=edit;
        this.delete=delete;
    }
    public int getCount() {
        return count.get();
    }

    public SimpleIntegerProperty countProperty() {
        return count;
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void deleteProduct(){
       new DbConnector().updateStatements("delete from biz_hub_product_master where product_name='"+getProductName()+"';");
        System.out.println("Deleted Successfully");
    }
}
