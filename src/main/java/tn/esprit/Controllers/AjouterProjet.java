package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;
import tn.esprit.test.HelloApplication;

public class AjouterProjet {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> categorieChoiceBox;

    @FXML
    private TextField descriptionTF;

    @FXML
    private Button mediaBTN;

    @FXML
    private Label modifier_categories;

    @FXML
    private TextField prixTF;

    @FXML
    private TextField titreTF;

    @FXML
    void afficherProjets(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/AfficherProjetsParAdmin.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @FXML
    void ajouterMedia(ActionEvent event) {


    }

    @FXML
    void ajouterProjet(ActionEvent event) {
        String titre = titreTF.getText();
        Double prix = Double.parseDouble(prixTF.getText());
        String description = descriptionTF.getText();
        int idCategorie = categorieChoiceBox.getSelectionModel().getSelectedIndex() + 1; // L'indice de la liste commence à partir de 0
        if(titre.isEmpty()&& description.isEmpty()){
            showAlert("Erreur", "Vous devez remplir le formulaire !");
        }else{
        Projet projet = new Projet(titre, description, null, prix, idCategorie);
        ProjetServices projetServices = new ProjetServices();
        projetServices.ajouter(projet);

        showAlert("Succès", "Le projet a été ajouté avec succès.");}
        refreshProjetsList();
    }
    public void refreshProjetsList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherProjetsParAdmin.fxml"));
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
        assert categorieChoiceBox != null : "fx:id=\"categorieChoiceBox\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert descriptionTF != null : "fx:id=\"descriptionTF\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert mediaBTN != null : "fx:id=\"mediaBTN\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert modifier_categories != null : "fx:id=\"modifier_categories\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert prixTF != null : "fx:id=\"prixTF\" was not injected: check your FXML file 'AjouterProjet.fxml'.";
        assert titreTF != null : "fx:id=\"titreTF\" was not injected: check your FXML file 'AjouterProjet.fxml'.";

        // Récupérer les titres des catégories depuis ProjetServices
        ProjetServices projetServices = new ProjetServices();
        List<String> titresCategories = projetServices.afficherTitresCategories();

        // Peupler la categorieChoiceBox avec les titres des catégories
        categorieChoiceBox.setItems(FXCollections.observableArrayList(titresCategories));
    }


}
