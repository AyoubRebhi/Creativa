package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class InterfaceAdmin {
    @FXML
    private Button afficherBTN;

    @FXML
    private Button afficherLivBTN;

    @FXML
    private Button affichercodeBTN;

    @FXML
    private Label interfaceAdmin;

    @FXML
    void AfficherCode(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherCodepromo.fxml"));
        try {
            Parent root = loader.load();
            AfficherCodepromo controller = loader.getController();
            interfaceAdmin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AfficherCommande(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherCommande.fxml"));
        try {
            Parent root = loader.load();
            AfficherCommande Controller = loader.getController();
            interfaceAdmin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AfficherLivraison(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherLivraison.fxml"));
        try {
            Parent root = loader.load();
            AfficherLivraison controller = loader.getController();
            interfaceAdmin.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
