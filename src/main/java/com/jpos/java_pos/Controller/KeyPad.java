package com.jpos.java_pos.Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class KeyPad implements Initializable {


    public TextField codefield;
    public Button clearBtn;
    public Button backSpaceBtn;
    public Button one;
    public Button two;
    public Button three;
    public Button four;
    public Button five;
    public Button six;
    public Button seven;
    public Button eight;
    public Button nine;
    public Button loginBtn;
    public Button closeBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keySetUp();
    }

    public void keySetUp(){

        closeBtn.setOnAction(e->{
            Stage stage=(Stage) closeBtn.getScene().getWindow();
            stage.close();
        });
        backSpaceBtn.setOnAction(e -> codefield.setText(codefield.getText().substring(0, codefield.getText().length() - 1)));
//        zero.setOnAction(e -> barcodeSearch.appendText("0"));
        one.setOnAction(e -> codefield.appendText("1"));
        two.setOnAction(e -> codefield.appendText("2"));
        three.setOnAction(e -> codefield.appendText("3"));
        four.setOnAction(e ->codefield.appendText("4"));
        five.setOnAction(e ->codefield.appendText("5"));
        six.setOnAction(e -> codefield.appendText("6"));
        seven.setOnAction(e ->codefield.appendText("7"));
        eight.setOnAction(e ->codefield.appendText("8"));
        nine.setOnAction(e ->codefield.appendText("9"));
        clearBtn.setOnAction(e -> {
            codefield.clear();
        });

    }
}
