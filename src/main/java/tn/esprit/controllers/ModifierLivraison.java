package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import tn.esprit.Models.Livraison;
import tn.esprit.Services.ServiceCommande;
import tn.esprit.Services.ServiceLivraison;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModifierLivraison {
    @FXML
    private Button ModifierLivraison;

    @FXML
    private RadioButton RB1;

    @FXML
    private RadioButton RB2;

    @FXML
    private TextField adresseTF;

    @FXML
    private TextField fraisTF;

    @FXML
    private TextField id_cmdTF;
    @FXML
    private TextField idLivTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private Label modifierL;

    @FXML
    private TextField moyenLivTF;

    @FXML
    private TextField statusTF;
    @FXML
    private Button retourBTN;

    @FXML
    void ModifierLivraison(ActionEvent event)throws SQLException {
        ServiceCommande serviceCommande = new ServiceCommande();
        int idLiv = Integer.parseInt(idLivTF.getText());
        int idCmd = Integer.parseInt(id_cmdTF.getText());
        int idUser = serviceCommande.getIdUtilisateurParNomComplet(id_userTF.getText());

        String status = statusTF.getText();
        String adresse = adresseTF.getText();
        String frais = fraisTF.getText();
        String moyenLiv = moyenLivTF.getText();
        Livraison l=new Livraison(idLiv,idCmd,idUser,status,adresse,frais,moyenLiv);
        ServiceLivraison serviceLivraison= new ServiceLivraison();

        serviceLivraison.modifier(l);


        // message de confirmation
        showAlert(Alert.AlertType.INFORMATION, "Succès", "Commande modifiée avec succès !");
    }

    // alerte msg
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


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
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherLivraison.fxml"));
        try {
            moyenLivTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
