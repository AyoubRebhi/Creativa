package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class AjouterCommande {
    @FXML
    private Button afficherBTN;

    @FXML
    private Label ajouter_commandes;

    @FXML
    private TextField code_promoTF;

    @FXML
    private Button continuerBTN;

    @FXML
    private TextField dateLivTF;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField id_projetTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField mt_totalTF;

    @FXML
    private TextField quantiteTF;

    @FXML
    void AfficherCommande(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherCommande.fxml"));
        try {
            dateLivTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AjouterCommande(ActionEvent event) throws SQLException {
        ServiceCommande serviceCommande = new ServiceCommande();
        Commande c = new Commande();

        c.setId_user(Integer.parseInt(id_userTF.getText()));
        c.setId_projet(Integer.parseInt(id_projetTF.getText()));
        c.setDate(Date.valueOf(dateTF.getText()));
        c.setQuantite(Integer.parseInt(quantiteTF.getText()));
        c.setMt_total(mt_totalTF.getText());
        c.setDate_livraison_estimee(Date.valueOf(dateLivTF.getText()));
        c.setCode_promo(Integer.parseInt(code_promoTF.getText()));
        {
            serviceCommande.ajouter(c);
            // Affichage d'une alerte pour indiquer que la commande a été ajoutée avec succès
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("La commande a été ajoutée avec succès !");
            alert.showAndWait();
        }
    }

}