package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jpos.java_pos.Model.DbConnector;
import com.jpos.java_pos.Model.ScreenLoader;
import com.jpos.java_pos.Model.User;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageUsers implements Initializable {
    public JFXListView userList;

    public static User userClicked;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUsers();
    }

    public void loadUsers(){
        String sql="Select * from biz_hub_users;";
        ObservableList<User> users= new DbConnector().loadUsers(sql);
        for (User user:users){
            VBox userGUIItem=userGUIItem(user.getName(), user.getRole(),user);
            userList.getItems().add(userGUIItem);
        }
    }
    public VBox userGUIItem(String userName, String userRole,User user){
        //containers
        VBox vBox=new VBox();
        HBox hBox=new HBox();
        VBox vBox1=new VBox();
        HBox hBox1=new HBox();
        HBox hBox2=new HBox();

        //avatar
        ImageView avatar=new ImageView(new Image(getClass().getResource("/images/avatar.png").toString()));
        hBox.getChildren().add(avatar);

        Label nameLabel=new Label("Name:");
        Label name=new Label(userName);
        Label roleLabel=new Label("Role:");
        Label role=new Label(userRole);

        HBox hBox3=new HBox();

        JFXButton button=new JFXButton("Edit");
        button.setOnAction(e->{
            userClicked=user;
            new ScreenLoader().load("/com/jpos/pos/CreateEdit.fxml",false, StageStyle.TRANSPARENT,"/images/pos.png");
        });
        JFXButton create=new JFXButton("Create");
        create.setOnAction(e->{
            userClicked=user;
            new ScreenLoader().load("/com/jpos/pos/CreateEdit.fxml",false, StageStyle.TRANSPARENT,"/images/pos.png");

        });
        hBox3.getChildren().addAll(button,create);
        hBox3.setAlignment(Pos.CENTER);
        hBox3.setSpacing(15);

        hBox1.getChildren().addAll(nameLabel,name);
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setSpacing(10);
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(roleLabel,role);
        vBox1.getChildren().addAll(hBox1,hBox2);
        vBox1.setAlignment(Pos.CENTER);

        //styling
        button.setStyle("-fx-border-color:black;" +
                "-fx-background-color:white;" +
                "-fx-font-size:18;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;");
        create.setStyle("-fx-border-color:black;" +
                "-fx-background-color:white;" +
                "-fx-font-size:18;" +
                "-fx-border-radius:8;" +
                "-fx-background-radius:8;");
        name.setStyle("-fx-font-size:18;");
        nameLabel.setStyle("-fx-font-size:18;");
        roleLabel.setStyle("-fx-font-size:18;");
        role.setStyle("-fx-font-size:18;");
        vBox.setStyle("-fx-border-color:grey;");

        vBox1.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(hBox,vBox1,hBox3);
        vBox1.setPadding(new Insets(5,5,5,5));
        vBox.setPadding(new Insets(5,5,5,5));
        vBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(2,2,2,2));
        vBox1.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPrefSize(250,250);

        return vBox;
    }

    public void closeUI(){
        Stage stage=(Stage) userList.getScene().getWindow();
        stage.close();
    }

}
