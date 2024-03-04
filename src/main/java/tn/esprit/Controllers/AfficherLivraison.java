package tn.esprit.Controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import tn.esprit.Models.Livraison;
import tn.esprit.Services.ServiceLivraison;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class AfficherLivraison implements Initializable{
    @FXML
    private ListView<Livraison> listView;

    @FXML
    private Label labelFX;

    @FXML
    private Button updateBTN;

    @FXML
    private Button retourBTN;

    @FXML
    private ComboBox<String> comboboxId;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Charger toutes les livraisons au démarrage
        loadLivraisons();

        // Remplir le ComboBox avec les états de livraison
        ObservableList<String> etatsLivraison = FXCollections.observableArrayList(
                "En_cours",
                "Livrée",
                "Annulée"
        );
        comboboxId.setItems(etatsLivraison);
    }

    @FXML
    void ModifierLivraison(ActionEvent event) {
        if (listView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune livraison sélectionnée");
            alert.setContentText("Veuillez sélectionner une livraison à modifier");
            alert.showAndWait();
            return;
        }

        // Récupérer la livraison sélectionnée
        Livraison selectedLivraison = listView.getSelectionModel().getSelectedItem();
        int idCmd = selectedLivraison.getId_cmd();
        int idLiv = selectedLivraison.getId_liv();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/ModifierLivraison.fxml"));
        try {
            Parent root = loader.load();
            ModifierLivraison controller = loader.getController();
            // Passer l'ID de la commande et de la livraison sélectionnées au contrôleur ModifierLivraison
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
            // Créer une boîte de dialogue de confirmation avec des boutons personnalisés
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Confirmation de l'annulation de la livraison");
            confirmationAlert.setContentText("Voulez-vous vraiment annuler cette livraison ?");

            // Ajouter les boutons personnalisés à la boîte de dialogue
            ButtonType btnOui = new ButtonType("Oui");
            ButtonType btnNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationAlert.getButtonTypes().setAll(btnOui, btnNon);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == btnOui) {
                try {
                    // Mettre à jour le statut de la livraison avec "Annulée"
                    selectedLivraison.setStatus("Annulée");
                    ServiceLivraison serviceLivraison = new ServiceLivraison();
                    serviceLivraison.annulerLivraison(selectedLivraison.getId_liv());

                    // Supprimer la livraison annulée de la ListView
                    listView.getItems().remove(selectedLivraison);

                    // Afficher un message de succès
                    System.out.println("Livraison annulée avec succès !");
                } catch (SQLException e) {
                    // Gérer l'exception en fonction de vos besoins
                    e.printStackTrace();
                }
            }
        } else {
            // Afficher un message d'erreur dans une boîte de dialogue
            Alert erreurAlert = new Alert(Alert.AlertType.ERROR);
            erreurAlert.setTitle("Erreur");
            erreurAlert.setHeaderText("Aucune livraison sélectionnée !");
            erreurAlert.setContentText("Veuillez sélectionner une livraison à annuler.");
            erreurAlert.showAndWait();
        }
    }

    @FXML
    void Retour(ActionEvent event) throws SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/sidebarAdmin.fxml"));
        try {
            listView.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void comboboxId(ActionEvent event) {
        String selectedState = comboboxId.getValue(); // Obtenir l'état sélectionné dans le ComboBox

        // Nettoyer la liste actuelle dans le ListView
        listView.getItems().clear();

        // Appeler la méthode FilterByStatus pour obtenir les livraisons filtrées par état
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        try {
            List<Livraison> filteredLivraisons = serviceLivraison.FilterByStatus(selectedState);

            // Ajouter les livraisons filtrées à la liste affichée dans le ListView
            if (!filteredLivraisons.isEmpty()) {
                listView.getItems().addAll(filteredLivraisons);
            } else {
                // Afficher un message d'erreur si aucune livraison n'est trouvée pour l'état sélectionné
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Aucune livraison trouvée");
                alert.setHeaderText(null);
                alert.setContentText("Aucune livraison trouvée pour l'état sélectionné: " + selectedState);
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    private void loadLivraisons() {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        List<Livraison> livraisons = null;
        try {
            livraisons = serviceLivraison.afficher();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (livraisons != null) {
            listView.getItems().addAll(livraisons);
        }
    }

}