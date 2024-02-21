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
import tn.esprit.Models.Livraison;
import tn.esprit.Services.ServiceCommande;
import tn.esprit.Services.ServiceLivraison;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class AfficherLivraison implements Initializable{
    @FXML
    private ListView<Livraison> listView;
    @FXML
    private Button suppBTN;

    @FXML
    private Label labelFX;

    @FXML
    private Button updateBTN;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceLivraison serviceLivraison=new ServiceLivraison();
        List<Livraison> l = null;
        try{
            l=serviceLivraison.afficher();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(l);
        listView.getItems().addAll(l);

    }
    @FXML
    void ModifierLivraison(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/ModifierLivraison.fxml"));
        try {
            Parent root = loader.load();
            ModifierLivraison controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void supprimerLivraison(ActionEvent event) throws SQLException {
        Livraison selectedLivraison = listView.getSelectionModel().getSelectedItem();
        if (selectedLivraison != null) {
            ServiceCommande serviceCommande = new ServiceCommande();
            serviceCommande.supprimer(selectedLivraison.getId_cmd());
            listView.getItems().remove(selectedLivraison);
            showAlert("Succès", "Livraison supprimée avec succès !");
        } else {
            showAlert("Erreur", "Veuillez sélectionner une livraison à supprimer.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}