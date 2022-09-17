package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jpos.java_pos.Model.ScreenLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {

    @FXML
    public JFXButton loginBtn;
    @FXML
    public JFXButton closeBtn;
    @FXML
    public ImageView logo;

    @FXML
    public void logIn(){
        new ScreenLoader().load("/com/jpos/pos/Home.fxml",true, StageStyle.DECORATED,"/images/pos_icon.png");
        closeWindow();
    }

    public void closeWindow() {
        Stage stage= (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logo.setImage(new Image(getClass().getResource("/images/pos.png").toString()));

    }
}
