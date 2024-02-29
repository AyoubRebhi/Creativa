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
    @FXML
    private Button retourBTN;

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
        if(listView.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune commande sélectionnée");
            alert.setContentText("Veuillez sélectionner une commande à modifier");
            alert.showAndWait();
            return;
        }

        // Récupérer la commande sélectionnée
        Livraison selectedLivraison = listView.getSelectionModel().getSelectedItem();
        int idCmd = selectedLivraison.getId_cmd();
        int idLiv = selectedLivraison.getId_liv();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/ModifierLivraison.fxml"));
        try {
            Parent root = loader.load();
            ModifierLivraison controller = loader.getController();
            // Passer l'ID de la commande et user sélectionnée au contrôleur ModifierCommande
            controller.setIdCommande(idCmd);
            controller.setIdlivraison(idLiv);
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void Annuler(ActionEvent event) {
        Livraison selectedLivraison = listView.getSelectionModel().getSelectedItem();
        if (selectedLivraison != null) {
            try {
                // Mettre à jour le statut de la livraison avec "annulée"
                selectedLivraison.setStatus("Annulée");

                // Créer une instance de ServiceLivraison
                ServiceLivraison serviceLivraison = new ServiceLivraison();

                // Appeler la méthode annulerLivraison sur l'instance serviceLivraison
                serviceLivraison.annulerLivraison(selectedLivraison.getId_liv());

                // Supprimer la livraison annulée de la ListView
                listView.getItems().remove(selectedLivraison);

                // Afficher un message de succès
                System.out.println("Livraison annulée avec succès !");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Aucune livraison sélectionnée !");
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