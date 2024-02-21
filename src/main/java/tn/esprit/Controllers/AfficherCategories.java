package tn.esprit.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.Models.Categorie;
import tn.esprit.Services.CategorieServices;
import tn.esprit.test.HelloApplication;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AfficherCategories {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;
    @FXML
    private Label labelFX;
    private List<Categorie> categories;
    private ModifierCategorie controller;

    @FXML
    void initialize() {
        CategorieServices categorieServices = new CategorieServices();
        try {
            categories = categorieServices.afficher();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            String idName = "ID,     ";
            String categorieName = "Categorie,     ";
            String nbProjetName = "Nombre de projets, ";
            observableList.add(idName + categorieName + nbProjetName);
            for (Categorie categorie : categories) {
                int idCategorie = categorie.getId_categorie();
                String titreCategorie = categorie.getTitre();
                int nbProjets = categorieServices.calculerNbProjets(idCategorie).get(titreCategorie);
                observableList.add(idCategorie + ",       " + titreCategorie + ",                " + nbProjets);
            }
            listView.setItems(observableList);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void modifierCategorie(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ModifierCategorie.fxml"));
        try {
            Parent root = loader.load();
            ModifierCategorie controller = loader.getController();
            labelFX.getScene().setRoot(root);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
