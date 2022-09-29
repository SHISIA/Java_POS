package com.jpos.java_pos.Model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MessageModel {
    public ImageView imgErrorIcon;
    public Label messageLabel;

    public MessageModel(ImageView imgErrorIcon,Label messageLabel){
        this.imgErrorIcon=imgErrorIcon;
        this.messageLabel=messageLabel;
        checkSelectedPrinter();
    }

    //checks if a printer is chosen or not
    public void checkSelectedPrinter(){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("selected.txt");
            InputStreamReader streamReader =
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            messageLabel.setText("No Printer Set");
            while ((line = reader.readLine()) != null) {
                messageLabel.setText("Last Printer:"+line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
