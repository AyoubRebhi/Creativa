package tn.esprit.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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


public class AfficherCommande implements Initializable{
    @FXML
    private ListView<Commande> listView;
    @FXML
    private Button deleteBTN;

    @FXML
    private Label labelFX;

    @FXML
    private Button updateBTN;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tn.esprit/ModifierCommande.fxml"));
        try {
            Parent root = loader.load();
            ModifierCommande controller = loader.getController();
            labelFX.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void SupprimerCommande(ActionEvent event) {


    }


}