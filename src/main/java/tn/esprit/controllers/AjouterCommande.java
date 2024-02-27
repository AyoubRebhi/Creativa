package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCodepromo;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.DatePicker;

public class AjouterCommande implements Initializable{
    ServiceCommande sm= new ServiceCommande();
    @FXML
    private ComboBox<String> idCombobox;
    @FXML
    private Button afficherBTN;

    @FXML
    private Label ajouter_commandes;

    @FXML
    private TextField code_promoTF;

    @FXML
    private Button continuerBTN;

    @FXML
    private TextField dateTF;

    @FXML
    private TextField id_projetTF;


    @FXML
    private TextField id_userTF;

    @FXML
    private TextField mt_totalTF;


    @FXML
    private Button PasserLiv;
    @FXML
    private TextField statusTF;

    @FXML
    private DatePicker datePicker;

    @FXML
    private DatePicker datePicker2;


    @FXML
    void AjouterCommande(ActionEvent event) throws SQLException {
        // Vérifier si tous les champs obligatoires sont remplis
        if (code_promoTF.getText().isEmpty() || id_userTF.getText().isEmpty() || mt_totalTF.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        // Vérifier si le code promo est disponible dans la base de données
        ServiceCodepromo serviceCodepromo = new ServiceCodepromo();
        String codePromo = code_promoTF.getText();
        if (!serviceCodepromo.codePromoExiste(codePromo)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Le code promo saisi n'existe pas !");
            alert.showAndWait();
            return;
        }

        //ajouter la commande si le code promo existe dans la base de données
        ServiceCommande serviceCommande = new ServiceCommande();
        Commande c = new Commande();

        c.setId_user(serviceCommande.getIdUtilisateurParNomComplet(id_userTF.getText()));
        c.setId_projet(serviceCommande.getAllProjectTitlesAndIds().get(idCombobox.getValue()));
        c.setDate(Date.valueOf(datePicker.getValue()));
        c.setMt_total(mt_totalTF.getText());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        c.setDate(Date.valueOf(datePicker2.getValue()));

        c.setCode_promo(Integer.parseInt(codePromo));
        c.setStatus(statusTF.getText());

        serviceCommande.ajouter(c);
        // Affichage d'une alerte pour indiquer que la commande a été ajoutée avec succès
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setContentText("La commande a été ajoutée avec succès !");
        alert.showAndWait();
    }





    @FXML
    void PasserLiv(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AjouterLivraison.fxml"));
        try {
            datePicker2.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/InterfaceUser.fxml"));
        try {
            statusTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ServiceCommande serviceCommande = new ServiceCommande();
            Map<String, Integer> projectTitlesAndIds = serviceCommande.getAllProjectTitlesAndIds();

            // Récupérer les titres de projet depuis la map
            ObservableList<String> projectTitles = FXCollections.observableArrayList();
            for (String title : projectTitlesAndIds.keySet()) {
                projectTitles.add(title);
            }

            // Ajouter les titres de projet à la ComboBox
            idCombobox.getItems().addAll(projectTitles);
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer l'exception selon votre cas
        }
        statusTF.setText("En cours");
        datePicker.setValue(LocalDate.now());

        // Définir la date estimée de livraison comme 5 jours après la date de passation de commande
        LocalDate date = datePicker.getValue();
        LocalDate date_Livraison_Estimee = date.plusDays(5);

// Définir la date estimée de livraison dans le DatePicker
        datePicker2.setValue(date_Livraison_Estimee);

    }


}
