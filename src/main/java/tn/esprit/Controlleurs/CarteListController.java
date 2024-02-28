package tn.esprit.Controlleurs;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private CarteFideliteService carteFideliteService = new CarteFideliteService();
    public UserService userService =new UserService();

    @FXML
    private void initialize() {
        int userId = session.id_utilisateur;

        // Vérifiez si l'utilisateur a une carte de fidélité
      int hasFidelityCard = carteFideliteService.carteExistsForUser(userId);

        // Check if the ID already exists
        if (hasFidelityCard !=-1) {

            // Si l'utilisateur a une carte, affichez le solde et masquez le bouton "Ajouter Carte"
            double solde = carteFideliteService.soldecarte(hasFidelityCard);
            soldeLabel.setText("Solde: " + solde);
            soldeLabel.setVisible(true);
            ajouterCarteButton.setVisible(false); // Masquer le bouton après avoir affiché le solde
        } else {
            carteFideliteService.ajouter(new CarteFidelite(0,userService.getById(userId)));
            // Si l'utilisateur n'a pas de carte, affichez le bouton "Ajouter Carte"
            ajouterCarteButton.setVisible(true);
        }
    }

    @FXML
    private void ajouterCarteAction(ActionEvent event) {
        // Implémentez l'action pour ajouter une carte
        // ...

        // Après avoir ajouté la carte, masquez le bouton "Ajouter Carte", affichez le solde et mettez à jour le Label
        ajouterCarteButton.setVisible(false);

        // Obtenez le solde après avoir ajouté la carte
        double solde = carteFideliteService.soldecarteiduser(session.id_utilisateur);
        soldeLabel.setText("Solde: " + solde);
        soldeLabel.setVisible(true);
    }

    // ... Autres méthodes de votre contrôleur
}


