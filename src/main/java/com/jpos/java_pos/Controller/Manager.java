package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXListView;
import com.jpos.java_pos.Model.DBSetup;
import com.jpos.java_pos.Model.DbConnector;
import com.jpos.java_pos.Model.Product;
import com.jpos.java_pos.Model.User;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Manager implements Initializable {

    public JFXListView permissionList;

    public DbConnector connector=new DbConnector();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUsers();
    }

    VBox tileTemplate(String permission){
        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        Label label=new Label(permission);
        label.setMinWidth(100);
        label.setMinHeight(150);
        label.setStyle("-fx-font-size:20;");
        vBox.setStyle("-fx-border-color: white; " +
                "-fx-background-color: orange;");
        vBox.setPadding(new Insets(15,15,15,15));
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(4.0,4.0,4.0,4.0));

        hbox.setStyle("-fx-background-color:white;");
        hbox.prefHeight(195);
        hbox.prefWidth(195);
        hbox.getChildren().addAll(label);
        vBox.getChildren().addAll(hbox);
        hbox.setMinWidth(200);
        hbox.minHeight(200);
        return vBox;
    }

    public static boolean checkManagerPrivilege(){
        if (KeyPad.passCodeStatus){
        DbConnector connector1=new DbConnector();
        connector1.setCredentials();
        ObservableList<User> users=connector1.loadUsers("select * from biz_hub_users where user_uid in (select user_uid from biz_hub_permissions_users \n" +
                "where permission_uid in (select permission_uid from biz_hub_permissions where permissions_group='POS Manager Tab'));");
        for (User user:users){
            if (user.getName().matches(AFK.activeUser.getName())){
                System.out.println("User has manager privilege");
                return true;
            }
          }
        }
        return false;
    }

    public void loadUsers(){
        connector.setCredentials();
        ObservableList<User> users=connector.loadUsers("select * from biz_hub_users;");
       if (KeyPad.passCodeStatus){
           for (User user:users){
               if (user.getName().matches(AFK.activeUser.getName())){
                   try {
                       String sql="select permission_name from biz_hub_permissions where permissions_group='POS Manager Tab' and permission_uid in\n" +
                               "(select permission_uid from biz_hub_permissions_users where user_uid in(select user_uid from biz_hub_users where user_uid='"+user.getUid()+"'))";
                       Connection connection= connector.getConnection();
                       ResultSet rs=connection.createStatement().executeQuery(sql);;
                       while (rs.next()){
                           String permission=rs.getString(1);
                           permissionList.getItems().add(tileTemplate(permission));
                       }
                       connection.close();
                   } catch (SQLException e) {
                       new SettingController().notification("Error!!","puzzled.png",3);
                   }
               }

           }
       }
    }
}
