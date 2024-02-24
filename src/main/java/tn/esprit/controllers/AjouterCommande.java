package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private Button PasserLiv;
    @FXML
    private TextField statusTF;

    @FXML
    private DatePicker datePicker;


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

        c.setId_user(serviceCommande.getIdUtilisateurParNomComplet(id_userTF.getText()));
        c.setId_projet(serviceCommande.getAllProjectTitlesAndIds().get(idCombobox.getValue()));
        c.setDate(Date.valueOf(datePicker.getValue()));
        c.setQuantite(Integer.parseInt(quantiteTF.getText()));
        c.setMt_total(mt_totalTF.getText());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dateLivraison = new Date(dateFormat.parse(dateLivTF.getText()).getTime());
            c.setDate_livraison_estimee(dateLivraison);
        } catch (ParseException e) {
            // Gérer l'erreur de format de date incorrecte
            e.printStackTrace();
        }


        c.setCode_promo(Integer.parseInt(code_promoTF.getText()));
        c.setStatus(statusTF.getText());

        {
            serviceCommande.ajouter(c);
            // Affichage d'une alerte pour indiquer que la commande a été ajoutée avec succès
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Succès");
            alert.setContentText("La commande a été ajoutée avec succès !");
            alert.showAndWait();
        }
    }

    @FXML
    void PasserLiv(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AjouterLivraison.fxml"));
        try {
            dateLivTF.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/InterfaceUser.fxml"));
        try {
            quantiteTF.getScene().setRoot(loader.load());
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
    }

    @FXML
    void datePicker(ActionEvent event) {

    }

}
