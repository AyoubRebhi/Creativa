package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tn.esprit.Models.CarteFidelite;
import tn.esprit.Models.session;
import tn.esprit.Services.CarteFideliteService;
import tn.esprit.Services.UserService;

import java.io.IOException;

public class SideBarController {
    public void directtoUsers(ActionEvent actionEvent) {
        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/list.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }




    }

    public void directtoCartes(ActionEvent actionEvent) {


        try {
            // Charger la page de connexion à partir du fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CarteList.fxml"));
            Parent root = loader.load();

            // Créer une nouvelle scène
            Scene scene = new Scene(root);

            // Obtenir la scène actuelle à partir de l'événement
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            // Changer la scène actuelle vers la nouvelle scène de connexion
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Gérer les erreurs de chargement de la page de connexion
        }

    }



    @FXML
    protected void ajoutercarte(ActionEvent event) {
        System.out.println(session.id_utilisateur);

        // Assuming you have a CarteFideliteService method to check if the ID exists
        CarteFideliteService carteFideliteService = new CarteFideliteService();
        UserService userService = new UserService();

        int userId = session.id_utilisateur;
        System.out.println("hhh"+carteFideliteService.carteExistsForUser(userId));

        // Check if the ID already exists
        if (carteFideliteService.carteExistsForUser(userId)==-1) {// If the ID doesn't exist, proceed with adding the new CarteFidelite
            CarteFidelite carteFidelite = new CarteFidelite(00, userService.getById(userId));
            carteFideliteService.ajouter(carteFidelite);
            showAlert("Carte ajoutée avec succès!");
              } else {

            afficherAlerteErreur("erreur" , "vous avez deja une carte")   ;
        }
    }

    private void showAlert(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void afficherAlerteErreur(String titre, String contenu) {
        Alert alerte = new Alert(Alert.AlertType.ERROR);
        alerte.setTitle(titre);
        alerte.setHeaderText(null);
        alerte.setContentText(contenu);
        alerte.showAndWait();
    }
}
