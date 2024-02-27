package tn.esprit.controllers;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import tn.esprit.Models.Commande;
import tn.esprit.Models.Livraison;
import tn.esprit.Services.ServiceCommande;
import tn.esprit.Services.ServiceLivraison;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class AjouterLivraison implements Initializable{
    @FXML
    private TextField adresseTF;
    @FXML
    private ComboBox<String> idCombobox;
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
        // Vérification de la saisie
        if (id_cmdTF.getText().isEmpty() || id_userTF.getText().isEmpty() || statusTF.getText().isEmpty() ||
                adresseTF.getText().isEmpty() || fraisTF.getText().isEmpty() || moyenLivTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return; // Arrêter l'exécution de la méthode si des champs sont vides
        }

        // Si tous les champs sont remplis, procéder à l'ajout de la livraison
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        Livraison l = new Livraison();

        l.setId_cmd(Integer.parseInt(id_cmdTF.getText()));
        l.setId_user(serviceLivraison.getIdUtilisateurParNomComplet(id_userTF.getText()));
        l.setStatus(statusTF.getText());
        l.setAdresse(adresseTF.getText());
        l.setFrais_liv(fraisTF.getText());
        l.setMoyen_livraison(moyenLivTF.getText());

        // Ajouter la livraison
        serviceLivraison.ajouter(l);

        // Affichage d'une alerte pour indiquer que la livraison a été ajoutée avec succès
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("La livraison a été ajoutée avec succès !");
        alert.showAndWait();

        // Envoi du message Twilio
        String messageText = "Nous sommes heureux de vous informer que votre commande est actuellement en cours de traitement. Notre équipe s'affaire à préparer vos articles avec le plus grand soin afin de vous garantir une satisfaction totale.";
       final String ACCOUNT_SID = "AC54e9aa0a574aae8b664854b28ca0cab2";
       final String AUTH_TOKEN = "dcb6fe67dd51d548e3a9df5b83a5fc55";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("+21653125536"), // Numéro du destinataire
                        new PhoneNumber("+19732873262"), // Votre numéro Twilio
                        messageText)
                .create();
        System.out.println(message.getSid());
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        statusTF.setText("En cours");
        fraisTF.setText("8dt");

    }
}
