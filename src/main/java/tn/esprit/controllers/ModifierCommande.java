package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


public class ModifierCommande {
    @FXML
    private TextField code_promoTF;

    @FXML
    private TextField dateLivTF;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField id_projetTF;

    @FXML
    private TextField id_userTF;

    @FXML
    private TextField id_cmdTF;

    @FXML
    private Label modifier_commandes;

    @FXML
    private TextField mt_totalTF;

    @FXML
    private TextField quantiteTF;
    @FXML
    private Button retourBTN;


        @FXML
    void modifierBTN(ActionEvent event) throws SQLException {
        int idCmd = Integer.parseInt(id_cmdTF.getText());
        int idUser = Integer.parseInt(id_userTF.getText());
        int idProjet = Integer.parseInt(id_projetTF.getText());
        Date date = Date.valueOf(dateTF.getText());
        Date dateLivraisonEstimee = Date.valueOf(dateLivTF.getText());
        String mtTotal = mt_totalTF.getText();
        int quantite = Integer.parseInt(quantiteTF.getText());
        int codePromo = Integer.parseInt(code_promoTF.getText());
        Commande c=new Commande(idCmd,idUser,idProjet,date,mtTotal,quantite,dateLivraisonEstimee,codePromo);
        ServiceCommande serviceCommande= new ServiceCommande();

        serviceCommande.modifier(c);


        // message de confirmation
        showAlert(AlertType.INFORMATION, "Succès", "Commande modifiée avec succès !");
    }
    // alerte msg
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherCommande.fxml"));
        try {
            mt_totalTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}










