package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import tn.esprit.Models.Commande;
import tn.esprit.Services.ServiceCommande;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class InterfaceUser implements Initializable {
    @FXML
    private Button AfficherBTN;

    @FXML
    private Button ajouterBTN;

    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void AfficherC(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AfficherCommande.fxml"));
        try {
            Parent root = loader.load();
            AfficherCommande controller = loader.getController();
            ajouterBTN.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AjouterC(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/AjouterCommande.fxml"));
        try {
            Parent root = loader.load();
            AjouterCommande controller = loader.getController();
            ajouterBTN.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
