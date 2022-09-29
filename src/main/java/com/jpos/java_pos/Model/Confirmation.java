package com.jpos.java_pos.Model;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.lang.reflect.Method;

public class Confirmation extends Application {
    Label messageLabel=new Label();
    private Stage stage;
    ImageView icon=new ImageView();
    Button yesButton=new Button("Yes");

    Button noButton=new Button("No");

    public Button getYesButton() {
        return yesButton;
    }

    public void setImageIcon(String image){
       icon.setImage(new Image(getClass().getResource("/images/"+image).toString()));
   }
    public void setMessage(String name){
        messageLabel.setText(name);
    }
    public void closeStage() {
        noButton.setOnAction(e->stage.close());
    }
    @Override
    public void start(Stage stage) {
       //Parent Node(container)
        this.stage=stage;
        VBox mainContainer=new VBox();
        mainContainer.setSpacing(10);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setStyle("-fx-border-color:black;");

        //set up icon
        icon.setFitWidth(80);
        icon.setFitHeight(80);
        //set Up message
        messageLabel.setAlignment(Pos.CENTER);

        //sub-node(holds buttons)
        HBox btnContainer=new HBox();
        btnContainer.getChildren().addAll(yesButton,noButton);
        btnContainer.setAlignment(Pos.CENTER);
        btnContainer.setSpacing(200);
        //sub-node(holds message and icon)
        HBox hBox=new HBox();
        hBox.setSpacing(50);
        hBox.setAlignment(Pos.CENTER);
        /*hide icons to prevent maximization which distorts the UI structure
        lets the confirmBox retain its intended structure
         */
        stage.initStyle(StageStyle.TRANSPARENT);
        hBox.getChildren().addAll(icon,messageLabel);
        mainContainer.getChildren().addAll(hBox,btnContainer);
        Scene scene=new Scene(mainContainer,500,150);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.show();

        closeStage();

    }

    public static void main(String[] args){
        launch(args);
    }

}
