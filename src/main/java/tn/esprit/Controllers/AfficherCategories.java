package tn.esprit.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.Models.Categorie;
import tn.esprit.Models.Projet;
import tn.esprit.Services.CategorieServices;
import tn.esprit.test.HelloApplication;
import javafx.scene.Scene;
import java.util.Map;


public class AfficherCategories {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    @FXML
    void initialize() {
        CategorieServices categorieServices = new CategorieServices();
        try {
            List<Categorie> categories = categorieServices.afficher();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            String idName="ID,     ";
            String categorieName="Categorie,     ";
            String nbProjetName="Nombre de projets, ";
            observableList.add(idName+categorieName+nbProjetName);
            for (Categorie categorie : categories) {
                int idCategorie = categorie.getId_categorie();
                String titreCategorie = categorie.getTitre();
                int nbProjets = categorieServices.calculerNbProjets(idCategorie).get(titreCategorie);
                observableList.add(idCategorie+",       "+titreCategorie +",                "+ nbProjets);
            }
            listView.setItems(observableList);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



}
