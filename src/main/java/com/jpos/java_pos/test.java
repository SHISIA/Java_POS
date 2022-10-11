package com.jpos.java_pos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application{
    public VBox createHBoxLayout(String name,String role) {
        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setStyle("-fx-border-color: black; " +
                "-fx-background-color: grey;");
        vBox.setPadding(new Insets(15,15,15,15));
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(4.0,4.0,4.0,4.0));
        VBox vBox1=new VBox();
        ImageView imageView=new ImageView();
        imageView.setFitHeight(136.0);
        imageView.setFitWidth(133.0);
        vBox.getChildren().add(imageView);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5,5,5,5));

        ImageView imageView1=new ImageView();
        imageView1.setFitHeight(136.0);
        imageView1.setFitWidth(133.0);

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

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(createHBoxLayout("",""), 747, 155);
        stage.setTitle("Layout Demo");
        stage.setScene(scene);
        stage.show();
    }

}
