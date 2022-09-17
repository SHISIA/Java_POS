package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jpos.java_pos.Model.SplashModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class SplashController implements Initializable{


    @FXML
    public ImageView splashLogo;
    @FXML
    public JFXComboBox dropDown;
    @FXML
    public JFXButton connectDBBtn;
    @FXML
    public Hyperlink loadDBlink;
    @FXML
    public JFXButton closeBtn;


    public SplashController(){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new SplashModel(connectDBBtn,closeBtn,loadDBlink,dropDown,splashLogo);
    }
}