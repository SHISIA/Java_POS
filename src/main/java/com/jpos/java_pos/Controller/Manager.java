package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jpos.java_pos.Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Manager implements Initializable {

    public JFXListView permissionList;

    public DbConnector connector=new DbConnector();
    public AnchorPane manager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadManager();
    }

    VBox tileTemplate(int number,String permissionName){
        VBox vBox=new VBox();
        HBox hBox=new HBox();
        VBox vBox1=new VBox();
        HBox hBox1=new HBox();
        HBox hBox2=new HBox();

        //avatar
        ImageView avatar=new ImageView(new Image(getClass().getResource("/images/permission.png").toString()));
        hBox.getChildren().add(avatar);

        Label nameLabel=new Label("No:");
        Label name=new Label(String.valueOf(number));
        Label permissionLabel=new Label("Permission:");
        Label permission=new Label(permissionName);

        hBox1.getChildren().addAll(nameLabel,name);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(10);
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(permissionLabel,permission);
        vBox1.getChildren().addAll(hBox1,hBox2);
        vBox1.setAlignment(Pos.CENTER);

        //styling
        name.setStyle("-fx-font-size:22;" +
                "-fx-text-fill:orange;" +
                "-fx-font-weight:bold;");
        nameLabel.setStyle("-fx-font-size:18;");
        permissionLabel.setStyle("-fx-font-size:18;");
        permission.setStyle("-fx-font-size:22;" +
                "-fx-text-fill:orange;" +
                "-fx-font-weight:bold;");
        vBox.setStyle("-fx-border-color:grey;");

        vBox1.setAlignment(Pos.CENTER);

        vBox1.setPadding(new Insets(5,5,5,5));
        vBox.setPadding(new Insets(5,5,5,5));
        vBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(2,2,2,2));
        vBox1.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(330,220);
        vBox.getChildren().addAll(hBox,vBox1);

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
                return true;
            }
          }
        }
        return false;
    }

    public void loadManager(){
        connector.setCredentials();
        ObservableList<User> users=connector.loadUsers("select * from biz_hub_users;");
       if (KeyPad.passCodeStatus){
           for (User user:users){
               if (user.getName().matches(AFK.activeUser.getName())){
                   try {
                       String sql="select permission_name from biz_hub_permissions where permissions_group='POS Manager Tab' and permission_uid in\n" +
                               "(select permission_uid from biz_hub_permissions_users where user_uid in(select user_uid from biz_hub_users where user_uid='"+user.getUid()+"'))";
                       Connection connection= connector.getConnection();
                       ResultSet rs=connection.createStatement().executeQuery(sql);
                       int num=1;
                       while (rs.next()){
                           String permission=rs.getString(1);
                           permissionList.getItems().add(tileTemplate(num,permission));
                           num++;
                       }
                       connection.close();
                   } catch (SQLException e) {
                       new SettingController().notification("Error!!","puzzled.png",3);
                   }
               }

           }
       }
    }

    public void loadUsers(){
        new ScreenLoader().load("/com/jpos/pos/ManageUsers.fxml",false, StageStyle.TRANSPARENT,"/images/pos.png");
    }
}
