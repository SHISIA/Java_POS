package com.jpos.java_pos.Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jpos.java_pos.Controller.SettingController;
import com.jpos.java_pos.json.JSON;
import com.jpos.java_pos.json.JSONReader;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONObject;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class SplashModel {
    public ImageView splashLogo;

    public JFXComboBox dropDown;

    public JFXButton closeBtn;
    public JFXButton connect;

    static File file;
    DbConnector connector=new DbConnector();

    static HashMap<String,String> paths=new HashMap<>();

    public SplashModel(JFXButton connect,JFXButton closeBtn,JFXComboBox comboBox,ImageView splashLogo){
        this.connect=connect;
        this.splashLogo=splashLogo;
        this.dropDown=comboBox;
        this.closeBtn=closeBtn;
        loadHome();
        loadDatabase();
    }

    public void loadHome(){
            closeBtn.setOnAction(e-> closeWindow());
            connect.setOnAction(f->{
                if (!(dropDown.getSelectionModel().getSelectedItem()==null)){
                new ScreenLoader().load("/com/jpos/pos/Home.fxml",true, StageStyle.DECORATED,"/images/pos_icon.png");
                closeWindow();
            } else {
                    new SettingController().notification("Please Choose a Database","warning.png",3);
        }});
    }

    public void dbSetup(){

    }

    protected void closeWindow(){
        Stage stage= (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }


    public void loadDatabase(){
        JSONObject object=new JSONReader().read("dbs.json","database");
        String name= (String) object.get("name");
        String schema=(String) object.get("schema");
        dropDown.getItems().add(schema);
    }
}
