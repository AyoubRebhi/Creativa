package tn.esprit.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;


public class AfficherCommande implements Initializable{
    @FXML
    private ListView<Commande> listView;

    @FXML
    private Button suppBTN;


    @FXML
    private Label labelFX;

    @FXML
    private Button updateBTN;
    @FXML
    private Button ajouterBTN;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCommande serviceCommande=new ServiceCommande();
        List<Commande> l = null;
        try{
            l=serviceCommande.afficher();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(l);
        listView.getItems().addAll(l);

    }
    @FXML
    void ModifierCommande(ActionEvent event) {
        if(listView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune commande sélectionnée");
            alert.setContentText("Veuillez sélectionner une commande à modifier");
            alert.showAndWait();
            return;
        }

        // Récupérer la commande sélectionnée
        Commande selectedCommande = listView.getSelectionModel().getSelectedItem();
        int idCmd = selectedCommande.getId_cmd();
        int iduser = selectedCommande.getId_user();

        // Charger la vue ModifierCommande.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/ModifierCommande.fxml"));
        try {
            Parent root = loader.load();

            // Obtenir le contrôleur ModifierCommande
            ModifierCommande controller = loader.getController();

            // Passer l'ID de la commande et user sélectionnée au contrôleur ModifierCommande
            controller.setIdCommande(idCmd);
            controller.setIdUtilisateur(iduser);


            // Changer la scène pour afficher la vue ModifierCommande.fxml
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void Annuler(ActionEvent event) {
        Commande selectedCommande = listView.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
            try {
                // Mettre à jour le statut de la commande avec "annulée"
                selectedCommande.setStatus("Annulée");
                ServiceCommande serviceCommande = new ServiceCommande();
                serviceCommande.annulerCommande(selectedCommande.getId_cmd());

                // Supprimer la commande annulée de la ListView
                listView.getItems().remove(selectedCommande);

                // Afficher un message de succès
                System.out.println("Commande annulée avec succès !");
            } catch (SQLException e) {
                // Gérer l'exception en fonction de vos besoins
                e.printStackTrace();
            }
        } else {
            // Afficher un message d'erreur indiquant qu'aucune commande n'est sélectionnée
            System.out.println("Aucune commande sélectionnée !");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/InterfaceAdmin.fxml"));
        try {
            listView.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}