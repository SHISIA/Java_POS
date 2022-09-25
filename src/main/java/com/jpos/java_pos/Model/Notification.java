package com.jpos.java_pos.Model;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;

public class Notification extends Application {
    Label messageLabel=new Label();

    ImageView icon=new ImageView();

   public void setImageIcon(String image){
       icon.setImage(new Image(getClass().getResource("/images/"+image).toString()));
   }

    public void setMessage(String name){
        messageLabel.setText(name);
    }

    @Override
    public void start(Stage stage) throws Exception {
        icon.setFitWidth(50);
        icon.setFitHeight(50);
        HBox hBox=new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        stage.initStyle(StageStyle.TRANSPARENT);
        hBox.getChildren().addAll(icon,messageLabel);
        Scene scene=new Scene(hBox,200,50);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
