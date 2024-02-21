package tn.esprit.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.esprit.Services.CategorieServices;

public class ModifierCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField titreTF;

    @FXML
    void modifierCategorie(ActionEvent event) {
        CategorieServices categorieServices = new CategorieServices();

    }

    @FXML
    void initialize() {
        assert titreTF != null : "fx:id=\"titreTF\" was not injected: check your FXML file 'ModifierCategorie.fxml'.";

    }

}
