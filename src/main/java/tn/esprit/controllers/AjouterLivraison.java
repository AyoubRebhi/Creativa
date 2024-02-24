package tn.esprit.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import tn.esprit.Models.Commande;
import tn.esprit.Models.Livraison;
import tn.esprit.Services.ServiceCommande;
import tn.esprit.Services.ServiceLivraison;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AjouterLivraison {
    @FXML
    private TextField adresseTF;

    @FXML
    private Button ajouterBTN;

    @FXML
    private Label ajouterL;

    @FXML
    private TextField fraisTF;

    @FXML
    private TextField id_cmdTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField moyenLivTF;

    @FXML
    private TextField statusTF;

    @FXML
    private RadioButton RB1;

    @FXML
    private RadioButton RB2;

    @FXML
    private Button afficherBTN;

    @FXML
    private Button retourBTN;
    @FXML

    void handleRB1(ActionEvent event) {
        if (RB1.isSelected()) {
            moyenLivTF.setText("Standard"); // mettre à jour le bouton en Standard
        }
    }

    @FXML
    void handleRB2(ActionEvent event) {
        if (RB2.isSelected()) {
            moyenLivTF.setText("Express"); //mettre à jour le bouton en Express
        }
    }

    @FXML
    void AfficherLivraison(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherLivraison.fxml"));
        try {
            moyenLivTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void AjouterLivraison(ActionEvent event) throws SQLException {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        Livraison l = new Livraison();

        l.setId_cmd(Integer.parseInt(id_cmdTF.getText()));
        l.setId_user(serviceLivraison.getIdUtilisateurParNomComplet(id_userTF.getText()));
        l.setStatus((statusTF.getText()));
        l.setAdresse((adresseTF.getText()));
        l.setFrais_liv(fraisTF.getText());
        l.setMoyen_livraison((moyenLivTF.getText()));
        {
            serviceLivraison.ajouter(l);
            // Affichage d'une alerte pour indiquer que la livraison a été ajoutée avec succès
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("La livraison a été ajoutée avec succès !");
            alert.showAndWait();
        }
    }

    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AjouterCommande.fxml"));
        try {
            moyenLivTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
