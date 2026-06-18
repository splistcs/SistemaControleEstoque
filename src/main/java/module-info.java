module sdedr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires spring.security.crypto;
    requires javafx.graphics;
    requires javafx.base;
    requires java.base;

    opens sdedr.model;
    opens sdedr.model.Enum;
    exports sdedr;
}
