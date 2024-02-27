package tn.esprit.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;
import tn.esprit.test.HelloApplication;

public class AjouterCategorie {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField titreTF;
    private List<Categorie> categories;
    private ListView<String> listView;



    @FXML
    void afficherCategories(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/sidebarAdminCategories.fxml"));
        try {
            titreTF.getScene().setRoot(fxmlLoader.load());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    @FXML
    void ajouterCategorie(ActionEvent event) {
        String titre = titreTF.getText();
        if (titre.isEmpty()) {
            showAlert("Erreur", "Vous devez ajouter le titre de la catégorie !");
        } else {
            CategorieServices categorieServices = new CategorieServices();
            Categorie categorie = new Categorie();
            categorie.setTitre(titre);
            categorieServices.ajouter(categorie);

            // Rafraîchir la liste des catégories après l'ajout
            refreshCategoriesList();

            showAlert("Succès", "La catégorie a été ajoutée avec succès.");
        }
    }

    private void refreshCategoriesList() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sidebarAdminCategories.fxml"));
        try {
            titreTF.getScene().setRoot(loader.load());
            AfficherCategories controller = loader.getController();
            controller.initialize(); // Mettre à jour la liste des catégories
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
