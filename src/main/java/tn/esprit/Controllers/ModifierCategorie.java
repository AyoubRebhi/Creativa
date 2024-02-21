package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;
import tn.esprit.test.HelloApplication;

import java.io.IOException;

public class ModifierCategorie {

    @FXML
    private TextField idTF;

    @FXML
    private Label modifier_categories;

    @FXML
    private TextField titreTF;
    private int categorieId;

    public void setCategorieId(int id) {
        this.categorieId = id;
    }
    public TextField getTitreTF() {
        return titreTF;
    }

    @FXML
    void modifierCategorie(ActionEvent event) {
        int idCategorie = Integer.parseInt(idTF.getText());
        String titreCategorie = titreTF.getText();

        Categorie c = new Categorie(idCategorie,titreCategorie);
        CategorieServices categorieServices = new CategorieServices();
        categorieServices.modifier(c);
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Catégorie modifiée avec succès !");

    }

    @FXML
    void initialize() {

    }

    @FXML
    void afficherCategories(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/AfficherCategories.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
