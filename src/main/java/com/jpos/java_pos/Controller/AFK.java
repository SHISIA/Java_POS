package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXListView;
import com.jpos.java_pos.Model.Confirmation;
import com.jpos.java_pos.Model.DbConnector;
import com.jpos.java_pos.Model.ScreenLoader;
import com.jpos.java_pos.Model.User;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class AFK implements Initializable {
    public JFXListView<VBox> usersList;
    public static User activeUser;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUsers();
        usersList.setOnMouseClicked(e->keyPad());
    }

    public void loadUsers(){
        String sql="select * from biz_hub_users where user_uid in (select user_uid from biz_hub_permissions_users \n" +
                "where permission_uid in (select permission_uid from biz_hub_permissions where permission_module='POS661616') )";
        //String sql="select * from biz_hub_users;";
        ObservableList<User> users=new DbConnector().loadUsers(sql);
        for (User user:users){
            usersList.getItems().add(createHBoxLayout(user.getName(), user.getRole()));
        }
    }

    public VBox createHBoxLayout(String name, String role) {
        VBox vBox=new VBox();
        vBox.setId(name);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-border-color: black; " +
                "-fx-background-color: grey;");
        vBox.setPadding(new Insets(15,15,15,15));
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(4.0,4.0,4.0,4.0));
        VBox vBox1=new VBox();
        ImageView imageView=new ImageView(new Image(getClass().getResource("/images/user.png").toString()));
        imageView.setFitHeight(136.0);
        imageView.setFitWidth(133.0);
        vBox.getChildren().add(imageView);
        hbox.setSpacing(140);
        hbox.setPadding(new Insets(20,20,20,20));

        ImageView imageView1=new ImageView(new Image(getClass().getResource("/images/SmazyLogo.png").toString()));
        imageView1.setFitHeight(60);
        imageView1.setFitWidth(100);

        Label nameLabel=new Label(name);
        nameLabel.setPrefHeight(32);
        nameLabel.prefWidth(250);
        nameLabel.setStyle("-fx-text-fill:#208606;");

        Label roleLabel=new Label(role);
        roleLabel.setStyle("-fx-text-fill:#2f720f;");
        roleLabel.setPrefHeight(33);
        roleLabel.prefWidth(250);

        vBox1.setSpacing(20);
        vBox1.setStyle("-fx-background-color:white;");
        hbox.setStyle("-fx-background-color:white;");

        vBox1.setAlignment(Pos.CENTER);
        vBox1.setPadding(new Insets(10,10,10,10));
        vBox1.getChildren().addAll(nameLabel,roleLabel);
        hbox.getChildren().addAll(imageView,vBox1,imageView1);
        vBox.getChildren().addAll(hbox);
        return vBox;
    }

    public void keyPad(){
        if (usersList.getSelectionModel().getSelectedItem()!=null){
            String name=usersList.getSelectionModel().getSelectedItem().getId();
            ObservableList<User> users=new DbConnector().loadUsers("select * from biz_hub_users where user_name='"+name+"';");
            for (User user:users){
                activeUser=user;
            }
            new ScreenLoader().load("/com/jpos/pos/KeyPad.fxml",false, StageStyle.TRANSPARENT,"/images/pos_icon.png");

        }
    }

    public void close(){
        Stage stage=new Stage();
        Confirmation confirmation=new Confirmation();
        confirmation.setImageIcon("quiz.png");
        confirmation.setMessage("Are you Shutting the system?");
        Button button=confirmation.getYesButton();
        button.setOnAction(e->{
            System.exit(0);
        });
        confirmation.start(stage);

    }
}
