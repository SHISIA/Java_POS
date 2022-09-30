package com.jpos.java_pos.Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jpos.java_pos.Controller.SettingController;
import com.jpos.java_pos.json.JSON;
import com.jpos.java_pos.json.JSONReader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Math.toIntExact;

public class DBSetup implements Initializable {
    @FXML
    public ImageView logoImg;
    @FXML
    public JFXTextField usernameField;
    @FXML
    public JFXButton saveBtn;
    @FXML
    public JFXTextField nameField;
    @FXML
    public JFXPasswordField passField;
    @FXML
    public JFXCheckBox passShowCheck;
    @FXML
    public JFXButton closeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoImg.setImage(new Image(getClass().getResource("/images/pos.png").toString()));
        setUpDb();
        setCloseBtn();
    }

    public void setUpDb(){
        saveBtn.setOnAction(e->{
            if (!(nameField.getText().isEmpty()&&usernameField.getText().isEmpty()&&passField.getText().isEmpty())){
                JSON json=new JSON();
                json.writeJSON("data_.json","server","password",passField.getText()
                ,"username",usernameField.getText());
                json.writeJSON("data.json","server","schema",nameField.getText()
                ,"hostName","localhost");
                JSONObject jsonObject=new JSONReader().read("dbs.json","database");
                Long aLong=(Long) jsonObject.get("name");
                int i=toIntExact(aLong);
                json.writeJSON("dbs.json","database","name",i+1,"schema",nameField.getText());
                new SettingController().notification("Credentials Successfully Saved","success.png",2);
                passField.clear();usernameField.clear();nameField.clear();
            }else {
                new SettingController().notification("One or more field(s) Empty","warning.png",2);
            }
        });
    }

    //closes the DBSetup stage
    public void setCloseBtn(){
      closeBtn.setOnAction(close->{
          Stage stage=(Stage) closeBtn.getScene().getWindow();
          stage.close();
          new ScreenLoader().load("/com/jpos/pos/Splash.fxml",false, StageStyle.TRANSPARENT,"/images/pos_icon.png");
      });
    }
}
