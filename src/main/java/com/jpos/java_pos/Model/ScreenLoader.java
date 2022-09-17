package com.jpos.java_pos.Model;

import com.jpos.java_pos.Splash;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ScreenLoader {

    public Stage load(String fxmlPath,boolean maximized,StageStyle stageStyle,String imIcon){
        Stage stage=new Stage();
        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
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

}
