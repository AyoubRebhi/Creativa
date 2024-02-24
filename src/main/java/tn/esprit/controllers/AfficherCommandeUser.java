package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class AfficherCommandeUser implements Initializable{

    @FXML
    private ListView<Commande> listView;

    @FXML
    private Button annulerBTN;

    @FXML
    private Label label;

    @FXML
    private Button modifierBTN;

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
            // Mettre en œuvre la logique d'annulation de la commande
        } else {
            // Afficher un message d'erreur indiquant qu'aucune commande n'est sélectionnée
        }
    }

    @FXML
    void Modifier(ActionEvent event) {
        Commande selectedCommande = listView.getSelectionModel().getSelectedItem();
        if (selectedCommande != null) {
            // Mettre en œuvre la logique de modification de la commande
        } else {
            // Afficher un message d'erreur indiquant qu'aucune commande n'est sélectionnée
        }
    }

    @FXML
    void Retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/InterfaceUser.fxml"));
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
            l=serviceCommande.afficherCommandesUtilisateur(2);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(l);
        listView.getItems().addAll(l);

    }

}
