package com.jpos.java_pos.Controller;

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
            printersDropDown.getItems().add("dfewhjbfwef");
    }

    public void activePrinter() {
        try {
            File file = new File(
                    getClass().getClassLoader().getResource("selected.txt").getFile()
            );
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("Number of words: " );
            writer.newLine();
            writer.write("Number of sentences: ");
            writer.newLine();
            writer.flush();
            writer.close();
//            ClassLoader classLoader = getClass().getClassLoader();
//            InputStream inputStream = classLoader.getResourceAsStream("selected.txt");
//            InputStreamReader streamReader =
//                    new InputStreamReader(inputStream, StandardCharsets.UTF_8);
//
//            BufferedReader reader = new BufferedReader(streamReader);
//
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    System.out.println(line);
//                }
        } catch (Exception exp) {
            System.out.println("Exception in generateFile " + exp);
        }
    }
}
