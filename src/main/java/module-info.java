module com.jpos.java_pos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;
    requires escpos.coffee;
    requires com.google.protobuf;
    requires json.simple;

    opens com.jpos.java_pos to javafx.fxml, javafx.controls,com.jfoenix;
    opens com.jpos.java_pos.Model to javafx.fxml;

    exports com.jpos.java_pos;
    exports com.jpos.java_pos.Model;
    exports com.jpos.java_pos.Controller;
    opens com.jpos.java_pos.Controller to javafx.fxml;
    exports com.jpos.java_pos.json;
    opens com.jpos.java_pos.json to com.jfoenix, javafx.controls, javafx.fxml;
}