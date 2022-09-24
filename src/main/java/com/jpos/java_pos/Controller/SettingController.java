package com.jpos.java_pos.Controller;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML
    public JFXComboBox printersDropDown;
    @FXML
    public JFXListView usersView;
    @FXML
    public ImageView logoImage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkInstalledPrinters();
        printersDropDown.setOnAction(e->activePrinter());
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

    public void activePrinter() {
        try {
            if (!printersDropDown.getSelectionModel().isEmpty()){
                File file = new File(
                        getClass().getClassLoader().getResource("selected.txt").getFile()
                );
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write((String) printersDropDown.getSelectionModel().getSelectedItem());
                writer.flush();
                writer.close();
            }else {

            }

        } catch (IOException exp) {
            System.out.println("Exception in generateFile " + exp);
        }
    }
}
