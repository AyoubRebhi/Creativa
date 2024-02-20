package tn.esprit.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;

public class AjouterCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField titreTF;

    @FXML
    void ajouterCategorie(ActionEvent event) {
        CategorieServices categorieServices = new CategorieServices();
        Categorie categorie = new Categorie();
        categorie.setTitre(titreTF.getText());
        categorieServices.ajouter(categorie);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setContentText("Categorie est ajoutée !");
        alert.showAndWait();

    }

    @FXML
    void initialize() {
        assert titreTF != null : "fx:id=\"titreTF\" was not injected: check your FXML file 'AjouterCategorie.fxml'.";

    }

}
