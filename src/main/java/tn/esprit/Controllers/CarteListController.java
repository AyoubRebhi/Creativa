package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tn.esprit.Models.CarteFidelite;
import tn.esprit.Models.session;
import tn.esprit.Services.CarteFideliteService;
import tn.esprit.Services.UserService;

public class CarteListController {
    @FXML
    private Button ajouterCarteButton;

    @FXML
    private Label soldeLabel;

    @FXML
    private Label labelajout;

    @FXML
    private Label labelsolde;

    private CarteFideliteService carteFideliteService = new CarteFideliteService();
    private UserService userService = new UserService();

    @FXML
    private void initialize() {
        int userId = session.id_utilisateur;

        // Vérifiez si l'utilisateur a une carte de fidélité
        int hasFidelityCard = carteFideliteService.carteExistsForUser(userId);

        // Check if the ID already exists
        if (hasFidelityCard != -1) {
            double solde = carteFideliteService.soldecarte(hasFidelityCard);
            soldeLabel.setText("Solde: " + solde);
            soldeLabel.setVisible(true);
            ajouterCarteButton.setVisible(false); // Masquer le bouton après avoir affiché le solde
            labelajout.setVisible(false); // Masquer le label ajout
            labelsolde.setVisible(true); // Afficher le label solde
        } else {

            ajouterCarteButton.setVisible(true);
            labelajout.setVisible(true); // Afficher le label ajout
            labelsolde.setVisible(false); // Masquer le label solde
        }
    }

    @FXML
    private void ajouterCarteAction(ActionEvent event) {
        // Implémentez l'action pour ajouter une carte
        // ...
        carteFideliteService.ajouter(new CarteFidelite(0,userService.getById(session.id_utilisateur)));

        // Après avoir ajouté la carte, masquez le bouton "Ajouter Carte", affichez le solde et mettez à jour les labels
        ajouterCarteButton.setVisible(false);
        labelajout.setVisible(false);

        // Obtenez le solde après avoir ajouté la carte
        double solde = carteFideliteService.soldecarteiduser(session.id_utilisateur);
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Mot de passe mis à jour avec succès.");

        soldeLabel.setText("Solde: " + solde+"  points");
        soldeLabel.setVisible(true);
        labelsolde.setVisible(true);
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    // ... Autres méthodes de votre contrôleur
}
