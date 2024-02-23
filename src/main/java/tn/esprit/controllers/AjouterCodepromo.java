package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import tn.esprit.Models.Codepromo;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCodepromo;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.sql.Date;

public class AjouterCodepromo {
    @FXML
    private Button ajouterBTN;

    @FXML
    private TextField codepromoTF;

    @FXML
    private Label label;

    @FXML
    private TextField pourcentageTF;

    @FXML
    private Button afficherBTN;

    @FXML
    void ajouter(ActionEvent event) {
        ServiceCodepromo serviceCodepromo = new ServiceCodepromo();
        Codepromo c = new Codepromo();

        c.setCode_promo(Integer.parseInt(codepromoTF.getText()));
        c.setPourcentage(pourcentageTF.getText());

        {
            serviceCodepromo.ajouter(c);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("Code promo a été ajouté avec succès !");
            alert.showAndWait();

        }
    }

    @FXML
    void Afficher(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherCodepromo.fxml"));
        try {
            codepromoTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}