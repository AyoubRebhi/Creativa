package tn.esprit.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AfficherCommandeUser implements Initializable{

    @FXML
    private ListView<Commande> listView;

    @FXML
    private Button annulerBTN;

    @FXML
    private Label label;

    @FXML
    private Button retourBTN;

    private int idUtilisateurConnecte; // Variable pour stocker l'identifiant de l'utilisateur connecté

    public void setIdUtilisateurConnecte(int idUtilisateurConnecte) {
        this.idUtilisateurConnecte = idUtilisateurConnecte;
    }

    @FXML
    void Annuler(ActionEvent event) {
        Commande selectedCommande = listView.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
            // Créer une boîte de dialogue de confirmation avec des boutons personnalisés
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Confirmation de l'annulation de la commande");
            confirmationAlert.setContentText("Voulez-vous vraiment annuler cette commande ?");

            // Ajouter les boutons personnalisés à la boîte de dialogue
            ButtonType btnOui = new ButtonType("Oui");
            ButtonType btnNon = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationAlert.getButtonTypes().setAll(btnOui, btnNon);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == btnOui) {
                try {
                    // Mettre à jour le statut de la commande avec "Annulée"
                    selectedCommande.setStatus("Annulée");
                    ServiceCommande serviceCommande = new ServiceCommande();
                    serviceCommande.annulerCommande(selectedCommande.getId_cmd());

                    // Mettre à jour l'affichage dans la ListView
                    int selectedIndex = listView.getSelectionModel().getSelectedIndex();
                    listView.getItems().set(selectedIndex, selectedCommande);

                    // Afficher un message de succès
                    System.out.println("Statut de la commande mis à jour avec succès !");
                } catch (SQLException e) {
                    // Gérer l'exception en fonction de vos besoins
                    e.printStackTrace();
                }
            }
        } else {
            // Afficher un message d'erreur indiquant qu'aucune commande n'est sélectionnée
            System.out.println("Aucune commande sélectionnée !");
        }
    }






    @FXML
    void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/InterfaceUser.fxml"));
        try {
            retourBTN.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCommande serviceCommande=new ServiceCommande();
        List<Commande> l = null;
        try{
            l=serviceCommande.afficherCommandesUtilisateur(1);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(l);
        listView.getItems().addAll(l);

    }

}
