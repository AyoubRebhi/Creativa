package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Models.Categorie;
import tn.esprit.Models.Projet;
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
    private int id;
    private Categorie categorie;

    public TextField getTitreTF() {
        return titreTF;
    }

    void setParametre(int id, Categorie categorie){
        this.id = id;
        this.categorie=categorie;
        CategorieServices cs = new CategorieServices();
        titreTF.setText(categorie.getTitre());
    }
    private Categorie getCategorie(){
        if(!titreTF.getText().isEmpty()) {
            categorie.setTitre(titreTF.getText());
        }else{
            showAlert(Alert.AlertType.ERROR, "Erreur","Vous devez ajouter le titre !");
        }
        return categorie;
    }

    @FXML
    void modifierCategorie(ActionEvent event) {
        Categorie nouvelleCategorie= getCategorie();
        CategorieServices cs = new CategorieServices();
        cs.modifier(nouvelleCategorie);
        showAlert(Alert.AlertType.CONFIRMATION,"Succès","La categorie est modifiée avec succes !");
        refreshCategoriesList();
    }
    public void refreshCategoriesList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarAdminCategories.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titreTF.getScene().getWindow(); // Récupérer la fenêtre actuelle
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void afficherCategories(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/sidebarAdminCategories.fxml"));
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
