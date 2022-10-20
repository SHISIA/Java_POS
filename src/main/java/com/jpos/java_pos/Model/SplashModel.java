package com.jpos.java_pos.Model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jpos.java_pos.Controller.SettingController;
import com.jpos.java_pos.json.JSONReader;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;


public class SplashModel {
    public ImageView splashLogo;

    public JFXComboBox dropDown;
    public JFXComboBox hostDropDown;

    public JFXButton closeBtn;
    public JFXButton connect;

    Hyperlink setUpDbLink;

   public static String selectedDB;
   public static String selectedHost;
   public static String selectedPass;
   public static String selectedUserName;


    public SplashModel(JFXButton connect,JFXButton closeBtn,JFXComboBox comboBox,ImageView splashLogo,Hyperlink setUpDbLink,JFXComboBox hostDropDown){
        this.connect=connect;
        this.splashLogo=splashLogo;
        this.dropDown=comboBox;
        this.setUpDbLink=setUpDbLink;
        this.closeBtn=closeBtn;
        this.hostDropDown=hostDropDown;
        loadHome();
        loadDatabase();
        dbSetup();
    }

    public void loadHome(){
            closeBtn.setOnAction(e-> closeWindow());
            connect.setOnAction(f->{
            if (!(dropDown.getSelectionModel().getSelectedItem()==null || dropDown.getSelectionModel().getSelectedItem()=="")
                &&
                   !(hostDropDown.getSelectionModel().getSelectedItem()==null || hostDropDown.getSelectionModel().getSelectedItem()=="")){
                new ScreenLoader().load("/com/jpos/pos/Home.fxml",true, StageStyle.DECORATED,"/images/pos_icon.png");
                closeWindow();
            } else {
                    new SettingController().notification("Please Choose a Database/Host","warning.png",3);
        }});
    }

    public void dbSetup(){
        setUpDbLink.setOnAction(e->{
                closeWindow();
                new ScreenLoader().load("/com/jpos/pos/DBSetup.fxml",false, StageStyle.TRANSPARENT,"/images/pos_icon.png");
        });
    }

    protected void closeWindow(){
        Stage stage= (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }


    public void loadDatabase(){
        //shema
        ArrayList<JSONObject> objectArrayList=new ArrayList<>();
        JSONArray object=new JSONReader().reader("dbs.json");
        for (Object object1: object){
            JSONObject object2=(JSONObject) object1;
            JSONObject dbObject= (JSONObject) object2.get("database");
            objectArrayList.add(dbObject);
        }
        for (JSONObject object1:objectArrayList){
            String db=(String) object1.get("schema");
            if (!db.matches("")){
                if (!dropDown.getItems().contains(db)){
                    dropDown.getItems().add(db);
                }
            }
        }

        //host
        ArrayList<JSONObject> hostArrayList=new ArrayList<>();
        JSONArray hostArray=new JSONReader().reader("hst.json");
        for (Object object1: hostArray){
            JSONObject object2=(JSONObject) object1;
            JSONObject dbObject= (JSONObject) object2.get("hosts");
            hostArrayList.add(dbObject);
        }
        for (JSONObject object1:hostArrayList){
            String host=(String) object1.get("host");
            if (!host.matches("")){
                if (!hostDropDown.getItems().contains(host)){
                    hostDropDown.getItems().add(host);
                }
            }
        }


        setSelectedDB();
    }
    public void setSelectedDB(){
        dropDown.setOnAction(e->{
            if (!(dropDown.getSelectionModel().getSelectedItem()==null)){
                selectedDB= (String) dropDown.getSelectionModel().getSelectedItem();
            }
        });
        hostDropDown.setOnAction(e->{
            if (!(hostDropDown.getSelectionModel().getSelectedItem()==null)){
                selectedHost= (String) hostDropDown.getSelectionModel().getSelectedItem();
            }
        });

        JSONObject jsonObject1=new JSONReader().read("data_.json","server");
        selectedPass=(String) jsonObject1.get("password");
        selectedUserName=(String) jsonObject1.get("username");
    }

}
