package com.jpos.java_pos.Model;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Notification extends Application {
    Label messageLabel=new Label();

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    int seconds;

    ImageView icon=new ImageView();

   public void setImageIcon(String image){
       icon.setImage(new Image(getClass().getResource("/images/"+image).toString()));
   }

    public void setMessage(String name){
        messageLabel.setText(name);
    }

    @Override
    public void start(Stage stage) {
        icon.setFitWidth(80);
        icon.setFitHeight(80);
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setStyle("-fx-font-size:20;");
        HBox hBox=new HBox();
        hBox.setStyle("-fx-border-color:black;");
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
        stage.initStyle(StageStyle.TRANSPARENT);
        hBox.getChildren().addAll(icon,messageLabel);
        Scene scene=new Scene(hBox,400,100);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }

    public static void main(String[] args){
        launch(args);
    }

}
