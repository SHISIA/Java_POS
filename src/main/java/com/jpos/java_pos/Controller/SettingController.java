package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jpos.java_pos.Model.DBManager;
import com.jpos.java_pos.Model.DbConnector;
import com.jpos.java_pos.Model.Notification;
import com.jpos.java_pos.json.JSON;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML
    public JFXComboBox printersDropDown;
    @FXML
    public JFXListView usersView;
    @FXML
    public ImageView logoImage;
    @FXML
    public JFXTextField serverPasswordField;
    @FXML
    public JFXTextField serverUsernameField;
    @FXML
    public JFXTextField serverSchemaField;
    @FXML
    public JFXTextField serverDbHostField;
    @FXML
    public Button serverCredBtn;
    @FXML
    public Button serverTestBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkInstalledPrinters();
        printersDropDown.setOnAction(e->activePrinter());
        setDBAttributes();
    }

    public void checkInstalledPrinters(){
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            if(services.length != 0 || services != null) {
                for(PrintService service : services) {
                    String name = service.getName();
                    printersDropDown.getItems().add(name);
                }
            }
    }

    public void setDBAttributes(){
                JSON json=new JSON();
                String schema=serverSchemaField.getText();
                String host=serverDbHostField.getText();
                String username=serverUsernameField.getText();
                String password=serverPasswordField.getText();
                serverTestBtn.setOnAction(e->{
                    if (!(serverDbHostField.getText().isEmpty()&&serverSchemaField.getText().isEmpty()
                            &&serverUsernameField.getText().isEmpty()&&serverPasswordField.getText().isEmpty())){
                        try {
                            DbConnector connector=new DbConnector();
                            connector.setUserName(username);
                            connector.setHostName(host);
                            connector.setPassword(password);
                            connector.setSchema(schema);
                            Connection connection=connector.getConnection();
                            ResultSet set=connection.createStatement().executeQuery("select version();");
                            while (set.next()){
                                 System.out.println("Test Success!! MySQL Version: "+set.getString(1));
                                }
                            connection.close();
                        } catch (Exception ex) {
                            notification("Failed","error.png",5);
                        }
                    }
                });
                serverCredBtn.setOnAction(e->{
                    if (!(serverUsernameField.getText().isEmpty()&&serverPasswordField.getText().isEmpty())){
                        json.writeJSON("data.json","server","schema",schema,"hostName",host);
                        json.writeJSON("data_.json","server","username",username,"password",password);                        System.out.println("successfully written");
                        notification("Successfully Saved","success.png",5);
                    }
                });

    }
    public void notification(String message,String imgPath,int time){
        Notification notification=new Notification();
        notification.setImageIcon(imgPath);
        notification.setSeconds(time);
        notification.setMessage(message);
        try {
            Stage stage=new Stage();
            notification.start(stage);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void activePrinter(){
        textWriter("selected.txt",(String) printersDropDown.getSelectionModel().getSelectedItem());
    }

    public void textWriter(String textName,String content) {
        try {
                File file = new File(getClass().getClassLoader().getResource(textName).getFile());
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(content);
                writer.flush();
                writer.close();
        } catch (IOException exp) {
            System.out.println("Exception in generateFile " + exp);
        }
    }
}
