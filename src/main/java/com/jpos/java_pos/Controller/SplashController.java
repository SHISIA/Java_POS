package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SplashController {


    @FXML
    public ImageView splashLogo;
    @FXML
    public JFXComboBox dropDown;
    @FXML
    public JFXButton connectDBBtn;
    @FXML
    public Hyperlink loadDBlink;

    @FXML
    protected void onHelloButtonClick() {

    }
}