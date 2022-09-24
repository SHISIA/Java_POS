package com.jpos.java_pos.Controller;

import com.jpos.java_pos.Model.MessageModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUp implements Initializable {
    @FXML
    public ImageView imgErrorIcon;
    @FXML
    public Label messageLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new MessageModel(imgErrorIcon,messageLabel);
    }
}
