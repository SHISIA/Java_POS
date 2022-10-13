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
    public VBox createHBoxLayout() {
        VBox vBox=new VBox();
        vBox.setAlignment(Pos.CENTER);
        Label label=new Label("Permission");
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

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(createHBoxLayout(), 200, 200);
        stage.setTitle("Layout Demo");
        stage.setScene(scene);
        stage.show();
    }

}
