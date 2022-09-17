package com.jpos.java_pos.Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class SplashModel {
    public ImageView splashLogo;

    public JFXComboBox dropDown;

    public Hyperlink loadDBlink;

    public JFXButton closeBtn;
    public JFXButton connect;

    static File file;
    DbConnector connector=new DbConnector();

    static HashMap<String,String> paths=new HashMap<>();

    public SplashModel(JFXButton connect,JFXButton closeBtn,Hyperlink loadDBlink,JFXComboBox comboBox,ImageView splashLogo){
        this.connect=connect;
        this.splashLogo=splashLogo;
        this.loadDBlink=loadDBlink;
        this.dropDown=comboBox;
        this.closeBtn=closeBtn;
        refreshDropDown();
        loadLogin();
        loadDatabase();
    }

    public void loadLogin(){
        Connection connection= connector.getConnection();
        closeBtn.setOnAction(e-> closeWindow());
        connect.setOnAction(f->{
            try {
                ResultSet res=connection.createStatement().executeQuery("select * from biz_hub_product_master");
                while (res.next()){
                    System.out.println("Data "+res.getString(2));
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            new ScreenLoader().load("/com/jpos/pos/LoginScreen.fxml",false, StageStyle.UNDECORATED,"/images/pos_icon.png");
            closeWindow();
        });
    }

    protected void closeWindow(){
        Stage stage= (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    public void refreshDropDown(){
        Connection connection= connector.getConnection();
        try {
            ResultSet resultSet=connection.createStatement().executeQuery("select * from DBs");
            while (resultSet.next()){
                paths.put(resultSet.getString(1),resultSet.getString(2));
                dropDown.getItems().addAll(resultSet.getString(1));
            }
            connection.close();
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void loadDatabase(){
        loadDBlink.setOnAction(e->{
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(loadDBlink.getScene().getWindow());
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("DB Files", "*.db")
                    ,new FileChooser.ExtensionFilter("SQL Files", "*.sql")
            );
            try {
                PreparedStatement statement = connector.getConnection().prepareStatement("insert into DBs value ('"+file.getName().replace(".sql","")+"','"+ file.getPath() +"');");
                statement.execute();
                statement.close();
                dropDown.getItems().clear();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            refreshDropDown();
        });
    }
}
