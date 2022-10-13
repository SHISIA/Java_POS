package com.jpos.java_pos.Controller;

import com.jpos.java_pos.Model.DbConnector;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Create {
    public TextField nameField;
    public PasswordField passField;
    public TextField idField;
    public TextField roleField;
    public TextField codeField;

    public void close(){
        Stage stage=(Stage) codeField.getScene().getWindow();
        stage.close();
    }

    public void createUser(){
        if (!(nameField.getText().isBlank()||passField.getText().isBlank()||idField.getText().isBlank()||roleField.getText().isBlank()
        ||codeField.getText().isBlank())){
            new DbConnector().updateStatements("insert into biz_hub_users values ('"+idField.getText()+"'," +
                    "'"+nameField.getText()+"'," +
                    "'"+roleField.getText()+"'," +
                    "'"+codeField.getText()+"'," +
                    "'"+passField.getText()+"',now());");
            new SettingController().notification("User "+nameField.getText()+" Created Successfully!!","success.png",2);
            nameField.clear();
            passField.clear();
            idField.clear();
            roleField.clear();
            codeField.clear();
            close();
        }else {
            new SettingController().notification("Empty Value(s)!!","warning.png",2);
        }
    }
}
