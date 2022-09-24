package com.jpos.java_pos.Model;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ScreenLoader {

    public Stage load(String fxmlPath,boolean maximized,StageStyle stageStyle,String imIcon){
        Stage stage=new Stage();
        try {
            Parent root =FXMLLoader.load(Objects.requireNonNull(ScreenLoader.class.getResource(fxmlPath)));
            Scene scene = new Scene(root);
            stage.setMaximized(maximized);
            stage.initStyle(stageStyle);
            URL url = getClass().getResource(imIcon);
            stage.getIcons().add(new Image(String.valueOf(url)));
            stage.setTitle("POS");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stage;
    }

    public Stage loadPopUp(String fxmlPath,StageStyle stageStyle){
        Stage stage=new Stage();
        try {
            Parent root =FXMLLoader.load(Objects.requireNonNull(ScreenLoader.class.getResource(fxmlPath)));
            Scene scene = new Scene(root);
            stage.initStyle(stageStyle);
            stage.setScene(scene);
            stage.show();

            //shows for a few seconds and closes
            PauseTransition wait = new PauseTransition(Duration.seconds(3));
            wait.setOnFinished((e) -> stage.close());
            wait.play();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stage;
    }

}
