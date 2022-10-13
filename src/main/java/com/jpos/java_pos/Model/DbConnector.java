package com.jpos.java_pos.Model;

import com.jpos.java_pos.Controller.SettingController;
import com.jpos.java_pos.json.JSONReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection= DriverManager.getConnection(
                    "jdbc:mysql://"+getHostName()+":3306/"+getSchema(),getUserName(),getPassword());

        }catch (Exception e){
            new SettingController().notification("DB Connection Error","error.png",7);
        }
        return connection;
    }

    public void setCredentials(){
//        JSONObject jsonObject=new JSONReader().read("data.json","server");
        setSchema(SplashModel.selectedDB);
//        setHostName((String) jsonObject.get("hostName"));
        setHostName(SplashModel.selectedHost);
        JSONObject jsonObject1=new JSONReader().read("data_.json","server");
        setPassword((String) jsonObject1.get("password"));
        setUserName((String) jsonObject1.get("username"));
    }

    public String streamReader(String filename){
        String text = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filename);
            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            while ((line = reader.readLine()) != null ) {
                text=line;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return text;
    }


//  universally insert and update statements
    public void updateStatements(String statement){
        setCredentials();
        try {
            PreparedStatement statement1=getConnection().prepareStatement(statement);
            statement1.execute();
            connection.close();
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
                String name=rs.getString(1);
                int quantity=1;
                double price= rs.getInt(2);
                double total=price*quantity;
                products.add(new Product(name,quantity, price,total,new Button("Edit"),new Button("Delete")));
            }
            connection.close();
        } catch (SQLException e) {
            new SettingController().notification("Not Found","puzzled.png",3);
//            e.printStackTrace();
        }
        return products;
    }

    public ObservableList<User> loadUsers(String sql){
        setCredentials();
        ObservableList<User> users = FXCollections.observableArrayList();
        try {
            ResultSet rs=getConnection().createStatement().executeQuery(sql);
            while (rs.next()){
                String role=rs.getString(3);
                String name=rs.getString(2);
                String code=rs.getString(4);
                String passcode=rs.getString(5);
                String uid=rs.getString(1);
                users.add(new User(role,name,code,passcode,uid));
            }
            connection.close();
        } catch (SQLException e) {
            new SettingController().notification("Logout and (Check) Select Schema","puzzled.png",3);
        }
        return users;
    }


}
