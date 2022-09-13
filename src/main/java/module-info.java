module com.jpos.java_pos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens com.jpos.java_pos to javafx.fxml;
    exports com.jpos.java_pos;
}