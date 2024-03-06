package tn.esprit.Controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;
import tn.esprit.test.HelloApplication;

public class AjouterCategorie {

    @FXML
    private TextField titreTF;
    @FXML
    private TextArea descriptionTF;
    private List<Categorie> categories;
    private ListView<String> listView;
    private Categorie categorie1 = new Categorie();

    @FXML
    void afficherCategories(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/AfficherListeCategoriesAdmin.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void ajouterImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Récupérer le chemin du fichier sélectionné
            String mediaPath = selectedFile.getAbsolutePath();
            // Stocker le chemin du média dans la variable du projet
            categorie1.setCategorieImage(mediaPath);
        }
    }
    @FXML
    void ajouterCategorie(ActionEvent event) {
        String titre = titreTF.getText();
        String description = descriptionTF.getText();
        if (titre.isEmpty() || description.isEmpty()) {
            showAlert("Erreur", "Vous devez remplir le formulaire !");
        } else {
            String image = categorie1.getCategorieImage();
            Categorie categorie = new Categorie(titre,image,description);
            CategorieServices categorieServices = new CategorieServices();
            categorieServices.ajouter(categorie);

            showAlert("Succès", "La catégorie a été ajoutée avec succès.");
            // Rafraîchir la liste des catégories après l'ajout
            refreshCategoriesList();
        }
    }

    private void refreshCategoriesList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherListeCategoriesAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) titreTF.getScene().getWindow(); // Récupérer la fenêtre actuelle
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void initialize() {
        assert titreTF != null : "fx:id=\"titreTF\" was not injected: check your FXML file 'AjouterCategorieAdmin.fxml'.";

    }


}
