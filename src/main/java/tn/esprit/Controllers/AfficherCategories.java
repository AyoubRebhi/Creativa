package tn.esprit.Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.Models.Categorie;
import tn.esprit.Models.Projet;
import tn.esprit.Services.CategorieServices;
import tn.esprit.test.HelloApplication;
import javafx.scene.Scene;


public class AfficherCategories {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Categorie, Integer> idCol;

    @FXML
    private TableColumn<Categorie, Integer> nbProjetsCol;

    @FXML
    private TableView<Categorie> tableView;

    @FXML
    private TableColumn<Categorie, String> titreCol;

    @FXML
    void initialize() {
        CategorieServices categorieServices = new CategorieServices();
        try {
            List<Categorie> categories =  categorieServices.afficher();
            //observableList ==> Run f real time
            ObservableList<Categorie> observableList = FXCollections.observableList(categories);
            tableView.setItems(observableList);

            idCol.setCellValueFactory(new PropertyValueFactory<>("id_categorie"));
            titreCol.setCellValueFactory(new PropertyValueFactory<>("titre"));


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



}
