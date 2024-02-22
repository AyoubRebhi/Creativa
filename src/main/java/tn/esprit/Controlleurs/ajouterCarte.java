package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import tn.esprit.Models.CarteFidelite;
import tn.esprit.Models.session;
import tn.esprit.Services.CarteFideliteService;
import tn.esprit.Services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class ajouterCarte implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    protected void ajoutercarte(ActionEvent event) {
        System.out.println(session.id_utilisateur);

        // Assuming you have a CarteFideliteService method to check if the ID exists
        CarteFideliteService carteFideliteService = new CarteFideliteService();
        UserService userService = new UserService();

        int userId = session.id_utilisateur;
        System.out.println("hhh"+carteFideliteService.carteExistsForUser(userId));
        int x=  carteFideliteService.carteExistsForUser(userId);
    // Check if the ID already exists
        if (x ==-1) {

            CarteFidelite carteFidelite = new CarteFidelite(00, userService.getById(userId));
            carteFideliteService.ajouter(carteFidelite);
            showAlert("Carte ajoutée avec succès!");

        } else {
            afficherAlerteErreur("erreur" , "vous avez deja une carte")   ;

            // If the ID doesn't exist, proceed with adding the new CarteFidelite

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
