package tn.esprit.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import tn.esprit.Models.Livraison;
import tn.esprit.Services.ServiceLivraison;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


public class AfficherLivraisonUser implements Initializable {
    @FXML
    private Button annulerBTN;

    @FXML
    private Label label;

    @FXML
    private ListView<Livraison> listView;

    @FXML
    private Button retourBTN;

    @FXML
    void Annuler(ActionEvent event) {
        Livraison selectedLivraison = (Livraison) listView.getSelectionModel().getSelectedItem();
        if (selectedLivraison != null) {
            try {
                // Mettre à jour le statut de la commande avec "annulée"
                selectedLivraison.setStatus("Annulée");
                ServiceLivraison serviceLivraison= new ServiceLivraison();
                serviceLivraison.annulerLivraison(selectedLivraison.getId_cmd());

                // Supprimer la commande annulée de la ListView
                listView.getItems().remove(selectedLivraison);

                // Afficher un message de succès
                System.out.println("Livraison annulée avec succès !");
            } catch (SQLException e) {
                // Gérer l'exception en fonction de vos besoins
                e.printStackTrace();
            }
        } else {
            // Afficher un message d'erreur indiquant qu'aucune commande n'est sélectionnée
            System.out.println("Aucune Livraison sélectionnée !");
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/sidebarClient.fxml"));
        try {
            retourBTN.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        List<Livraison> livraisons = null;
        try {
            livraisons = serviceLivraison.afficherLivraisonUtilisateur(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (livraisons != null) {
            // Convertir la liste en ObservableList
            ObservableList<Livraison> observableList = FXCollections.observableArrayList(livraisons);
            // Ajouter les éléments à la ListView
            listView.setItems(observableList);
        }
    }

}
