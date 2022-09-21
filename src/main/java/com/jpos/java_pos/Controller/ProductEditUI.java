package com.jpos.java_pos.Controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class ProductEditUI {
    @FXML
    public JFXTextField changesField;
    @FXML
    public JFXComboBox colsDropDown;

    public void closeUI(){
        Stage stage=(Stage) changesField.getScene().getWindow();
        stage.close();
    }
}
