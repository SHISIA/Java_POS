package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jpos.java_pos.Model.DbConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.io.*;
import java.net.URL;
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
            if (!(serverDbHostField.getText().isEmpty()||serverSchemaField.getText().isEmpty())){
                serverTestBtn.setOnAction(e->{
                    try {
                        DbConnector connector=new DbConnector();
                        connector.setSchema(serverSchemaField.getText());
                        connector.setHostName(serverDbHostField.getText());
                        ResultSet set=connector.getConnection().createStatement().executeQuery("select version();");
                        while (set.next()){
                            System.out.println("Success!! Version: "+set.getString(1));
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    textWriter("hostName",serverDbHostField.getText());
                    textWriter("schema",serverSchemaField.getText());
                });
            }
            if (!(serverUsernameField.getText().isEmpty()||serverPasswordField.getText().isEmpty())){
                serverCredBtn.setOnAction(e->{
                    textWriter("username",serverUsernameField.getText());
                    textWriter("password",serverPasswordField.getText());
                });
            }
    }

    public void activePrinter(){
        textWriter("selected.txt",(String) printersDropDown.getSelectionModel().getSelectedItem());
    }

    public void textWriter(String textName,String content) {
        try {
            if (!printersDropDown.getSelectionModel().isEmpty()){
                File file = new File(
                        getClass().getClassLoader().getResource(textName).getFile()
                );
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(content);
                writer.flush();
                writer.close();
            }else {

            }

        } catch (IOException exp) {
            System.out.println("Exception in generateFile " + exp);
        }
    }
}
