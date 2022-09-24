package com.jpos.java_pos.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.*;

public class DbConnector {
    String userName="";

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    String schema="";

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    String hostName="";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password="";
    public Connection connection;

    public Connection getConnection() {
        try {
            setPassword("pw517ue");
            setUserName("root");
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection= DriverManager.getConnection(
                    "jdbc:mysql://"+getHostName()+":3306/"+getSchema(),getUserName(),getPassword());
            System.out.println("Connected to database");

        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }


//    universally insert and update statements
    public void updateStatements(String statement){
        try {
            PreparedStatement statement1=getConnection().prepareStatement(statement);
            statement1.execute();
            connection.close();
            System.out.println("Execution Success");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Product> loadProducts(String sql){
        ObservableList<Product> products = FXCollections.observableArrayList(
        );
        try {
            ResultSet rs=getConnection().createStatement().executeQuery(sql);;
            while (rs.next()){
                String name=rs.getString(2);
                int quantity=rs.getInt(3);
                double price= rs.getInt(4);
                double total=price*quantity;
                products.add(new Product(name,quantity, price,total,new Button("Edit"),new Button("Delete")));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }


}
