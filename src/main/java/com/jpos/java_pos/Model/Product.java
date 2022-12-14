package com.jpos.java_pos.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Product {
    private SimpleIntegerProperty count;
    private SimpleStringProperty productName;

    public void setTotal(double total) {
        this.total.set(total);
    }

    private SimpleDoubleProperty total;
    private SimpleDoubleProperty price;
    private Button edit;

    public Button getEdit() {
        return edit;
    }

    public Button getDelete() {
        return delete;
    }

    private Button delete;

    public Product(String productName, int count, Double price, Double total, Button edit, Button delete) {
        this.count = new SimpleIntegerProperty(count);
        this.productName = new SimpleStringProperty(productName);
        this.price = new SimpleDoubleProperty(price);
        this.total=new SimpleDoubleProperty(total);
        this.edit=edit;
        this.delete=delete;
        delete.setStyle("-fx-background-color:black;"+
                "-fx-text-fill:red;");
        edit.setStyle("-fx-background-color:black;"+
                "-fx-text-fill:orange;");
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

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty totalProperty() {
        return total;
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

}
