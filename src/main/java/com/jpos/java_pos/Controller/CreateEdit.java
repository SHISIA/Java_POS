package com.jpos.java_pos.Controller;

import com.jpos.java_pos.Model.DbConnector;
import com.jpos.java_pos.Model.User;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEdit implements Initializable {
    public TextField nameField;
    public PasswordField passField;

    DbConnector connector=new DbConnector();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    public void close(){
        Stage stage=(Stage) passField.getScene().getWindow();
        stage.close();
    }

    public void init(){
        User currentUser=ManageUsers.userClicked;
        nameField.setText(currentUser.getName());
        passField.setText(currentUser.getPassCode());
    }

    public void edit(){
        if (!(nameField.getText().isBlank()||passField.getText().isBlank())){
            connector.updateStatements("update biz_hub_users set user_name='"+nameField.getText()+"' , user_passcode='"+passField.getText()+"' where user_name='"+ManageUsers.userClicked.getName()+"';");
            new SettingController().notification(ManageUsers.userClicked.getName()+" Edited Successful!!","success.png",2);
            nameField.clear();
            passField.clear();
            close();
        }else {
            new SettingController().notification("Empty Value(s)!!","warning.png",2);
        }
    }

    //resets passcode to 12345(assumed as default)
    public void reset(){
        connector.updateStatements("update biz_hub_users set user_passcode='12345' where user_name='"+ManageUsers.userClicked.getName()+"';");
        new SettingController().notification(ManageUsers.userClicked.getName()+" Reset Successfully!!","success.png",2);
        close();
    }
}
