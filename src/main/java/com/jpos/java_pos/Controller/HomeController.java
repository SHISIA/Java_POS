package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXButton;
import com.jpos.java_pos.Model.ScreenLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public BorderPane container;
    @FXML
    public JFXButton btnAFK;
    @FXML
    public JFXButton btnPOS;
    @FXML
    public JFXButton btnManager;
    @FXML
    public JFXButton btnSettings;
    @FXML
    public JFXButton btnLogout;
    public Circle avatar;

    public void setContainer(String url){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
        container.getChildren().remove(container.getCenter()); //remove existing fxml from center.
        try {
            container.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }    }
    @FXML
    void setBtnPOS(){
        if (KeyPad.passCodeStatus) {
            setContainer("/com/jpos/pos/SalesPOS.fxml");
        }else {
            new SettingController().notification("Kindly Authenticate First","puzzled.png",2);

        }
    }
    @FXML
    void setBtnAFK(){setContainer("/com/jpos/pos/AFK.fxml");}
    @FXML
    void setBtnManager(){/*setContainer("/com/jpos/pos/SalesPOS.fxml");*/}
    @FXML
    void setBtnSettings(){setContainer("/com/jpos/pos/Settings.fxml");}
    @FXML
    void setBtnLogout(){
        Stage stage=(Stage) btnLogout.getScene().getWindow();
        stage.close();
        new ScreenLoader().load("/com/jpos/pos/Splash.fxml",false, StageStyle.UNDECORATED,"/images/pos_icon.png");
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL url1 = getClass().getResource("/images/pos.png");
        avatar.setFill(new ImagePattern(new Image(String.valueOf(url1))));
        setBtnAFK();
    }
}
