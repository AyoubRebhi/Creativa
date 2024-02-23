package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class AfficherCommandeUser {

    @FXML
    private Button annulerBTN;

    @FXML
    private Label label;

    @FXML
    private Button modifierBTN;

    @FXML
    private Button retourBTN;

    // Ajoutez ici les références nécessaires pour récupérer et manipuler la commande

    @FXML
    void Annuler(ActionEvent event) {
        // Logique pour annuler une commande
    }

    @FXML
    void Modifier(ActionEvent event) {
        // Logique pour modifier une commande
    }

    @FXML
    void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/InterfaceUser.fxml"));
        try {
            retourBTN.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
