package com.jpos.java_pos.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbConnector {
    String userName="";

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
                    "jdbc:mysql://localhost:3306/JavaPOS",getUserName(),getPassword());
            System.out.println("wefodicuh");
            System.out.println("Connected to database");

        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }


}
