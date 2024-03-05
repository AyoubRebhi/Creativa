module tn.esprit.maytest {
    requires itextpdf;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;
    requires java.desktop;

    opens tn.esprit.maytest.Models to javafx.base; // Open the Models package to javafx.base
    opens tn.esprit.maytest to javafx.fxml;
    exports tn.esprit.maytest;
    exports tn.esprit.maytest.Controllers;
    opens tn.esprit.maytest.Controllers to javafx.fxml;

}