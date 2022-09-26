package com.jpos.java_pos.Model;

import com.jpos.java_pos.json.JSONReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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
            JSONObject jsonObject=new JSONReader().read("data.json","server");
            setSchema((String) jsonObject.get("schema"));
            setHostName((String) jsonObject.get("hostName"));
            setPassword((String) jsonObject.get("password"));
            setUserName((String) jsonObject.get("username"));
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection= DriverManager.getConnection(
                    "jdbc:mysql://"+getHostName()+":3306/"+getSchema(),getUserName(),getPassword());
            System.out.println("Connected to database");

        }catch (Exception e){
            e.printStackTrace();
            Notification notification=new Notification();
            notification.setMessage("Error!! Check Details");
            notification.setImageIcon("error.png");
        }
        return connection;
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
    //saves lines to .txt files
    public void writeText(String textName,String content){
        try {
            URL url = getClass().getResource(textName);
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(url.toURI())));
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
